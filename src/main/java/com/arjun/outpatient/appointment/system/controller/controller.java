package com.arjun.outpatient.appointment.system.controller;


import com.arjun.outpatient.appointment.system.model.Doctor;
import com.arjun.outpatient.appointment.system.model.UserAppointment;
import com.arjun.outpatient.appointment.system.service.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/api/doctor")
public class controller {

    @Autowired
    services services;
    @PostMapping
    public ResponseEntity<String> addDoctors(@RequestBody Doctor doctor){
        String q= services.postDoctor(doctor);
        return ResponseEntity.ok(q);

    }


    @GetMapping("allDoctors")
    public ResponseEntity<ArrayList<Doctor>> getAllDoctors(){
        ArrayList<Doctor>a=services.Getar();
//        for (Doctor p:a){
//            System.out.println(p);
//        }
       return  ResponseEntity.ok(a);
    }


    @PostMapping("appointment")
    public ResponseEntity<Object> postAppointment(@RequestBody UserAppointment userAppointment){
        System.out.println("in controller" +userAppointment.getDoctorName()+" "+userAppointment.getName());
        Object op=services.mapUsertoDocter(userAppointment);
        return ResponseEntity.ok(op);
    }


    @GetMapping("getDoctor/{id}")
    public ResponseEntity<Object> getAllDoctors(@PathVariable int id){
        Object dc=services.checkAndgiveDoctordetails(id);
        return ResponseEntity.ok(dc);
    }
    @DeleteMapping("AllDelete")
    public ResponseEntity<String> delete(){
        String h=services.delete();
        return  ResponseEntity.ok(h);
    }




}
