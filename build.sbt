ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "mateusz"
ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "scala-docker"
  )

enablePlugins(DockerPlugin)

docker / dockerfile := {
  val jarFile: File = (Compile / packageBin / sbt.Keys.`package`).value
  val classpath = (Compile / managedClasspath).value
  val mainclass = (Compile / packageBin / mainClass).value.getOrElse(sys.error("Expected exactly one main class"))
  val jarTarget = s"/app/${jarFile.getName}"
  val classpathString = classpath.files.map("/app/" + _.getName)
    .mkString(":") + ":" + jarTarget
  ImageName(s"${organization.value}/${name.value}:latest")
  new Dockerfile {
    from("openjdk:11-jre")
    add(classpath.files, "/app/")
    add(jarFile, jarTarget)
    entryPoint("java", "-cp", classpathString, mainclass)
  }
}
docker / imageNames := Seq(
  // Sets the latest tag
  ImageName(s"${organization.value}/${name.value}:${version.value}")
)