package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import models.ReviewDetail;
import models.User;

import org.codehaus.jackson.node.ObjectNode;

import play.data.Form;
import play.db.DB;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.reviewFrom;

public class ReviewController extends Controller{

	  final static Form<User> userForm = form(User.class);
	  final static Form<ReviewDetail> reviewForm = form(ReviewDetail.class);
	
	 public static Result index() {
		 Form<User> filledUserForm = userForm.bindFromRequest();
		 String username = filledUserForm.field("username").value();
		  ReviewDetail review = new ReviewDetail();
		  review.setReviewerId(username);
		  review.setAuthor(username);
		  Form<ReviewDetail> filledForm =  form(ReviewDetail.class).fill(review);

		  DataSource ds = DB.getDataSource();
		  Connection connection = DB.getConnection();
		  System.out.println(connection);
		  List<ReviewDetail> reviewList = listReview();
		  System.out.println(reviewList);

	    return ok(reviewFrom.render(filledForm,reviewList));
	  }
	 
	 
	 public static Result add() {
		 Form<ReviewDetail> filledReviewForm = reviewForm.bindFromRequest();
		 ReviewDetail review = filledReviewForm.get();
		 
		  createReview(review);
		  
		  ReviewDetail newReview = new ReviewDetail();
		  newReview.setReviewerId(review.getReviewerId());
		  
		  
		  //review.setReviewerId(username);
		 // review.setAuthor(username);
		 //ReviewDetail review = new ReviewDetail();
		//  Form<ReviewDetail> filledForm =  form(ReviewDetail.class).fill(review);
		  List<ReviewDetail> reviewList = listReview();
	    return ok(reviewFrom.render(filledReviewForm.fill(newReview),reviewList));
	  }
	 
	 
	
	 public static Result newReview() {
		 Form<ReviewDetail> filledReviewForm = reviewForm.bindFromRequest();
		 ReviewDetail review = filledReviewForm.get();
		  createReview(review);
		  ObjectNode result = Json.newObject();
		  result.put("status", "OK");
		  
		  ObjectNode notifier = Json.newObject();
		  notifier.put("msg", "Review Added");
		  notifier.put("data", "<tr><td>"+review.getReviewId()+"</td><td>"+review.getAuthor()+"</td><td>"+ review.getSummery()+"</td><td>"+review.getAssignedTo()+"</td><td>"+review.getFileName()+"</td><td>"+review.getStatus()+"</td></tr> ");
		  SocketController.outter.write(notifier);
	    return ok(result);
	  }
	 


	private static void createReview(ReviewDetail review) {
		DataSource ds = DB.getDataSource();
		  Connection connection = DB.getConnection();
		  try{
		  PreparedStatement ps = connection.prepareStatement("insert into review_dtl values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		  ps.setString(1, String.valueOf(Math.round(Math.random()*1000)));//Temp
		  ps.setString(2, review.getReviewerId());
		  ps.setString(3, review.getType());
		  ps.setString(4, review.getSeverity());
		  ps.setString(5, review.getFileName());
		  ps.setString(6, review.getSummery());
		  ps.setString(7, review.getDescription());
		  ps.setString(8, review.getPhase());
		  ps.setString(9, review.getAssignedTo());
		  ps.setString(10, review.getAuthor());
		  ps.setTimestamp(11,new Timestamp(System.currentTimeMillis()));
		  ps.setTimestamp(12,new Timestamp(System.currentTimeMillis()));
		  ps.setString(13, review.getModifier());
		  ps.setString(14, review.getLine());
		  ps.setString(15, review.getRevision());
		  ps.setString(16, review.getStatus());
		  
		  boolean result = ps.execute();
		  System.out.println("executed for insert ["+result+"]");
		  
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	}
	
	
	
	private static List<ReviewDetail> listReview() {
		DataSource ds = DB.getDataSource();
		  Connection connection = DB.getConnection();
		List<ReviewDetail>  reviewList = new ArrayList<ReviewDetail>();
		  try{
		  PreparedStatement ps = connection.prepareStatement("select * from review_dtl order by creation_dt asc ");
		 
		  boolean result = ps.execute();
		  ResultSet rs =  ps.getResultSet();
	
		  
		  
		 while(rs.next()){
			 ReviewDetail review = new ReviewDetail();
			 review.setReviewId(rs.getString(1));
			 review.setReviewerId(rs.getString(2));
			 review.setType(rs.getString(3));
			 review.setSeverity(rs.getString(4));
			 review.setFileName(rs.getString(5));
			 review.setSummery(rs.getString(6));
			 review.setDescription(rs.getString(7));
			 review.setPhase(rs.getString(8));
			 review.setAssignedTo(rs.getString(9));
			 review.setAuthor(rs.getString(10));
			 review.setCreationDate(rs.getTimestamp(11));
			 review.setModificationDate(rs.getTimestamp(12));
			 review.setModifier(rs.getString(13));
			 review.setLine(rs.getString(14));
			 review.setRevision(rs.getString(15));
			 review.setStatus(rs.getString(16));
			 reviewList.add(review);
		 }
		  System.out.println("executed for select ["+result+"]");
		  
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return reviewList;
	}
}
