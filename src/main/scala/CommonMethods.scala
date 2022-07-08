object CommonMethods {
  def selectMonth(monthNumber: String, data: Seq[Seq[String]]): Seq[Seq[String]] = {
    data.foldLeft(Seq.empty[Seq[String]]) { (result, seq) =>
      if (seq(1).contains(monthNumber)) result :+ seq
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

  def selectName(name: String, data: Seq[Seq[String]]): Seq[Seq[String]] = {
    data.foldLeft(Seq.empty[Seq[String]]) { (result, seq) =>
      if (seq.head == name) result :+ seq
      else result
    }
  }

  def selectFruits(fruit: String, data: Seq[Seq[String]]): Seq[Seq[String]] = {
    data.foldLeft(Seq.empty[Seq[String]]) { (result, seq) =>
      if (seq.head.contains(fruit)) result :+ seq
      else result
    }
  }

  def minAmount(data: Seq[Seq[String]]): Seq[String] = {
    data.foldLeft(data.head) { (result, seq) =>
      if (seq.last.toDouble < result.last.toDouble) seq
      else result
    }
  }
}
