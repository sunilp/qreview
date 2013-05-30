package controllers;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.ReviewDetail;
import models.Task;
import models.User;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.data.*;
import static play.data.Form.*;
import play.db.DB;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class UserController extends Controller{
    
    final static Form<User> userForm = form(User.class);

    public static Result updateUser() {
	 Form<User> filledForm = userForm.bindFromRequest();
	 User user = filledForm.get();
	 
	 System.out.println(user.name);
	 System.out.println(user.alias);
	 System.out.println(user.supervisor);
	 System.out.println(user.primaryEmail);
	 
	 User.update(ReviewController.connection,user);
  	 return ok(home.render(ReviewController.listReview(), session("username")));
    }
    
    public static Result showUser(String id) {
 	
 	User user = User.findById(ReviewController.connection, id);
 	if(user==null){
 	    System.out.println("user is null");
 	    user=new User();
 	}else{
 	    System.out.println("shown data for user "+user.username);
 	}
   		
   	 return ok(editUser.render(userForm.fill(user)));
     }
    
    
}
