package com.intecap.apiproductos.response;

import java.util.ArrayList;
import java.util.HashMap;

public class Response {

    private ArrayList<HashMap<String,String>> metadata = new ArrayList<>();

    public ArrayList<HashMap<String,String>> getMetadata(){
        return this.metadata;
    }

    public void setMetadata(String tipo,String codigo,String data){
        HashMap<String, String> map=new HashMap<>();
        map.put("tipo",tipo);
        map.put("codigo",codigo);
        map.put("data",data);
        this.metadata.add(map);
    }
}
