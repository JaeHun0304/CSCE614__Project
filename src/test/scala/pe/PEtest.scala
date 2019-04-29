package lif

import chisel3.iotesters
import chisel3.iotesters.{PeekPokeTester}
import scala.collection.mutable.ListBuffer



class PEUnitTester(c: PE) extends PeekPokeTester(c) {

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