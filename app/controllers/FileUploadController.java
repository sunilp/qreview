package controllers;

import models.S3File;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http;

import views.html.*;

import java.util.List;
import java.util.UUID;

public class FileUploadController extends Controller {

    public static Result index() {
        List<S3File> uploads = new Model.Finder(UUID.class, S3File.class).all();
        return ok(fileUpload.render(uploads));
    }

    public static Result upload() {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart uploadFilePart = body.getFile("upload");
        if (uploadFilePart != null) {
            S3File s3File = new S3File();
            s3File.name = uploadFilePart.getFilename();
            s3File.file = uploadFilePart.getFile();
            s3File.save();
            return redirect(routes.FileUploadController.index());
        }
        else {
            return badRequest("File upload error");
        }
    }

}