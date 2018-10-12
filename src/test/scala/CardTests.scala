import org.scalatest.FunSpec
import org.scalatest.Matchers._

class CardTests extends FunSpec {
  val name = "Quietus Spike"
  val edition = "(C17)"

  describe("toString") {

    describe("edition is missing") {
      val card = new Card(name)

      it("contains the name") {
        card.toString should include(name)
      }

      it("does not contain the edition") {
        card.toString should not include(edition)
      }
    }

    describe("edition is present") {
      val card = new Card(name, Some(edition))

      it("contains the name") {
        card.toString should include(name)
      }

      it("contains the edition") {
        card.toString should include(edition)
      }
    }

  }

}
