logLevel := Level.Warn

addSbtPlugin("org.wartremover" % "sbt-wartremover" % "2.4.2")
addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.16")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10")

/**
  * Usage
  * sbt> dependencyUpdates
  *
  */
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.4.2")

/**
  * Usage
  * sbt> undeclaredCompileDependencies
  *
  */
addSbtPlugin("com.github.cb372" % "sbt-explicit-dependencies" % "0.2.9")

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
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.2")
