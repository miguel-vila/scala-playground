package co.mglvl

trait Nat {
  type Plus[N<:Nat] <: Nat
  type Times[N<:Nat] <: Nat
  //type CoMinus[N <: Succ[this.type]] <: N
}

trait _0 extends Nat {
  type Plus[N<:Nat] = N
  type Times[N<:Nat] = _0
  //type CoMinus[N <: Succ[this.type]] = N
}

trait Succ[Prev <: Nat] extends Nat {
  type Plus[N<:Nat] = Succ[Prev#Plus[N]]
  type Times[N<:Nat] = N#Plus[Prev#Times[N]]
  type MinusOne = Prev
  //type CoMinus[N <: Succ[this.type]] = (Prev#CoMinus[N])
}

object Tests {
  type _1 = Succ[_0]
  type _2 = Succ[_1]
  type _3 = Succ[_2]
  type _4 = Succ[_3]
  type _5 = Succ[_4]
  type _6 = Succ[_5]
  type _7 = Succ[_6]
  type _8 = Succ[_7]
  type _9 = Succ[_8]
  type _10 = Succ[_9]

  type |+|[A<:Nat,B<:Nat] = A#Plus[B]
  type |*|[A<:Nat,B<:Nat] = A#Times[B]

  implicitly[ _0 |+| _1 =:= _1 ]
  implicitly[ _2 |+| _3 =:= _5 ]
  implicitly[ _6 |+| _1 =:= _7 ]
  implicitly[ _7 |+| _3 =:= _10 ]
  implicitly[ _5 |*| _1 =:= _5 ]
  implicitly[ _2 |*| _3 =:= _6 ]
  //implicitly[ _0#CoMinus[_3] =:= _3 ]

  //implicitly[ (_2#Plus[_1]) =:= _3 ]
  //implicitly[ (_1#Plus[_2]) =:= _3 ]
}