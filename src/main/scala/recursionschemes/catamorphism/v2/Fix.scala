package recursionschemes.catamorphism.v2

/*
 * We've removed the recursive logic from the evaluators,
 * now we need to implement it somewhere else.
 *
 * The Fix[F[_]] type below will help with this! It represents the
 * recursive part of v1.Expr (Fix[ExprF] is equivalent to v1.Expr)
 *
 * Fix models a concept called "Fixed Point" => `F[Fix] = Fix` (Fix is a Fixed Point of F).
 * This means that the action of F on Fix gives us back the same Fix
 * The functions `in` and `out` model this equivalence - one is the inverse of the other (isomorphism)
 */

final case class Fix[F[_]](unfix: F[Fix[F]]) extends Product with Serializable

object Fix {
  def in[F[_]](ff: F[Fix[F]]): Fix[F] = Fix[F](ff)
  def out[F[_]](f: Fix[F]): F[Fix[F]] = f.unfix
}
