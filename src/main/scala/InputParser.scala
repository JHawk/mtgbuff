import scala.io.Source

object InputParser {
  def toCard(line: String): Card = {
    val name = line // parse
    new Card(name)
  }

  def parse(path: String): Array[Card] = {
    val source = Source.fromFile(path)
    val cards = source.getLines.toArray.map(toCard)
    source.close()
    for (card <- cards) println(card)
    cards
  }

  parse("./src/test/data/test-deck1")
}
