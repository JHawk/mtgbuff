import org.scalatest.FunSpec
import org.scalatest.Matchers._

class CardTests extends FunSpec {
  val name = "Quietus Spike"
  val edition = "(C17)"

  describe("name") {

    describe("name includes trailing whitespace") {
      val card = new Card(name + " \n \t")

      it("formats the name") {
        card.name should equal(name)
      }
    }

    describe("name includes leading whitespace") {
      val card = new Card( " \n \t" + name)

      it("formats the name") {
        card.name should equal(name)
      }
    }

  }

  describe("edition") {

    describe("when edition is present") {
      describe("edition includes trailing whitespace") {
        val card = new Card(name, Some(edition + " \n \t"))

        it("formats the edition") {
          card.edition should equal(Some(edition))
        }
      }

      describe("edition includes leading whitespace") {
        val card = new Card(name, Some(" \n \t" + edition))

        it("formats the edition") {
          card.edition should equal(Some(edition))
        }
      }
    }

    describe("when edition is missing") {
      val card = new Card(name, None)

      it("formats the edition") {
        card.edition should equal(None)
      }
    }
  }

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
