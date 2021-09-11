lazy val root = (project in file("."))
  .settings(
    name := "$name$",
    version := "$version$"
  )
  .aggregate(core)

lazy val core = (project in file("./modules/core"))
  .settings(
    name := "core",
  )
  .settings(commonSettings)
  .settings(coreSettings)

/**
 * Common Settings
 */
lazy val commonSettings = Seq(
  scalaVersion := Versions.Scala,
  semanticdbEnabled := true, // enable scalafix SemanticDB
  semanticdbVersion := scalafixSemanticdb.revision,
  scalacOptions ++= Seq(
    /**
      * @see https://docs.scala-lang.org/overviews/compiler-options/index.html
      */
    "-encoding",
    "utf-8", // Specify character encoding used by source files.
    "-Ymacro-annotations",
    "-deprecation", // Emit warning and location for usages of deprecated APIs.
    "-unchecked", // Enable additional warnings where generated code depends on assumptions.
    "-Xlint",
    "-explaintypes", // Explain type errors in more detail.
    "-language:experimental.macros", // Allow macro definition (besides implementation and application)
    "-language:higherKinds", // Allow higher-kinded types
    "-language:implicitConversions", // Allow definition of implicit functions called views
    "-Xfatal-warnings", // Fail the compilation if there are any warnings.
    "-Ywarn-unused:imports", // Warn when imports are unused.
    "-Ywarn-numeric-widen" // Warn when numerics are widened.
  ),
  Compile /console / scalacOptions ~= (_.filterNot(Set("-Xlint", "-Ywarn-unused:imports"))),
  Test / testOptions += Tests.Argument("-oD"),
  Test / fork := true,
  Compile / compile / wartremoverErrors := Warts.unsafe
    .filterNot(Set(Wart.Any)) ++ Seq(
    Wart.ExplicitImplicitTypes,
    Wart.FinalCaseClass,
    Wart.FinalVal,
    Wart.LeakingSealed,
    Wart.While
  ),
  // scaladoc: Create inheritance diagrams for classes, traits and packages.
  Compile / doc / scalacOptions := Seq("-diagrams"),

  /**
   * @see https://github.com/sbt/sbt-assembly#merge-strategy
   */
  assembly / assemblyMergeStrategy:= {
    case PathList(ps @ _*) if ps.last endsWith ".properties" => MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".class" => MergeStrategy.first
    case x =>
      val oldStrategy = (assembly / assemblyMergeStrategy).value
      oldStrategy(x)
  },
  assembly / test:= {},
)

/**
 * Core Settings
 */
lazy val coreSettings = Seq()
