object Application extends App {

  if (args.length < 0) {
    println("ERROR: lack of argument")
    sys.exit(-1)
  }
  val path = args(0)  // C:\scala-projects\scala-docker\src\main\resources\harvest.csv

  if (args.length < 1) {
    println("ERROR: lack of argument")
    sys.exit(-1)
  }

  val path1 = args(1)  // C:\\scala-projects\\scala-docker\\src\\main\\resources\\prices.csv

  val ha = HarvestAnalysis(path)

  // BEST WORKER

  // Best worker in each month
  val bestIEachMonth = ha.listOfMonths.map(month => ha.bestInMonth(month).dropRight(1) :+ month.mkString)
  println("List of best gatherers in each month: " + bestIEachMonth)

  // Best worker in fruit group
  val bestByEachFruit = ha.listOfFruits.map(fruit => ha.bestByFruit(fruit).dropRight(1) :+ fruit.mkString)
  println("List of best gatherers by type of fruit" + bestByEachFruit)

  // BEST FRUITS

  val pa = PricesAnalysis(path1)

  // best earning fruit in whole year
  val bestFruitOverall = pa.maxAmount(pa.fruitsPricesOverall).dropRight(1)
  println("Most profitable fruit in a whole year: " + bestFruitOverall.head)

  // best earning fruit in each month
  val bestFruitByMonth = pa.listOfMonths.map(month => pa.bestFruitInMonth(month).dropRight(1) :+ month)
  println("List of most profitable fruit in each month: " + bestFruitByMonth)

  // worst earning fruit in whole year
  val worstFruitOverall = pa.minAmount(pa.fruitsPricesOverall).dropRight(1)
  println("Least profitable fruit in a whole year: " + worstFruitOverall.head)

  // worst earning fruit in each month
  val worstFruitByMonth = pa.listOfMonths.map(month => pa.worstFruitInMonth(month).dropRight(1) :+ month)
  println("List of least profitable fruit in each month: " + worstFruitByMonth)
}







