class Deck(val cards: Array[Card]) {
  def count()= cards.length
  def head() = cards.head

  override def toString: String =
    cards.map(_.toString).mkString("\n")

  // 1 to 10 competitive

  // 1-2  Jank - Vorthos (chair / food / bear theme deck) 17
  // 3-4  Casual 14
  // 5-6  Focused 10
  // 7-8  Optimized 8
  // 9-10 Competitive 5

}
