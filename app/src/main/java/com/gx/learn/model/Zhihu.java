package com.gx.learn.model;

/**
 * Created by gx on 16/10/14.
 */

import java.util.List;

public class Zhihu {
    private String date;

    private List<Stories> stories ;

    private List<Top_stories> top_stories ;

    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setStories(List<Stories> stories){
        this.stories = stories;
    }
    public List<Stories> getStories(){
        return this.stories;
    }
    public void setTop_stories(List<Top_stories> top_stories){
        this.top_stories = top_stories;
    }
    public List<Top_stories> getTop_stories(){
        return this.top_stories;
    }
    public class Top_stories {
        private String image;

        private int type;

        private int id;

        private String ga_prefix;

        private String title;

        public void setImage(String image){
            this.image = image;
        }
        public String getImage(){
            return this.image;
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
        public void setGa_prefix(String ga_prefix){
            this.ga_prefix = ga_prefix;
        }
        public String getGa_prefix(){
            return this.ga_prefix;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }

    }
    public class Stories {
        private List<String> images;

        private int type;

        private int id;

        private String ga_prefix;

        private String title;

        public void setString(List<String> images) {
            this.images = images;
        }

        public List<String> getString() {
            return this.images;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getGa_prefix() {
            return this.ga_prefix;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }


    }

}