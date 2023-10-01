package com.arjun.outpatient.appointment.system.store;

import com.arjun.outpatient.appointment.system.model.UserAppointment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
@Component
public class DoctorTimesStore {
    private static HashMap<String, Integer> times = new HashMap<>();
    private static HashMap<String, HashMap<String, HashMap<Integer, UserAppointment>>> slotMap = new HashMap<>();

    private static HashMap<String,boolean[]>availableSlots=new HashMap<>();
    private static HashMap<Integer,String>idTOtime=new HashMap<>();
    private static boolean slotsFilled[] = new boolean[5];


    {
        times.put("5:00 - 5:30", 0);
        times.put("5:30 - 6:00", 1);
        times.put("6:00 - 6:30", 2);
        times.put("6:30 - 7:00", 3);
        times.put("7:00 - 7:30", 4);
        availableSlots.put("monday",new boolean[5]);
        availableSlots.put("Tuesday",new boolean[5]);
        availableSlots.put("wednesday",new boolean[5]);
        availableSlots.put("Thursday",new boolean[5]);
        availableSlots.put("friday",new boolean[5]);
        availableSlots.put("saturday",new boolean[5]);
        idTOtime.put(0,"5:00 - 5:30");
        idTOtime.put(1,"5:30 - 6:00");
        idTOtime.put(2,"6:00 - 6:30");
        idTOtime.put(3,"6:30 - 7:00");
        idTOtime.put(4,"7:00 - 7:30");

    }

    public static HashMap<String, Integer> getTimes() {
        return times;
    }

    public boolean doctorTimeforUser(String time) {
        if (times.containsKey(time)) {
            return true;
        }
        return false;
    }

    public boolean slotAvailable(UserAppointment ap) {
        int slotId= times.get(ap.getTime());
        boolean[] available= availableSlots.get(ap.getDay());
        if(available[slotId]==true){
            return false;
        }
        else return true;
    }

    public ArrayList<String> giveAvailableSlots(String day){
            ArrayList<String >ar=new ArrayList<>();
            boolean[]getAvailableSlots=availableSlots.get(day);
            for(int i=0;i<5;i++){
                if(getAvailableSlots[i]==false){
                    String availableTime=idTOtime.get(i);
                    ar.add(availableTime);
                }
            }
            return ar;
    }

    public String addApointment(UserAppointment ap) {
        int slotId= times.get(ap.getTime());
        boolean[] available= availableSlots.get(ap.getDay());
        available[slotId] = true;
        availableSlots.put(ap.getDay(),available);

        String name = ap.getDoctorName();
        String day = ap.getDay();
        String time = ap.getTime();
        int id = ap.getId();
        if (slotMap.containsKey(name)) {
            if (slotMap.get(name).containsKey(day)) {
                if (slotMap.get(name).get(day).containsKey(id)) {
                    return "already appointment booked in this day";
                } else {
                    slotMap.get(name).get(day).put(id, ap);
                }
            } else {
                UserAppointment ua = ap;
                HashMap<Integer, UserAppointment> map = new HashMap<>();
                map.put(id, ap);
                slotMap.get(name).put(day, map);

            }
        } else {

            UserAppointment ua = ap;
            HashMap<Integer, UserAppointment> map = new HashMap<>();
            map.put(id, ap);
            HashMap<String, HashMap<Integer, UserAppointment>> whole = new HashMap<>();
            whole.put(day, map);
            slotMap.put(name, whole);

        }
        return  "Hi"+ap.getTime()+ "appointment booked sucessfull!!!";
    }
}
