package com.jat.tabdemo;

/**
 * Created by  on 2016/6/15..
 */
public class Channel {

    private String weburl;
    private String name;

    public Channel(String arg0,String name,int position,String arg1,String arg2){
        this.name = name;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
