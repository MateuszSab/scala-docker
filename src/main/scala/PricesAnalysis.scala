case class PricesAnalysis(path: String) {
  val data = io.Source.fromFile(path)
  val rows = data.getLines.foldLeft(Seq.empty[Seq[String]]) { (result, line) =>
    result :+ line.split(",").toSeq
  }.tail

  // VALUES
  val listOfPrices = rows.foldLeft(Seq.empty[String]) {(result, seq) =>
    result :+ seq(2)
  }.distinct
  val listOfMonths = rows.foldLeft(Seq.empty[String]) {(result, seq) =>
    result :+ seq(1).take(7)
  }.distinct
  val listOfFruits = rows.foldLeft(Seq.empty[String]) {(result, seq) =>
    result :+ seq.head
  }.distinct

  val fruitsPricesOverall = listOfFruits.map(fruit => Seq(fruit, sumAmounts(selectFruits(fruit, rows)).toString))


  // METHODS
  def selectFruits(fruit: String, data: Seq[Seq[String]]): Seq[Seq[String]] = {
    data.foldLeft(Seq.empty[Seq[String]]) { (result, seq) =>
      if (seq.head.contains(fruit)) result :+ seq
      else result
    }
  }
  def sumAmounts(data: Seq[Seq[String]]): Double = {
    data.foldLeft(data.head.last.toDouble) { (result, seq) =>
      result + seq.last.toDouble
    }
  }
  def maxAmount(data: Seq[Seq[String]]): Seq[String] = {
    data.foldLeft(data.head) { (result, seq) =>
      if (seq.last.toDouble > result.last.toDouble) seq
      else result
    }
  }
  def minAmount(data: Seq[Seq[String]]): Seq[String] = {
    data.foldLeft(data.head) { (result, seq) =>
      if (seq.last.toDouble < result.last.toDouble) seq
      else result
    }
  }
  def selectMonth(monthNumber: String): Seq[Seq[String]] = {
    rows.foldLeft(Seq.empty[Seq[String]]) { (result, seq) =>
      if (seq(1).contains(monthNumber)) result :+ seq
      else result
    }
  }
  def bestFruitInMonth(month: String): Seq[String] = {
    val selectedMon = selectMonth(month)
    val namesAndAmounts = listOfFruits.map(fruit => Seq(fruit, sumAmounts(selectFruits(fruit, selectedMon)).toString))
    maxAmount(namesAndAmounts)
  }
  def worstFruitInMonth(month: String): Seq[String] = {
    val selectedMon = selectMonth(month)
    val namesAndAmounts = listOfFruits.map(fruit => Seq(fruit, sumAmounts(selectFruits(fruit, selectedMon)).toString))
    minAmount(namesAndAmounts)
  }
}
