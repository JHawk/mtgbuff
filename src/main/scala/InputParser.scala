import scala.io.Source

object InputParser {
  private case class ParsedLine(count: Int = 0, card: Option[Card] = None)

  val tappedOutLineMatcherWithDisplayInfo = raw"""(\d+)+x (.+?(?=\()+)+(\(.+\)) (.*)""".r
  val tappedOutLineMatcherWithExpansion = raw"""(\d+)+x (.+?(?=\()+)+(\(.+\))""".r
  val tappedOutLineMatcher = raw"""(\d+)+x (.+)""".r

  def toCards(line: String): Seq[Card] = {
    toCard(line) match {
      case ParsedLine(n, Some(card)) => Seq.fill(n)(card)
      case _ => Seq()
    }
  }

  def parse(path: String): Array[Card] = {
    val source = Source.fromFile(path)
    val cards = source.getLines.toArray.flatMap(toCards)
    source.close()
    for (card <- cards) println(card)
    cards
  }

  private def toCard(line: String): ParsedLine = {
    val toCount = (s: String) => s.toInt

    line match {
      case tappedOutLineMatcherWithDisplayInfo(count, name, edition, _) =>
        new ParsedLine(toCount(count), Some(new Card(name, Some(edition))))
      case tappedOutLineMatcherWithExpansion(count, name, edition, _*) =>
        new ParsedLine(toCount(count), Some(new Card(name, Some(edition))))
      case tappedOutLineMatcher(count, name, _*) =>
        new ParsedLine(toCount(count), Some(new Card(name)))
      case _ => new ParsedLine()
    }
  }
}
