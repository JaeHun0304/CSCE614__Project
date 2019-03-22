
package lif

import chisel3.iotesters
import chisel3.iotesters.{PeekPokeTester}
import scala.collection.mutable.ListBuffer

/*
  Author:JaeHun Jung
  Date: 03/22/2019
  Description: This is the hardware test module for the class LIFin Chisel language. 
  If you are not familiar with the Chisel please check https://github.com/freechipsproject/chisel3.
  The different 4 scenarios implemented in the independent class but strucutre is same, just the elements of the "List" are different.
*/


class LIFUnitTester(c: LIF) extends PeekPokeTester(c) {

// Initialize three ListBuffer for storing 3 neruons presynaptic spike sequences.
	var test_case1_sj1 = new ListBuffer[Int]()
	var test_case1_sj2 = new ListBuffer[Int]()
	var test_case1_sj3 = new ListBuffer[Int]()

// Push necessary sequence value to the Lists.
	for(i <- 1 to 5){	
		test_case1_sj1 ++= List[Int](1,1,1,1,1,0,0)
		test_case1_sj2 ++= List[Int](1,1,1,1,1,0,0)
		test_case1_sj3 ++= List[Int](1,1,1,1,1,0,0)
	}

// iterate over the element of the spike sequences to poke necessary Sj[t] inputs to the LIF class.
	for(i <- 0 until test_case1_sj1.length){
		if(i == 0){
			poke(c.io.sj1, test_case1_sj1(i))
			poke(c.io.sj2, test_case1_sj2(i))
			poke(c.io.sj3, test_case1_sj3(i))
			poke(c.io.reset, 1)
			step(1)   //wait for 1 clock cycle for the register update based on the poked inputs
			poke(c.io.reset, 0)
		}
		else{
			poke(c.io.sj1, test_case1_sj1(i))
			poke(c.io.sj2, test_case1_sj2(i))
			poke(c.io.sj3, test_case1_sj3(i))
			step(1)}	//wait for 1 clock cyle for the register update
	}
}

// Same strucutre with different seeding into spike sequences. (Case 2)
class LIFUnitTester2(c: LIF) extends PeekPokeTester(c) {

	var test_case1_sj1 = new ListBuffer[Int]()
	var test_case1_sj2 = new ListBuffer[Int]()
	var test_case1_sj3 = new ListBuffer[Int]()

	for(i <- 1 to 5){	
		test_case1_sj1 ++= List[Int](1,1,1,1,1,0,0)
		test_case1_sj2 ++= List[Int](1,1,1,1,1,0,0)
		test_case1_sj3 ++= List[Int](0,0,0,0,0,1,1)
	}

	for(i <- 0 until test_case1_sj1.length){
		if(i == 0){
			poke(c.io.sj1, test_case1_sj1(i))
			poke(c.io.sj2, test_case1_sj2(i))
			poke(c.io.sj3, test_case1_sj3(i))
			poke(c.io.reset, 1)
			step(1)
			poke(c.io.reset, 0)
		}
		else{
			poke(c.io.sj1, test_case1_sj1(i))
			poke(c.io.sj2, test_case1_sj2(i))
			poke(c.io.sj3, test_case1_sj3(i))
			step(1)}
	}
}

// Same strucutre with different seeding into spike sequences. (Case 3)
class LIFUnitTester3(c: LIF) extends PeekPokeTester(c) {

	var test_case1_sj1 = new ListBuffer[Int]()
	var test_case1_sj2 = new ListBuffer[Int]()
	var test_case1_sj3 = new ListBuffer[Int]()

	for(i <- 1 to 5){	
		test_case1_sj1 ++= List[Int](0,0,0,0,0,1,1)
		test_case1_sj2 ++= List[Int](0,0,0,0,0,1,1)
		test_case1_sj3 ++= List[Int](1,1,1,1,1,0,0)
	}

	for(i <- 0 until test_case1_sj1.length){
		if(i == 0){
			poke(c.io.sj1, test_case1_sj1(i))
			poke(c.io.sj2, test_case1_sj2(i))
			poke(c.io.sj3, test_case1_sj3(i))
			poke(c.io.reset, 1)
			step(1)
			poke(c.io.reset, 0)
		}
		else{
			poke(c.io.sj1, test_case1_sj1(i))
			poke(c.io.sj2, test_case1_sj2(i))
			poke(c.io.sj3, test_case1_sj3(i))
			step(1)}
	}
}

// Same strucutre with different seeding into spike sequences. (Case 4)
class LIFUnitTester4(c: LIF) extends PeekPokeTester(c) {

	var test_case1_sj1 = new ListBuffer[Int]()
	var test_case1_sj2 = new ListBuffer[Int]()
	var test_case1_sj3 = new ListBuffer[Int]()

		test_case1_sj2 ++= List[Int](0,0,0,0,0)

	for(i <- 1 to 2){	
		test_case1_sj1 ++= List[Int](0,0,0,0,0,0,0,0,0,0,1,1,1,1,1)
		test_case1_sj2 ++= List[Int](1,1,1,1,1,0,0,0,0,0,0,0,0,0,0)
		test_case1_sj3 ++= List[Int](1,1,1,1,1,0,0,0,0,0,0,0,0,0,0)
	}
		test_case1_sj1 ++= List[Int](0,0,0,0,0)
		test_case1_sj3 ++= List[Int](1,1,1,1,1)


	for(i <- 0 until test_case1_sj1.length){
		if(i == 0){
			poke(c.io.sj1, test_case1_sj1(i))
			poke(c.io.sj2, test_case1_sj2(i))
			poke(c.io.sj3, test_case1_sj3(i))
			poke(c.io.reset, 1)
			step(1)
			poke(c.io.reset, 0)
		}
		else{
			poke(c.io.sj1, test_case1_sj1(i))
			poke(c.io.sj2, test_case1_sj2(i))
			poke(c.io.sj3, test_case1_sj3(i))
			step(1)}
	}
}