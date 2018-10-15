import org.scalatest._

class InputParserTests extends FlatSpec {

  behavior of "toCards"

  it should "parse an empty card line" in {
    val line = ""

    assert(InputParser.toCards(line).length == 0)
  }

  it should "parse a card from a line containing only the count and name" in {
    val line = "1x Rakdos Guildgate"
    val card = InputParser.toCards(line).head

    assert(card.name == "Rakdos Guildgate")
  }

  it should "parse a card from a line containing the count, name and edition" in {
    val line = "1x New Blood (C17)"
    val card = InputParser.toCards(line).head

    assert(card.name == "New Blood")
    assert(card.edition == Some("(C17)"))
  }

  it should "parse a card from a line containing the count, name, edition and display info" in {
    val line = "1x Olivia Voldaren (ISD) *CMDR*"
    val card = InputParser.toCards(line).head

    assert(card.name == "Olivia Voldaren")
    assert(card.edition == Some("(ISD)"))
  }

  behavior of "parse"

  it should "parse cards from a file" in {
    val path = "./src/test/data/test-deck1"
    val cards = InputParser.parse(path)

    assert(cards.length == 100)
  }
}
