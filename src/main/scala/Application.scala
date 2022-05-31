object Application extends App {

  if (args.length < 1) {
    println("ERROR: lack of argument")
    sys.exit(-1)
  }
  val path = args(0)

  if (args.length < 2) {
    println("ERROR: lack of argument")
    sys.exit(-1)
  }

  val path1 = args(1)

  println(s"loading files from: $path")
  println(s"loading files from: $path1")

  val harvest = io.Source.fromFile(path)
  for (line <- harvest.getLines) {
    val cols = line.split(",").map(_.trim)
    println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}")

    }
    harvest.close

  val prices = io.Source.fromFile(path1)
  for (line <- prices.getLines) {
    val cols = line.split(",").map(_.trim)
        println(s"${cols(0)}|${cols(1)}|${cols(2)}")
  }
  prices.close

  }







