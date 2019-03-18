;buildInfoPackage: chisel3, version: 3.1.6, scalaVersion: 2.11.12, sbtVersion: 1.1.1, builtAtString: 2018-12-21 23:42:51.273, builtAtMillis: 1545435771273
circuit TREE : 
  module TREE : 
    input clock : Clock
    input reset : UInt<1>
    output io : {flip in : UInt<16>, out : UInt<16>}
    
    reg regA : UInt<16>, clock with : (reset => (reset, UInt<16>("h00"))) @[TREE.scala 12:27]
    reg regB : UInt<16>, clock with : (reset => (reset, UInt<16>("h00"))) @[TREE.scala 13:27]
    reg regC : UInt<16>, clock with : (reset => (reset, UInt<16>("h00"))) @[TREE.scala 14:27]
    node _T_16 = lt(io.in, UInt<7>("h064")) @[TREE.scala 16:20]
    when _T_16 : @[TREE.scala 16:28]
      regA <= io.in @[TREE.scala 16:34]
      skip @[TREE.scala 16:28]
    else : @[TREE.scala 17:27]
      regB <= io.in @[TREE.scala 17:33]
      skip @[TREE.scala 17:27]
    node _T_18 = lt(regA, UInt<7>("h050")) @[TREE.scala 19:19]
    when _T_18 : @[TREE.scala 19:26]
      regC <= regA @[TREE.scala 19:32]
      skip @[TREE.scala 19:26]
    else : @[TREE.scala 20:27]
      io.out <= UInt<2>("h02") @[TREE.scala 20:35]
      skip @[TREE.scala 20:27]
    node _T_21 = lt(regB, UInt<8>("h082")) @[TREE.scala 22:19]
    when _T_21 : @[TREE.scala 22:27]
      io.out <= UInt<2>("h03") @[TREE.scala 22:35]
      skip @[TREE.scala 22:27]
    else : @[TREE.scala 23:27]
      io.out <= UInt<3>("h04") @[TREE.scala 23:35]
      skip @[TREE.scala 23:27]
    node _T_25 = lt(regC, UInt<6>("h032")) @[TREE.scala 25:19]
    when _T_25 : @[TREE.scala 25:26]
      io.out <= UInt<1>("h00") @[TREE.scala 25:34]
      skip @[TREE.scala 25:26]
    else : @[TREE.scala 26:27]
      io.out <= UInt<1>("h01") @[TREE.scala 26:35]
      skip @[TREE.scala 26:27]
    
