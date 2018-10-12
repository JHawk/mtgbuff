class Card(var name: String, var edition: Option[String] = None) {
  name = name.trim
  edition = edition match {
    case Some(e) => Some(e.trim)
    case None => None
  }

  override def toString: String =
    {
      edition match {
        case Some(e) => s"$name $e"
        case None => name
      }
    }
}
