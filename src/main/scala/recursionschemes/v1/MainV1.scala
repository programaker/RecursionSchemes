package recursionschemes.v1

object MainV1 {
  import Expr._

  def main(args: Array[String]): Unit = {
    // x² + 3x + 4 (x² = x * x)
    val expr = Plus(Times(Variable("x"), Variable("x")), Plus(Times(Constant(3), Variable("x")), Constant(4)))

    println(evalToInt(expr, Map("x" -> 2)))
    println(evalToString(expr))
  }
}
