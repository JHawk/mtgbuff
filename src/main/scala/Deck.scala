class Deck(cards: Array[Card]) {
  def count()= cards.length

  override def toString: String =
    cards.map(_.toString).mkString("\n")
}
