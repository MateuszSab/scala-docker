case class HarvestAnalysis(path: String) {
  val data = io.Source.fromFile(path)
  val rows = data.getLines.foldLeft(Seq.empty[Seq[String]]) { (result, line) =>
    result :+ line.split(",").toSeq
  }.tail

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

  def selectName(name: String, data: Seq[Seq[String]]): Seq[Seq[String]] = {
    data.foldLeft(Seq.empty[Seq[String]]) { (result, seq) =>
      if (seq.head == name) result :+ seq
      else result
    }
  }

  def maxAmount(data: Seq[Seq[String]]): Seq[String] = {
    data.foldLeft(data.head) { (result, seq) =>
      if (seq.last.toDouble > result.last.toDouble) seq
      else result
    }
  }

  def selectMonth(monthNumber: String): Seq[Seq[String]] = {
    rows.foldLeft(Seq.empty[Seq[String]]) { (result, seq) =>
      if (seq(1).contains(monthNumber)) result :+ seq
      else result
    }
  }

  def sumAmounts(data: Seq[Seq[String]]): Double = {
    data.foldLeft(data.head.last.toDouble) { (result, seq) =>
      result + seq.last.toDouble
    }
  }

  def bestInMonth(month: String): Seq[String] = {
    val selectedMon = selectMonth(month)
    val namesAndAmounts = listOfNames.map(name => Seq(name, sumAmounts(selectName(name, selectedMon)).toString))
    maxAmount(namesAndAmounts)
  }

  def bestByFruit(fruit: String): Seq[String] = {
    val selectedFruit = selectFruits(fruit)
    val namesAndAmountsForFruits = listOfNames.map(name => Seq(name, sumAmounts(selectName(name, selectedFruit)).toString))
    maxAmount(namesAndAmountsForFruits)
  }


  def selectFruits(fruit: String): Seq[Seq[String]] = {
    rows.foldLeft(Seq.empty[Seq[String]]) { (result, seq) =>
      if (seq(2).contains(fruit)) result :+ seq
      else result
    }
  }
}
