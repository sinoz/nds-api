name := "minimal-java"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.google.guava"  % "guava"           % "19.0",
  "io.netty"          % "netty-all"       % "4.1.5.Final",
  "junit"             % "junit"           % "4.12"  % "test",
  "com.novocode"      % "junit-interface" % "0.11"  % "test",
  "org.scalatest"     % "scalatest_2.11"  % "3.0.0" % "test"
)
