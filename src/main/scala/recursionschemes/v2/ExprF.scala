package recursionschemes.v2

import cats.Functor

/*
 * Expression v2 is parameterized by the desired result type `A`.
 * It is now a Functor too, and this will be important for the recursion part!
 *
 * The "node" expressions do not contain entire Expressions as children anymore,
 * only their final results `A`.
 *
 * With this, we can remove the recursion logic from the eval functions
 * and let them focus only on the evaluation itself.
 */

sealed abstract class ExprF[A] extends Product with Serializable

object ExprF {
  final case class ConstantF[A](value: Int) extends ExprF[A]
  final case class VariableF[A](name: String) extends ExprF[A]
  final case class TimesF[A](left: A, right: A) extends ExprF[A]
  final case class PlusF[A](left: A, right: A) extends ExprF[A]

  //Functor instance for ExprF in the companion object,
  //so that we don't need to import the implicit
  implicit val ExprFunctor: Functor[ExprF] = new Functor[ExprF] {
    override def map[A, B](fa: ExprF[A])(f: A => B): ExprF[B] = fa match {
      case ConstantF(d) => ConstantF(d)
      case VariableF(a) => VariableF(a)
      case PlusF(l, r)  => PlusF(f(l), f(r))
      case TimesF(l, r) => TimesF(f(l), f(r))
    }
  }
}
