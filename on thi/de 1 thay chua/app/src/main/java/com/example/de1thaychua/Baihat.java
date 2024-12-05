package com.example.de1thaychua;

public class Baihat implements Comparable<Baihat> {

    private int ID;
    private String name;
    private String singer;
    private float time;
    public Baihat(){

    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }



    public Baihat(int ID, String name, String singer, float time) {
        this.ID = ID;
        this.name = name;
        this.singer = singer;
        this.time = time;
    }


    public Baihat(String name, String singer, float time) {
        this.name = name;
        this.singer = singer;
        this.time = time;
    }

    @Override
    public int compareTo(Baihat o) {
        if(this.getTime() < o.getTime())
            return -1;
        if(this.getTime()==o.getTime())
            return 0;
        return 1;
    }
}
