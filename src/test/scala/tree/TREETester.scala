
package tree

import chisel3.iotesters
import chisel3.iotesters.{PeekPokeTester}

class TREETester(c: TREE) extends PeekPokeTester(c){
	poke(c.io.in, 180)
	step(1)
	expect(c.io.out, 4)

	poke(c.io.in, 120)
	step(1)
	expect(c.io.out, 3)

	poke(c.io.in, 90)
	step(1)
	expect(c.io.out, 2)

	poke(c.io.in, 70)
	step(2)
	expect(c.io.out, 1)

	poke(c.io.in, 40)
	step(2)
	expect(c.io.out, 0)
}