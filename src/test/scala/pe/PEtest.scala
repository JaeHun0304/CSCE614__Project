package lif

import chisel3.iotesters
import chisel3.iotesters.{PeekPokeTester}
import scala.util.Random




class PEUnitTester(c: PE) extends PeekPokeTester(c) {

  
  for (i <- 0 until 8) {
  	// Randomly generate input data
    val random_data1 = Random.nextInt(65536)
  	val random_data2 = Random.nextInt(65536)
  	val random_data3 = Random.nextInt(65536)
  	//  poke random data into the inputs
  	poke(c.io.pixel_in.data, random_data1)
  	poke(c.io.filter_in.data, random_data2)
  	poke(c.io.psum_in.data, random_data3)

    poke(c.io.pixel_queue.valid, (i>>0)%2)
    poke(c.io.filter_queue.valid,  (i>>0)%2)
    poke(c.io.mul_result.ready,  (i>>0)%2)
    step(1)

    poke(c.io.mul_result.valid, (i>>0)%2)
    poke(c.io.psum_out.ready, (i>>0)%2)

  }
}