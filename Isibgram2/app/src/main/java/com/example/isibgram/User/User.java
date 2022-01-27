package com.example.isibgram.User;

public class User {

    private String name;
    private String profile_photo;
    private String email;
    private Integer posts;
    private String matricule;

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String name, String profile_photo, String email, Integer posts, String matricule) {
        this.name = name;
        this.profile_photo = profile_photo;
        this.email = email;
        this.posts = posts;
        this.matricule = matricule;
    }

    public  User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPosts() {
        return posts;
    }

    public void setPosts(Integer posts) {
        this.posts = posts;
    }
}
