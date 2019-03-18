
package tree

import chisel3._

class TREE extends Module {
	val io = IO(new Bundle {
		val in = Input(UInt(16.W))
		val out = Output(UInt(16.W))
	})

	val regA = RegInit(UInt(16.W), 0.U)	//temporary results will be stored in registers
	val regB = RegInit(UInt(16.W), 0.U)
	val regC = RegInit(UInt(16.W), 0.U)

	when(io.in < 100.U){regA := io.in}	//classify input based on the generated tree
		.otherwise{regB := io.in}

	when(regA < 80.U){regC := regA}
		.otherwise{io.out := 2.U}

	when(regB < 130.U){io.out := 3.U}
		.otherwise{io.out := 4.U}

	when(regC < 50.U){io.out := 0.U}
		.otherwise{io.out := 1.U}

}