ThisBuild / scalaVersion := "2.12.17"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """mockingexample""",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "org.mockito" % "mockito-core" % "4.8.1" % Test,
      "org.scalatestplus" %% "mockito-3-4" % "3.2.9.0" % Test
    )
  )