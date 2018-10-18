name := "mtgbuff"

version := "1.0.0"

scalaVersion := "2.11.0"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "3.0.5" % "test",
  "com.typesafe.akka" %% "akka-http" % "10.1.5",
  "com.typesafe.akka" %% "akka-stream" % "2.5.12",
  "org.apache.spark" %% "spark-core" % "2.2.0",
  "org.apache.spark" %% "spark-streaming" % "2.2.0",
  "org.apache.spark" %% "spark-sql" % "2.2.0",
  "com.couchbase.client" %% "spark-connector" % "2.2.0",
  "org.apache.spark" %% "spark-mllib" % "2.2.0",
  "org.json4s" %% "json4s-native" % "3.6.1"
//  "mysql" % "mysql-connector-java" % "5.1.37"
)