
package lif

import chisel3.iotesters
import chisel3.iotesters.{PeekPokeTester}
import scala.collection.mutable.ListBuffer

class LIFUnitTester(c: LIF) extends PeekPokeTester(c) {

	var test_case1_sj1 = new ListBuffer[Int]()
	var test_case1_sj2 = new ListBuffer[Int]()
	var test_case1_sj3 = new ListBuffer[Int]()

	for(i <- 1 to 5){	
		test_case1_sj1 ++= List[Int](1,1,1,1,1)
		test_case1_sj1 ++= List[Int](0,0)
		test_case1_sj2 ++= List[Int](1,1,1,1,1)
		test_case1_sj2 ++= List[Int](0,0)
		test_case1_sj3 ++= List[Int](1,1,1,1,1)
		test_case1_sj3 ++= List[Int](0,0)
	}

	for(i <- 0 to test_case1_sj1.length){
		if(i == 0){
			poke(c.io.reset, 1)
			poke(c.io.sj1, test_case1_sj1(i))
			poke(c.io.sj2, test_case1_sj2(i))
			poke(c.io.sj3, test_case1_sj3(i))
			step(1)
			poke(c.io.reset, 0)
		}
			poke(c.io.sj1, test_case1_sj1(i))
			poke(c.io.sj2, test_case1_sj2(i))
			poke(c.io.sj3, test_case1_sj3(i))
			step(1)
	}
}