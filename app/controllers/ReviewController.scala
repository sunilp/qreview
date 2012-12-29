package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import views._

import models._

object ReviewController extends Controller {

	val userForm :Form[User] = Form(
		mapping("username" -> nonEmptyText,"password" -> text, "name" -> text,"designation" -> text)
		(User.apply)(User.unapply)
		)

	val reviewForm :Form[ReviewDetail] = Form(
		mapping("reviewId" -> nonEmptyText,"reviewerId" -> text, "rtype" -> text,"severity" -> text,
			"fileName" -> nonEmptyText,"summery" -> text, "description" -> text,"phase" -> text,
			"assignedTo" -> nonEmptyText,"author" -> text, "creationDate" -> date,"modifier" -> text,"modificationDate" -> date)
		(ReviewDetail.apply)(ReviewDetail.unapply)
		)

  def index = Action {

implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
      		val newReview = ReviewDetail("reeeeeviewId" ,"reviewyyyerId", "rtype","severity",
			"fileName","summery", "description","phase",
			"assignedTo","author", new java.util.Date(),"modifier",new java.util.Date())
      	BadRequest(views.html.reviewForm(reviewForm.fill(newReview)))     },
      value => {
      	val newReview = ReviewDetail("reviewId" ,"reviewerId", "rtype","severity",
			"fileName","summery", "description","phase",
			"assignedTo","author", new java.util.Date(),"modifier",new java.util.Date())
        Ok(views.html.reviewForm(reviewForm.fill(newReview)))
      }
    )
  

    
  }

   def add = Action {
    Ok(views.html.reviewForm(reviewForm))
  }
  
}