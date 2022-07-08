case class JoinedAnalysis(path1: String, path2: String) {
  val harvest = io.Source.fromFile(path1)
  val prices = io.Source.fromFile(path2)
  val harvestRows = harvest.getLines.foldLeft(Seq.empty[Seq[String]]) { (result, line) =>
    result :+ line.split(",").toSeq
  }.tail
  val pricesRows = prices.getLines.foldLeft(Seq.empty[Seq[String]]) { (result, line) =>
    result :+ line.split(",").toSeq
  }.tail

  import CommonMethods._

  // VALUES
  val listOfPrices = harvestRows.foldLeft(Seq.empty[String]) { (result, seq) =>
    result :+ seq(2)
  }.distinct
  val listOfMonths = harvestRows.foldLeft(Seq.empty[String]) { (result, seq) =>
    result :+ seq(1).take(7)
  }.distinct
  val listOfFruits = harvestRows.foldLeft(Seq.empty[String]) { (result, seq) =>
    result :+ seq.head
  }.distinct
  val listOfNames = harvestRows.foldLeft(Seq.empty[String]) { (result, seq) =>
    result :+ seq.head
  }.distinct
  val dataWithProfit = countProfit(harvestRows)
  val profitByNameOverall = listOfNames.map(name => Seq(name, sumAmounts(selectName(name, dataWithProfit)).toString))



  // METHODS


  def countProfit(harvest: Seq[Seq[String]]): Seq[Seq[String]] = {
    harvest.map(harv => Seq(harv.head, harv(1), harv(2), (harv.last.toDouble * selectPriceByRowByDateAndFruit(harv(1), harv(2))).toString))
  }

  def selectPriceByRowByDateAndFruit(date: String, fruit: String): Double = {
    pricesRows.foldLeft(0.0) { (result, seq) =>
      if (seq.head == fruit && seq(1) == date) seq.last.toDouble
      else result
    }
  }


  def mostProfitableWorkerInMonth(month: String): Seq[String] = {
    val selectedMon = selectMonth(month, dataWithProfit)
    val namesAndAmounts = listOfNames.map(name => Seq(name, sumAmounts(selectName(name, selectedMon)).toString))
    maxAmount(namesAndAmounts)
  }
}
