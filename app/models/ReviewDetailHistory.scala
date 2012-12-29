package models

import java.util.Date

case class ReviewDetailHistory(
	reviewDetailHtrId : String,
	reviewId : String,
	attrName : String,
	oldVal : String,
	newVal : String,
	creationDate : Date,
	modifier : String
)