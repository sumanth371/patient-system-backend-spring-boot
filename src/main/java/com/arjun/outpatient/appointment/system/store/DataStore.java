package com.arjun.outpatient.appointment.system.store;

import com.arjun.outpatient.appointment.system.model.Doctor;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataStore {
    private  static HashMap<Integer,Doctor>has=new HashMap<>();
    private static HashMap<String,Integer>nameToid=new HashMap<>();
   private static ArrayList<Doctor>ar=new ArrayList<>();
    public static ArrayList<Doctor> getDoctors() {
        for(Map.Entry<Integer,Doctor>entry:has.entrySet()){
            ar.add(entry.getValue());

        }
        return ar;
    }
   public String deleteAll(){

        ar.clear();
        has.clear();
        nameToid.clear();
        return "succesfull";
   }
    public HashMap<Integer,Doctor> getDoctersMap(){
        return has;
    }

    public HashMap<String,Integer> getNameToid(){
        return nameToid;
    }

    public static void addDoctor(Doctor doctor) {
       int id=doctor.getId();
       String name=doctor.getName();
       has.put(id,doctor);
       nameToid.put(name,id);
    }
}
