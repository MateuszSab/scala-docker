object Application extends App {

  if (args.length < 0) {
    println("ERROR: lack of argument")
    sys.exit(-1)
  }
  val path = args(0)

  if (args.length < 1) {
    println("ERROR: lack of argument")
    sys.exit(-1)
  }

  val path1 = args(1)

  import CommonMethods._


  val ha = HarvestAnalysis(path)

  // BEST WORKER

  // Best worker in each month
  val bestIEachMonth = ha.listOfMonths.map(month => ha.bestInMonth(month).dropRight(1) :+ month.mkString)
  println("List of best gatherers in each month: ")
  bestIEachMonth.map(seq => (seq.last, seq.head)) foreach (println)

  // Best worker in fruit group
  val bestByEachFruit = ha.listOfFruits.map(fruit => ha.bestByFruit(fruit).dropRight(1) :+ fruit.mkString)
  println("List of best gatherers by type of fruit")
  bestByEachFruit.map(seq => (seq.last, seq.head)) foreach (println)

  // BEST FRUITS

  val pa = PricesAnalysis(path1)

  // best earning fruit in whole year
  val bestFruitOverall = maxAmount(pa.fruitsPricesOverall).dropRight(1)
  println("Most profitable fruit in a whole year: " + bestFruitOverall.head)

  // best earning fruit in each month
  val bestFruitByMonth = pa.listOfMonths.map(month => pa.bestFruitInMonth(month).dropRight(1) :+ month)
  println("List of most profitable fruit in each month: ")
  bestFruitByMonth.map(seq => (seq.last, seq.head)) foreach (println)

  // worst earning fruit in whole year
  val worstFruitOverall = minAmount(pa.fruitsPricesOverall).dropRight(1)
  println("Least profitable fruit in a whole year: " + worstFruitOverall.head)

  // worst earning fruit in each month
  val worstFruitByMonth = pa.listOfMonths.map(month => pa.worstFruitInMonth(month).dropRight(1) :+ month)
  println("List of least profitable fruit in each month: ")
  worstFruitByMonth.map(seq => (seq.last, seq.head)) foreach (println)

  // MOST PROFITABLE GATHERERS

  val ja = JoinedAnalysis(path, path1)

  // most profitable gatherer in each month
  val mostProfitableGathererIEachMonth = ja.listOfMonths.map(month => ja.mostProfitableWorkerInMonth(month).dropRight(1) :+ month.mkString)
  println("List of most profitable gatherers in each month: ")
  mostProfitableGathererIEachMonth.map(seq => (seq.last, seq.head)) foreach (println)

  // most profitable gatherer in whole year
  val mostProfitableGathererOverall = maxAmount(ja.profitByNameOverall).dropRight(1)
  println("Most profitable gatherer in a whole year: " + mostProfitableGathererOverall.head)

}







