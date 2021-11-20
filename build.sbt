val scala2Version = "2.13.6"
val zioVersion = "2.0.0-M2"
val zioTestIntellijVersion = "1.0.7"

lazy val hello =
  project
    .in(file("hello"))
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        "dev.zio" %% "zio" % zioVersion,
        "dev.zio" %% "zio-test" % zioVersion % Test,
        "dev.zio" %% "zio-test-sbt" % zioVersion % Test,
        //In zio-test-intellij absence, you may get no logs on some failing tests when running tests with intellij
        "dev.zio" %% "zio-test-intellij"  % zioTestIntellijVersion % Test
      ),
      testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
    )

lazy val settings =
  commonSettings ++
    commandAliases

lazy val commonSettings =
  Seq(
    name := "helsinki",
    scalaVersion := scala2Version,
    organization := "com.example"
  )

lazy val commandAliases =
  addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt") ++
    addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")

lazy val stdOptions = Seq(
  "-encoding",
  "UTF-8",
  "-explaintypes",
  "-Yrangepos",
  "-feature",
  "-language:higherKinds",
  "-language:existentials",
  "-Xlint:_,-type-parameter-shadow,-byname-implicit",
  "-Xsource:2.13",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-unchecked",
  "-deprecation",
  "-Xfatal-warnings"
)

lazy val stdOpts213 = Seq(
  "-Wunused:imports",
  "-Wvalue-discard",
  "-Wunused:patvars",
  "-Wunused:privates",
  "-Wunused:params"
)

scalacOptions := stdOptions ++ stdOpts213
