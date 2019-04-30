
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
    val out = Output(UInt(16.W))
  })

  val mul_result = Wire(UInt(16.W))
  val pixel_queue = Queue(io.pixel_in, 12)
  val filter_queue = Queue(io.filter_in, 225)
  val psum_queue = Queue(io.psum_in, 16)

  mul_result := pixel_queue.deq() * filter_queue.deq()
  io.out := mul_result + psum_queue.deq()

}