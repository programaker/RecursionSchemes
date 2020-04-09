package recursionschemes.v1

/*
 * Represents an Expression that allows:
 * Constants, Variables, `*` and `+` operations
 *
 * Expr is a recursive data structure, as the operations act on
 * other Expr's
 */

sealed abstract class Expr extends Product with Serializable

object Expr {
  final case class Constant(value: Int) extends Expr
  final case class Variable(name: String) extends Expr
  final case class Times(left: Expr, right: Expr) extends Expr
  final case class Plus(left: Expr, right: Expr) extends Expr
}
