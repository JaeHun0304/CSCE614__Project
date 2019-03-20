
package pe

import chisel3._
import chisel3.util._

/**
  Author:JaeHun Jung
  Date: 03/20/2019
  Single PE hardware module in Eyriss architecture
  There are separate FIFO queues for storing the ALU input and output. 
  Note that the queue interface in the Chisel is decoupled with the support of ready-valid mechanism.
  */
class PE extends Module {
  val io = IO(new Bundle {
    val pe_in = Flipped(Decoupled(UInt(32.W)))
    val pe_out = Decoupled(UInt(32.W))
  })

  val alu_in1 = Decoupled(RegInit(0.U(32.W)))
  val alu_in2 = Decoupled(RegInit(0.U(32.W)))
  val alu_in3 = Decoupled(RegInit(0.U(32.W)))
  val alu_out = Flipped(Decoupled(RegInit(0.U(32.W))))

  val pe_fifo_in = Queue(io.pe_in, 5)
  val pe_fifo_out = Queue(alu_out, 5)

  io.pe_out <> pe_fifo_out
  alu_in1 <> pe_fifo_in
  alu_in2 <> pe_fifo_in
  alu_in3 <> pe_fifo_in

  when(alu_in1.ready === 1.U && alu_in2.ready === 1.U && alu_in3.ready === 1.U){
  alu_in1.bits := pe_fifo_in.deq 
  alu_in2.bits := pe_fifo_in.deq
  alu_in3.bits := pe_fifo_in.deq
  }

  when(alu_in1.valid === 1.U && alu_in2.valid === 1.U && alu_in3.valid === 1.U && alu_out.ready === 1.U){
    alu_out.bits := alu_in1.bits + (alu_in2.bits * alu_in3.bits)
  }

}