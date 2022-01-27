package com.example.isibgram.Post;

public class Post {

   private String title;
   private String postMatricule;
   private String textDescription;

   public Post(){}

   public Post(String title, String postMatricule, String textDescription) {
      this.title = title;
      this.postMatricule = postMatricule;
      this.textDescription = textDescription;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getPostMatricule() {
      return postMatricule;
   }

   public void setPostMatricule(String postMatricule) {
      this.postMatricule = postMatricule;
   }

   public String getTextDescription() {
      return textDescription;
   }

   public void setTextDescription(String textDescription) {
      this.textDescription = textDescription;
   }
}
