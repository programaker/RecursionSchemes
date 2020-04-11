package recursionschemes.catamorphism.v2

object MainV2 {
  def main(args: Array[String]): Unit = {
    // x² + 3x + 4 (x² = x * x)
    // using the smart constructors here to build the expression
    val expr = plus(times(variable("x"), variable("x")), plus(times(constant(3), variable("x")), constant(4)))

    val vars = Map("x" -> 2)

    println(cata(expr, evalToInt(_: ExprF[Int], vars))) //type inference let me down here =(
    //or with the explicit Algebra
    println(cata(expr, evalToIntAlg(vars)))

    println(cata(expr, evalToString))
    //or with the explicit Algebra
    println(cata(expr, evalToStringAlg))
  }
}
