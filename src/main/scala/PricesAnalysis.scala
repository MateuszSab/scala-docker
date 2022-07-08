case class PricesAnalysis(path: String) {
  val data = io.Source.fromFile(path)
  val rows = data.getLines.foldLeft(Seq.empty[Seq[String]]) { (result, line) =>
    result :+ line.split(",").toSeq
  }.tail

  import CommonMethods._


  // VALUES
  val listOfPrices = rows.foldLeft(Seq.empty[String]) { (result, seq) =>
    result :+ seq(2)
  }.distinct
  val listOfMonths = rows.foldLeft(Seq.empty[String]) { (result, seq) =>
    result :+ seq(1).take(7)
  }.distinct
  val listOfFruits = rows.foldLeft(Seq.empty[String]) { (result, seq) =>
    result :+ seq.head
  }.distinct

  val fruitsPricesOverall = listOfFruits.map(fruit => Seq(fruit, sumAmounts(selectFruits(fruit, rows)).toString))


  // METHODS

  def bestFruitInMonth(month: String): Seq[String] = {
    val selectedMon = selectMonth(month, rows)
    val namesAndAmounts = listOfFruits.map(fruit => Seq(fruit, sumAmounts(selectFruits(fruit, selectedMon)).toString))
    maxAmount(namesAndAmounts)
  }

  def worstFruitInMonth(month: String): Seq[String] = {
    val selectedMon = selectMonth(month, rows)
    val namesAndAmounts = listOfFruits.map(fruit => Seq(fruit, sumAmounts(selectFruits(fruit, selectedMon)).toString))
    minAmount(namesAndAmounts)
  }
}
