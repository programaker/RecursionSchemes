package recursionschemes

package object v2 {
  import ExprF._

  /*
   * The v2 evaluators do not contain the recursion logic anymore. Their only concern is
   * the evaluation of the expression, according to the desired result type A in ExprF[A]
   */

  def evalToInt(expr: ExprF[Int], vars: Map[String, Int]): Int = expr match {
    case ConstantF(value) => value
    case VariableF(name)  => vars.getOrElse(name, 0)
    case TimesF(l, r)     => l * r
    case PlusF(l, r)      => l + r
  }

  def evalToString(expr: ExprF[String]): String = expr match {
    case ConstantF(value) => s"$value"
    case VariableF(name)  => name
    case TimesF(l, r)     => s"$l * $r"
    case PlusF(l, r)      => s"$l + $r"
  }

  /*
   * When we have a combination of a type `A` and an evaluator for a functor `F[A]`,
   * we have an Algebra! Our `A`s are Int and String; The Functor is `ExprF[A]` and the
   * evaluators are the `evalToXXX` functions with type `F[A] => A`
   *
   * The type A is called "Carrier" of the algebra and the evaluator "Structure Map"
   * We could express the evaluators in terms of Algebra as follows:
   */

  type Algebra[F[_], A] = F[A] => A
  def evalToIntAlg(vars: Map[String, Int]): Algebra[ExprF, Int] = evalToInt(_, vars)
  def evalToStringAlg: Algebra[ExprF, String] = evalToString

  /*
   * Now comes the recursion part, with the help of our v2.Fix[F[_]] type!
   * To accomplish that we need:
   *
   * 1) Smart constructors to put ExprF in the context of Fix.
   * They will help to build expressions of type Fix[ExprF] easily
   */

  type FixExpr = Fix[ExprF]
  def variable(name: String): FixExpr = Fix(VariableF(name))
  def constant(value: Int): FixExpr = Fix(ConstantF(value))
  def times(l: FixExpr, r: FixExpr): FixExpr = Fix(TimesF(l, r))
  def plus(l: FixExpr, r: FixExpr): FixExpr = Fix(PlusF(l, r))

  /*
   * 2) Catamorfism
   *
   * A Catamorfism is similar to a "fold" for a Fix recursive structure.
   * Given a functor F, a Fix[F[_]] and an Algebra[F[_], A], it will produce an A.
   *
   * More precisely, a Catamorfism "cata" is defined as the function (via composition, as in the post):
   * `cata = algebra ∘ F(cata) ∘ Fix.out` //note the recursion in F(cata)!
   *
   * Or, more closely to actual Scala code:
   * `cata = algebra compose F.map(cata) compose Fix.out`
   *
   * Or:
   * `cata = Fix.out andThen F.map(cata) andThen algebra`
   *
   * Or, just applying the functions:
   * `cata = algebra(Fix.out.map(cata))`
   */

  import cats._
  import cats.implicits._

  def cata[F[_]: Functor, A](expr: Fix[F], alg: Algebra[F, A]): A =
    alg(Fix.out(expr).map(cata(_, alg)))
}
