
package lif

import chisel3._

object LIFMain extends App {
	iotesters.Driver.execute(args, () => new LIF){
		c => new LIFUnitTester(c)
	}
}