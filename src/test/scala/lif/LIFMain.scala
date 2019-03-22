
package lif

import chisel3._

object LIFMain extends App {
	iotesters.Driver.execute(args, () => new LIF){
		c => new LIFUnitTester(c)
	}
}

object LIFMain2 extends App {
	iotesters.Driver.execute(args, () => new LIF){
		c => new LIFUnitTester2(c)
	}
}

object LIFMain3 extends App {
	iotesters.Driver.execute(args, () => new LIF){
		c => new LIFUnitTester3(c)
	}
}

object LIFMain4 extends App {
	iotesters.Driver.execute(args, () => new LIF){
		c => new LIFUnitTester4(c)
	}
}