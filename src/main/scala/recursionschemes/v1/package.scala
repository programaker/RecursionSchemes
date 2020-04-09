package recursionschemes

package object v1 {
  import Expr._

  /*
   * These are 2 different evaluators for the expression Expr.
   *
   * The problem is that the recursive logic is mixed with the evaluation logic,
   * making the functions repetitive
   */

  def evalToDouble(expr: Expr, vars: Map[String, Double]): Double = expr match {
    case Const(value) => value
    case Var(name)    => vars.getOrElse(name, 0.0)
    case Times(l, r)  => evalToDouble(l, vars) * evalToDouble(r, vars)
    case Plus(l, r)   => evalToDouble(l, vars) + evalToDouble(r, vars)
  }

  def evalToString(expr: Expr): String = expr match {
    case Const(value) => s"$value"
    case Var(name)    => s"$name"
    case Times(l, r)  => s"${evalToString(l)} * ${evalToString(r)}"
    case Plus(l, r)   => s"${evalToString(l)} + ${evalToString(r)}"
  }

}
