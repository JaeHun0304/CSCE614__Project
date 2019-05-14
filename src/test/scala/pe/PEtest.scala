

package pe

import chisel3.iotesters
import chisel3.iotesters.{PeekPokeTester}
import scala.util.Random


/*
  Author:JaeHun Jung
  Date: 04/28/2019
  Test code for the PE.scala file. There are two cases in the test. First test has input
  PSUM(Partial SUM) and the other test does not have PSUM. The addition of input PSUM from
  other PE only activated when there is a data in the PSUM input queue.
*/

// Test case when incoming PSUM data is existed
class PEUnitTester(c: PE) extends PeekPokeTester(c) {

  	// Randomly generate input data
    val random_data1 = Random.nextInt(50)
  	val random_data2 = Random.nextInt(50)
  	val random_data3 = Random.nextInt(50)
    val random_data4 = Random.nextInt(50)
    val random_data5 = Random.nextInt(50)
  	
    //  poke random generated data into ifmap, filter, and incoming PSUM

    poke(c.io.reset_accumulation, 0)
    poke(c.io.accumulate_input, 1)
    poke(c.io.pixel_in.valid, 1)
    poke(c.io.pixel_in.bits, random_data1)
    poke(c.io.filter_in.valid, 1)
    poke(c.io.filter_in.bits, random_data2)
    poke(c.io.psum_in.valid, 1)
    poke(c.io.psum_in.bits, random_data3)
    step(1)

    poke(c.io.psum_in.bits, 0)
    poke(c.io.pixel_in.valid, 1)
    poke(c.io.pixel_in.bits, random_data4)
    poke(c.io.filter_in.valid, 1)
    poke(c.io.filter_in.bits, random_data5)
    step(10)

}

// Same mechanism but case when no incoming PSUM data
class PEUnitTester2(c: PE) extends PeekPokeTester(c) {

    
    val random_data1 = Random.nextInt(50)
    val random_data2 = Random.nextInt(50)
    val random_data3 = Random.nextInt(50)
    val random_data4 = Random.nextInt(50)
    val random_data5 = Random.nextInt(50)
    
    poke(c.io.reset_accumulation, 1)
    poke(c.io.accumulate_input, 1)
    poke(c.io.pixel_in.valid, 1)
    poke(c.io.pixel_in.bits, random_data1)
    poke(c.io.filter_in.valid, 1)
    poke(c.io.filter_in.bits, random_data2)
    step(1)

    poke(c.io.accumulate_input, true)
    poke(c.io.pixel_in.valid, 1)
    poke(c.io.pixel_in.bits, random_data3)
    poke(c.io.filter_in.valid, 1)
    poke(c.io.filter_in.bits, random_data4)
    step(2)

}