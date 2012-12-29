package models 
import java.util.Date

case class ReviewDetail(
	reviewId : String,
	reviewerId : String,
	rtype : String,
	severity : String,
	fileName : String,
	summery : String,
	description : String,
	phase : String,
	assignedTo : String,
	author : String,
	creationDate : Date,
	modifier : String,
	modificationDate : Date
)