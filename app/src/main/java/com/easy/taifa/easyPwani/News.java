package com.easy.taifa.easyPwani;

import java.io.Serializable;

public class News implements Serializable{
  public   String heading;
  public  String story;
  public  String imgUrl;
  public  String date;
  public  String storyUrl;


    public  News(){

    }
    public News(String heading,String story,String imgUrl,String date,String storyUrl){
        this.date =date;
        this.heading = heading;
        this.imgUrl = imgUrl;
        this.story = story;
        this.storyUrl = storyUrl;


    }
}
