
package lif

import chisel3._

class LIF extends Module {
	val io = IO(new Bundle {
    val reset = Input(Bool())
    val sj1, sj2, sj3 = Input(UInt())
    val firing_out = Output(Bool())
  })

  val sj1_prev, sj2_prev, sj3_prev, vi, vi_prev = Reg(UInt())

  when(io.reset === 1.U){
  	vi := 6.U
  	vi_prev := vi
  	sj1_prev := io.sj1
  	sj2_prev := io.sj2
  	sj3_prev := io.sj3
  }.otherwise{
  	vi := vi_prev + (sj1_prev + sj2_prev + sj3_prev) - 1.U
  	
  	when(vi >= 14.U){
  		io.firing_out := 1.U
  		vi := 6.U
  	}

  	sj1_prev := io.sj1
  	sj2_prev := io.sj2
  	sj3_prev := io.sj3
  	vi_prev := vi
  }
}