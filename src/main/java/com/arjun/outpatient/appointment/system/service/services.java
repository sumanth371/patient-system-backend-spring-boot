package com.arjun.outpatient.appointment.system.service;

import com.arjun.outpatient.appointment.system.model.Doctor;
import com.arjun.outpatient.appointment.system.model.UserAppointment;
import com.arjun.outpatient.appointment.system.store.DataStore;
import com.arjun.outpatient.appointment.system.store.DoctorTimesStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class services {

    @Autowired
    DataStore ds;

    @Autowired
    DoctorTimesStore doctorTimesStore;
    public String postDoctor(Doctor doctor) {
        ds.addDoctor(doctor);
        return "Doctor added successfully";
    }
    public ArrayList<Doctor> Getar(){
        return ds. getDoctors();
    }

    public Object checkAndgiveDoctordetails(int id){
        HashMap<Integer,Doctor>has=ds.getDoctersMap();
        if(!has.containsKey(id)){
            return "sorry docter you wanted is not available in database";
        }
        return has.get(id);
    }

    public Object mapUsertoDocter(UserAppointment appointment){
        HashMap<Integer,Doctor>docterMap=ds.getDoctersMap();
        HashMap<String,Integer>nameToid=ds.getNameToid();
        String docterName=appointment.getDoctorName();
        String time=appointment.getTime();
    //    System.out.println("name"+" "+docterName);
//        for(Map.Entry<String,Integer>entry:nameToid.entrySet()){
//            System.out.println(entry.getKey()+" "+entry.getValue());
//        }
        if(!nameToid.containsKey(docterName)){
            return "sorry doctor name is not in database";
        }
        int doctorId=nameToid.get(docterName);
        String doctorLocation=docterMap.get(doctorId).getLocation();
        String patientLocation=appointment.getLocation();
        if(!doctorLocation.equals(patientLocation)){
            return "sorry doctor is not available in your location";
        }
        if(!doctorTimesStore.doctorTimeforUser(time)){
            return "doctor not available in that time";
        }
        if(!doctorTimesStore.slotAvailable(appointment)){
            ArrayList<String>availableSlots=doctorTimesStore.giveAvailableSlots(appointment.getDay());
            return "sorry slot is you took is filled these are the available slots"+availableSlots;
        }
       String res= doctorTimesStore.addApointment(appointment);
        return res;


    }



    public String delete(){
        return ds.deleteAll();
    }

}
