import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.{Materializer, ActorMaterializer}

import scala.concurrent.Future
import scala.util.{Failure, Success}

class Card(var name: String, var edition: Option[String] = None) extends Usable {
  name = name.trim
  edition = edition match {
    case Some(e) => Some(e.trim)
    case None => None
  }

  val cardsUrl = "https://api.magicthegathering.io/v1/cards"

  override def toString: String =
    {
      edition match {
        case Some(e) => s"$name $e"
        case None => name
      }
    }

  def cmc: Int = {
    val query = Uri.Query(("name", name))
    val uri = Uri(cardsUrl)
    val withQuery = uri.withQuery(query)

    implicit val system = ActorSystem()
    implicit val executionContext = system.dispatcher

    val responseFuture = Http().singleRequest(HttpRequest(uri = withQuery.toString()))
    implicit val materializer = ActorMaterializer()

    responseFuture
      .onComplete {
        case Success(res) => {
          println(Unmarshal(res.entity).to[String])
        }
        case Failure(asf) => println(asf)
      }
    1
  }
}
