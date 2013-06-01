package controllers;

import models.User;
import play.data.*;
import static play.data.Form.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.iereject;
import views.html.index;

public class Application extends Controller {
	
  final static Form<User> userForm = form(User.class, User.All.class);
  
  public static Result index() {
	  User user = new User();
	//  user.setUsername(System.getProperty("user.name"));
	  String username = session("username");
	  if(username!=null && !username.trim().equalsIgnoreCase("null")){
	      user.setUsername(username);
	    return ReviewController.index(user);  
	  }
	  
	  Form<User> filledForm = userForm.fill(user);
    return ok(index.render("Start doing differently...",filledForm));
  }
  
  
  public static Result submit() {
		 Form<User> filledUserForm = userForm.bindFromRequest();
		 User user = filledUserForm.get();
		 String username = user.username.trim().toLowerCase();
		 System.out.println("user:"+username);
		 if(filledUserForm.hasErrors() || user.username==null || "".equals(username.trim())) {
			 System.out.println("username is null");
	            return badRequest(index.render("Start putting your view...",filledUserForm));
	        }
		 
		 		User dbUser =  User.findById(ReviewController.connection, username);
		if(dbUser!=null){
		    System.out.println("user exist in db "+dbUser.getUsername());
		}else{
		   User.create(ReviewController.connection, user);
		   System.out.println("Created new User "+user.getUsername());
		}
		 //set the username in session
		 session("username", username);
	    return ReviewController.index(filledUserForm.get());
	  }
	 
  
  public static Result rejectIE() {
	  User user = new User();
	//  user.setUsername(System.getProperty("user.name"));
	  
	  Form<User> filledForm = userForm.fill(user);
    return ok(iereject.render());
  }
  
  public static Result signOut() {
	session("username",null);  
	  return index();
}
  
  
  
}