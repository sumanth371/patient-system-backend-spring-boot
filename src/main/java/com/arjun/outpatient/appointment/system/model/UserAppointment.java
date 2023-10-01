package com.arjun.outpatient.appointment.system.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserAppointment  {
    int id;
    String name;
    String doctorName;
    String Time;
    String location;
    String day;
}
