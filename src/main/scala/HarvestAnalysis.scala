case class HarvestAnalysis(path: String) {
  val data = io.Source.fromFile(path)
  val rows = data.getLines.foldLeft(Seq.empty[Seq[String]]) { (result, line) =>
    result :+ line.split(",").toSeq
  }.tail

  import CommonMethods._

  // VALUES
  val listOfMonths = rows.foldLeft(Seq.empty[String]) { (result, seq) =>
    result :+ seq(1).take(7)
  }.distinct

  val listOfNames = rows.foldLeft(Seq.empty[String]) { (result, seq) =>
    result :+ seq.head
  }.distinct

  val listOfFruits = rows.foldLeft(Seq.empty[String]) { (result, seq) =>
    result :+ seq(2)
  }.distinct

  // METHODS

  def selectFruitsFromHarvest(fruit: String, data: Seq[Seq[String]]): Seq[Seq[String]] = {
    data.foldLeft(Seq.empty[Seq[String]]) { (result, seq) =>
      if (seq(2).contains(fruit)) result :+ seq
      else result
    }
  }


  def bestInMonth(month: String): Seq[String] = {
    val selectedMon = selectMonth(month, rows)
    val namesAndAmounts = listOfNames.map(name => Seq(name, sumAmounts(selectName(name, selectedMon)).toString))
    maxAmount(namesAndAmounts)
  }

  def bestByFruit(fruit: String): Seq[String] = {
    val selectedFruit = selectFruitsFromHarvest(fruit, rows)
    val namesAndAmountsForFruits = listOfNames.map(name => Seq(name, sumAmounts(selectName(name, selectedFruit)).toString))
    maxAmount(namesAndAmountsForFruits)
  }
}
