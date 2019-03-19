
package tree

import chisel3._

// test:runMain tree.TREEMain --backend-name verilator

object TREEMain extends App {
  iotesters.Driver.execute(args, () => new TREE) {
    c => new TREETester(c)
  }
}