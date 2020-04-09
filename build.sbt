name := "RecursionSchemes"
version := "1.0"
scalaVersion := "2.13.1"

val betterMonadicForV = "0.3.1"
val kindProjectorV = "0.10.3"
val catsV = "2.1.1"

val compilerPlugins = Seq(
  "com.olegpy" %% "better-monadic-for" % betterMonadicForV,
  "org.typelevel" %% "kind-projector" % kindProjectorV
)

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsV
)

scalacOptions ++= Seq(
  "-encoding", "utf8",
  "-feature",
  "-explaintypes",
  "-deprecation",

  "-language:experimental.macros",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",

  "-Ywarn-dead-code",
  "-Ywarn-value-discard",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:implicits",
  "-Ywarn-unused:locals",
  "-Ywarn-unused:params",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates"
)

wartremoverWarnings ++= Warts.allBut(
  Wart.Recursion,
  Wart.Nothing,
  Wart.ImplicitParameter,
  Wart.Any,
  Wart.StringPlusAny
)

compilerPlugins.map(addCompilerPlugin)
