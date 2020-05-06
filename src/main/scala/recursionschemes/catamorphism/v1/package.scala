package recursionschemes.catamorphism

package object v1 {
  import Expr._

  /*
   * Having expressions built with Expr type, we would like to evaluate them
   * to obtain some value.
   *
   * These are 2 possible evaluators for Expr: one for Int and another to String.
   *
   * The problem is that the recursive logic is mixed with the evaluation logic,
   * making the functions repetitive (any other eval function would have the same "shape")
   */

  def evalToInt(expr: Expr, vars: Map[String, Int]): Int = expr match {
    case Constant(value) => value
    case Variable(name)  => vars.getOrElse(name, 0)
    case Times(l, r)     => evalToInt(l, vars) * evalToInt(r, vars)
    case Plus(l, r)      => evalToInt(l, vars) + evalToInt(r, vars)
  }

  def evalToString(expr: Expr): String = expr match {
    case Constant(value) => s"$value"
    case Variable(name)  => name
    case Times(l, r)     => s"${evalToString(l)} * ${evalToString(r)}"
    case Plus(l, r)      => s"${evalToString(l)} + ${evalToString(r)}"
  }
}
