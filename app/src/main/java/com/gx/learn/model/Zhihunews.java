package com.gx.learn.model;

import java.util.List;

/**
 * Created by gx on 16/10/16.
 */

public class Zhihunews {
    private String body;

    private String image_source;

    private String title;

    private String image;

    private String share_url;

    private List<String> js ;

    private String ga_prefix;



    private List<String> images ;

    private int type;

    private int id;

    private List<String> css ;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    public void setImage_source(String image_source){
        this.image_source = image_source;
    }
    public String getImage_source(){
        return this.image_source;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return this.image;
    }
    public void setShare_url(String share_url){
        this.share_url = share_url;
    }
    public String getShare_url(){
        return this.share_url;
    }
    public void setJs(List<String> js){
        this.js = js;
    }
    public List<String> getJs(){
        return this.js;
    }
    public void setGa_prefix(String ga_prefix){
        this.ga_prefix = ga_prefix;
    }
    public String getGa_prefix(){
        return this.ga_prefix;
    }


    public void setImages(List<String> images){
        this.images = images;
    }
    public List<String> getImages(){
        return this.images;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setcss(List<String> css){
        this.css = css;
    }
    public List<String> getcss(){
        return this.css;
    }
}
