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
    // @see https://github.com/scala/bug/issues/11110
    // "-Yno-adapted-args" // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
  ),
  scalacOptions in (Compile, console) ~= (_.filterNot(Set("-Xlint", "-Ywarn-unused:imports"))),
  testOptions in Test += Tests.Argument("-oD"),
  fork in Test := true,
  wartremoverErrors in (Compile, compile) := Warts.unsafe
    .filterNot(Set(Wart.Any)) ++ Seq(
    Wart.ExplicitImplicitTypes,
    Wart.FinalCaseClass,
    Wart.FinalVal,
    Wart.LeakingSealed,
    Wart.While
  ),
  // scaladoc: Create inheritance diagrams for classes, traits and packages.
  scalacOptions in (Compile, doc) := Seq("-diagrams"),

  /**
   * @see https://github.com/sbt/sbt-assembly#merge-strategy
   */
  assemblyMergeStrategy in assembly := {
    case PathList(ps @ _*) if ps.last endsWith ".properties" => MergeStrategy.first
    case PathList(ps @ _*) if ps.last endsWith ".class" => MergeStrategy.first
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  },
  test in assembly := {},
)

/**
 * Core Settings
 */
lazy val coreSettings = Seq()

/**
 * test
 */
addCommandAlias("testFast", ";testOnly -- -l org.scalatest.tags.Network")
addCommandAlias("testNetwork", ";testOnly -- -n org.scalatest.tags.Network")
addCommandAlias("t", "testFast")

/**
 * scalafmt
 */
addCommandAlias("fmt", ";scalafmt ;test:scalafmt")
scalafmtVersion in ThisBuild := "1.5.1"
scalafmtTestOnCompile in ThisBuild := true
scalafmtShowDiff in (ThisBuild, scalafmt) := true
