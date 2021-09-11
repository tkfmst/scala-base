logLevel := Level.Warn

addSbtPlugin("org.wartremover" % "sbt-wartremover" % "2.4.16")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.3")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "1.1.0")
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.9.30")

/**
  * Usage
  * sbt> dependencyUpdates
  *
  */
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.5.3")

/**
  * Usage
  * sbt> undeclaredCompileDependencies
  *
  */
addSbtPlugin("com.github.cb372" % "sbt-explicit-dependencies" % "0.2.16")

/**
  * Usage
  * sbt> dumpLicenseReport
  */
addSbtPlugin("com.typesafe.sbt" % "sbt-license-report" % "1.2.0")

/* scaladoc */
/**
  * Usage
  * sbt> dependencyTree
  * sbt> dependencyBrowseGraph
  * sbt> dependencyList
  */
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.10.0-RC1")
