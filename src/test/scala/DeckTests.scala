import org.scalatest.FunSpec
import org.scalatest.Matchers._

class DeckTests extends FunSpec {
  val card = new Card("Mountain", Some("(MRD)"))
  val cards = Array.fill(100)(card)
  val deckOfMountains = new Deck(cards)

  describe("count") {
    it("returns the card count") {
      deckOfMountains.count should equal(100)
    }
  }

  describe("toString") {
    it("returns string with card names") {
      deckOfMountains.toString should include(card.name)
    }
    it("returns string with card editions") {
      deckOfMountains.toString should include(card.edition.get)
    }
  }
}
