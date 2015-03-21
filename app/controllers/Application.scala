package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def matcher(jsValue: JsValue): Option[Any] = {
    jsValue match {
      case jsObject: JsObject => Some(jsObject)
      case JsBoolean(boolean) => Some(boolean)
      case JsNumber(number)   => Some(number)
      case JsArray(values)    => Some(values map (matcher _))
      case JsString(string)   => Some(string)
      case JsUndefined(error) => Some("error: " + error)
      case JsNull             => None
    }
  }
}