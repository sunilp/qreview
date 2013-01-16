package controllers;

import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
	
  final static Form<User> signupForm = form(User.class);
  
  public static Result index() {
	  User user = new User();
	  user.setUsername(System.getProperty("user.name"));
	  Form<User> filledForm = signupForm.fill(user);
    return ok(index.render("Start putting your view...",filledForm));
  }
  
  
  
  
  
  
}