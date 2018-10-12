import org.scalatest._

class InputParserTests extends FlatSpec {

  behavior of "toCard"
  it should "parse a card from a line containing only the count and name" in {
    val line = "1x Rakdos Guildgate"
    val card = InputParser.toCard(line)
    assert(card.name == "Rakdos Guildgate")
  }

}
