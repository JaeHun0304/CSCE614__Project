
package lif

import chisel3._

/*
  Author:JaeHun Jung
  Date: 03/22/2019
  Description: This is the hardware module that implements LIF (Leaky Integrate-and-Fire Model)
  in Chisel language. If you are not familiar with the Chisel please check https://github.com/freechipsproject/chisel3. 
*/

// There are 4 inputs and single output in this hardware class
// sj1, sj2, sj3 are signal of neuron which indicates whether it is fired(1) or not(0)
// reset signal is used to initialize the membrance potential function and three previous neuron signals
class LIF extends Module {  
	val io = IO(new Bundle {
    val reset = Input(Bool())
    val sj1, sj2, sj3 = Input(UInt(1.W))
    val firing_out = Output(Bool())
  })

  val sj1_prev, sj2_prev, sj3_prev = RegInit(0.U(1.W))  // sj1_prev, sj2_prev, and sj3_prev registers save 1-bit Sj[t-1]
  val vi, vi_prev = RegInit(0.U(5.W)) // vi and vi_prev registers save membrane potential at t and at (t-1) respectively

  io.firing_out := 0.U   //initialize firing output waveform to zero

// When reset is treu(1), initialize registers, note that vi is initialized into resting poetntial which is 6
  when(io.reset === 1.U){  
  	vi := 6.U
  	vi_prev := vi      //Initialize Vi[t-1] with Vi[t] for t=0 case
  	sj1_prev := io.sj1 //For the first input, initialize Sj[t-1] with input of Sj[t] for t=0 case
  	sj2_prev := io.sj2
  	sj3_prev := io.sj3
  }.otherwise{ 
    // perform LIF computation if reset flag is not set
  	vi := vi_prev + (sj1_prev + 2.U * sj2_prev + 3.U * sj3_prev) - 1.U
  	// When Vi[t] memebrane potential exceeds threshold, set firing_out flag and reset Vi[t] to resting potential.
  	when(vi >= 14.U){
  		io.firing_out := 1.U
  		vi := 6.U
  	}
    // After computation and comparison are finisehd, update Sj[t-1] and Vi[t-1] for the next calculation.
  	sj1_prev := io.sj1
  	sj2_prev := io.sj2
  	sj3_prev := io.sj3
  	vi_prev := vi
  }

}