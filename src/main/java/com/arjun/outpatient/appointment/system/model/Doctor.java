package com.arjun.outpatient.appointment.system.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Doctor {
    int id;
    String name;
    String location;
    String description;
    String day;

}