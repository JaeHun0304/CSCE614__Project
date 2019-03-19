
package tree

import chisel3._

class TREE extends Module {
	val io = IO(new Bundle {
		val in = Input(UInt(16.W))
		val out = Output(UInt(16.W))
	})

	val regA = RegInit(0.U(16.W))	//temporary results will be stored in registers
	val regB = RegInit(0.U(16.W))
	val regC = RegInit(0.U(16.W))

	when(io.in < 100.U){regA := io.in}	//classify input based on the generated tree
		.otherwise{regB := io.in}

	when(regA < 80.U){regC := regA}
		.otherwise{io.out := 2.U}

	when(regB < 130.U){io.out := 3.U}
		.otherwise{io.out := 4.U}

	when(regC < 50.U){io.out := 0.U}
		.otherwise{io.out := 1.U}

}

// object TREEMain extends App {
  // chisel3.Driver.execute(args, () => new TREE)
// }