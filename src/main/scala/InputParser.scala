import scala.io.Source

object InputParser {
  val tappedOutLineMatcherWithDisplayInfo = raw"""(\d+x)+ (.+?(?=\()+)+(\(.+\)) (.*)""".r
  val tappedOutLineMatcherWithExpansion = raw"""(\d+x)+ (.+?(?=\()+)+(\(.+\))""".r
  val tappedOutLineMatcher = raw"""(\d+x)+ (.+)""".r

  def toCard(line: String): Card = {
    line match {
      case tappedOutLineMatcherWithDisplayInfo(_, name, edition, _) => new Card(name, Some(edition))
      case tappedOutLineMatcherWithExpansion(_, name, edition, _*) => new Card(name, Some(edition))
      case tappedOutLineMatcher(_, name, _*) => new Card(name)
      case _ => new Card("missing")
    }
  }

  def parse(path: String): Array[Card] = {
    val source = Source.fromFile(path)
    val cards = source.getLines.toArray.map(toCard)
    source.close()
    for (card <- cards) println(card)
    cards
  }
}
