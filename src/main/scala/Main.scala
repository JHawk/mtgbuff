object MtgBuff {
  def main(args: Array[String]): Unit = {
    val deck = InputParser.parseDeck("./src/test/data/test-deck1")
    println(deck.toString)
  }
}