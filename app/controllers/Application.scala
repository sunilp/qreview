package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import views._

import models._

object Application extends Controller {

	val signupForm :Form[User] = Form(
		mapping("username" -> nonEmptyText,"password" -> text, "name" -> text,"designation" -> text)
		(User.apply)(User.unapply)
		)

  def index = Action {

  	val filledForm = User(System.getProperty("user.name"),"","","")
    Ok(views.html.index(signupForm.fill(filledForm)))
  }
  
}