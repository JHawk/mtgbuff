object InputParser extends Usable {
  private case class ParsedLine(count: Int = 0, card: Option[Card] = None)

  val tappedOutLineMatcherWithDisplayInfo = raw"""(\d+)+x (.+?(?=\()+)+(\(.+\)) (.*)""".r
  val tappedOutLineMatcherWithExpansion = raw"""(\d+)+x (.+?(?=\()+)+(\(.+\))""".r
  val tappedOutLineMatcher = raw"""(\d+)+x (.+)""".r

  def toCards(line: String): Seq[Card] = {
    parseLine(line) match {
      case ParsedLine(n, Some(card)) => Seq.fill(n)(card)
      case _ => Seq()
    }
  }

  def parseDeck(path: String): Deck = {
    val cards = using(scala.io.Source.fromFile(path)) { source => {
      source.getLines.toArray.flatMap(toCards)
    }}
    new Deck(cards)
  }

  private def parseLine(line: String): ParsedLine = {
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
