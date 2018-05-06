package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class FileReader {
    private String[] strings;
    private int position;

    public FileReader(String file){
        FileHandle handle = Gdx.files.internal(file);
        strings = handle.readString().split(" ");
    }

    public String nextLine(){
        return strings[position++];
    }
    public boolean compareNext(String s){
        return (strings[position].equals(s));
    }
    public void next(){
        position++;
    }
    public void up(){
        position=0;
    }

    public String getCurrent(){
        return strings[position];
    }
}
