
package pe

import chisel3._
import chisel3.util._

/**
  Author:JaeHun Jung
  Date: 04/26/2019
  Single PE hardware module in Eyriss architecture
  There are separate FIFO queues for input filter weights, pixel ifmap inputs and PSUM
  transferred from other PE. PE will only do the multiplication of filter weights and 
  input features which are stored in the separate FIFO queues. Besides that, if the
  incoming PSUM data also will be stored in the seaprate queue and will be added to the
  multiplied output to generate accumulative PSUM for the convolutional layer.
  */
class PE extends Module {
  val io = IO(new Bundle {
    val pixel_in = Flipped(Decoupled(UInt(16.W)))
    val filter_in = Flipped(Decoupled(UInt(16.W)))
    val psum_in = Flipped(Decoupled(UInt(16.W)))
    val reset_accumulation = Input(Bool())
    val accumulate_input = Input(Bool())
    val out = Output(UInt(16.W))
  })

  val psum_out = Wire(Flipped(Decoupled((UInt(16.W)))))
  val from_inpsum = RegInit(0.U(16.W))
  val from_ifmap = RegInit(0.U(16.W))
  val mul_result = RegInit(0.U(16.W))
  val with_accumulation = RegInit(0.U(16.W))
  val without_accumulation = RegInit(0.U(16.W))
  val pixel_queue = Queue(io.pixel_in, 12)
  val filter_queue = Queue(io.filter_in, 224)
  val psum_queue = Queue(io.psum_in, 16)
  val psum_out_queue = Queue(psum_out, 24)

  from_inpsum := psum_queue.deq()
  from_ifmap := pixel_queue.deq() * filter_queue.deq()
  mul_result := Mux(io.accumulate_input, from_inpsum, from_ifmap)

  with_accumulation := psum_out_queue.deq() + mul_result
  without_accumulation := mul_result
  io.out := Mux(io.reset_accumulation, without_accumulation, with_accumulation)
  psum_out.enq(io.out)



  // when(io.accumulate_input === true.B){
    // mul_result := psum_queue.deq()
  // }.otherwise{
    // mul_result := pixel_queue.deq() * filter_queue.deq()
  // }


  // when(io.reset_accumulation === false.B){
    // io.out := psum_out.deq() + mul_result
    // psum_out.enq(io.out)
  // }.otherwise{
    // io.out := mul_result
    // psum_out.enq(io.out)
  // }



}