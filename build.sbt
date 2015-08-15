name := "scala-playground"

version := "0.0.0"

scalaVersion := "2.11.2"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",//para poder ver feature warnings al compilar
  "-language:postfixOps", //para cosas como '5 seconds'
  "-language:implicitConversions",
  "-language:existentials",
  "-language:higherKinds",
  "-unchecked",
  "-language:reflectiveCalls", // para poder utilizar el .$each de la librería de mongodb
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",        // N.B. doesn't work well with the ??? hole
  //"-Ywarn-numeric-widen",
  //"-Ywarn-value-discard", // No muy buena idea combinar esto con akka
  "-Xfuture"
)

resolvers ++= Seq(
    "Sonatype Releases"   at "http://oss.sonatype.org/content/repositories/releases",
    "Sonatype Snapshots"  at "http://oss.sonatype.org/content/repositories/snapshots",
    "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    "spray repo"          at "http://repo.spray.io"
)

val scalazV = "7.1.0"
val sprayV = "1.3.1"
val akkaV = "2.3.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka"   %%  "akka-actor"                    % akkaV,
  "com.typesafe.akka"   %%  "akka-persistence-experimental" % akkaV,
  //"org.iq80.leveldb"            % "leveldb"          % "0.7",
  "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8",
  "org.scalaz"          %%  "scalaz-core"                   % scalazV,
  "com.nicta"			%%	"rng"							% "1.3.0",
  "org.mockito"         %   "mockito-core"                  % "1.10.8"  % "test",
  "com.typesafe.akka"   %%  "akka-testkit"                  % akkaV     % "test",
  "junit"               %   "junit"                         % "4.10"    % "test",
  "org.scalatest"       %%  "scalatest"                     % "2.2.1"   % "test",
  "org.scalacheck"      %%  "scalacheck"                    % "1.11.6"  % "test"
)