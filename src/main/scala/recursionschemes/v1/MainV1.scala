package recursionschemes.v1

object MainV1 {
  import Expr._

  def main(args: Array[String]): Unit = {
    // x² + 3x + 4 (where x² = x * x)
    val expr =
      Plus(Times(Var("x"), Var("x")), Plus(Times(Const(3.0), Var("x")), Const(4.0)))

    println(evalToDouble(expr, Map("x" -> 2.0)))
    println(evalToString(expr))
  }
}
