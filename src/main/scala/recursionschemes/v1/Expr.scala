package recursionschemes.v1

/*
 * Represents an Expression that allows:
 * Constants, Variables, `*` and `+` operations
 */

sealed abstract class Expr extends Product with Serializable
object Expr {
  final case class Const(value: Double) extends Expr
  final case class Var(name: String) extends Expr
  final case class Times(left: Expr, right: Expr) extends Expr
  final case class Plus(left: Expr, right: Expr) extends Expr
}
