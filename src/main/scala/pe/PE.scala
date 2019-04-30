
package pe

import chisel3._
import chisel3.util._

/**
  Author:JaeHun Jung
  Date: 04/26/2019
  Single PE hardware module in Eyriss architecture
  There are separate FIFO queues for storing the ALU input and output.
  ALU only target for the MAC(Multiplay and Accumulate) operation. 
  Note that the queue interface in the Chisel is decoupled with the support of ready-valid mechanism.
  */
class PE extends Module {
  val io = IO(new Bundle {
    val pixel_in = Input(UInt(16.W))
    val filter_in = Input(UInt(16.W))
    val psum_in = Decoupled(UInt(16.W)) //valid and bits are inputs
    val output = Output(UInt(16.W))
  })
  
  val psum_out = Decoupled(UInt(16.W))
  val pixel_queue = Queue(io.pixel_in, 12)
  val filter_queue = Queue(io.filter_in, 225)
  val psum_queue = Queue(psum_out, 24)
  val mul_result = Decoupled(UInt(16.W))

  pixel_queue.nodeq()
  filter_queue.nodeq()
  psum_queue.nodeq()

  when(pixel_queue.ready){
    pixel_queue.enq(pixel_in)
  }
  .elsewhen(filter_queue.ready){
    filter_queue.enq(filter_in)
  }
  .elsewhen()

  when(pixel_queue.valid && filter_queue.valid && mul_result.ready){
    mul_result.bits := pixel_queue.deq() * filter_queue.deq()
  }

  when(io.psum_out.ready && io.psum_in.valid && mul_result.valid){
    psum_queue.enq(mul_result.bits + io.psum_in.bits)
    io.output := mul_result.bits + io.psum_in.bits
  } 

  when(io.psum_out.ready && mul_result.valid && io.psum_in.valid === false.B){
    psum_queue.enq(mul_result.bits)
    io.output := mul_result.bits
  }

}