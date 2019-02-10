package com.llg.usedcar.mapper.dao;

import com.llg.usedcar.entity.Appointment;

import java.util.List;

public interface AppointmentMapper {
    List<Appointment> listAppointment(Appointment appointment);

    List<Appointment> appointmentRemind(Appointment appointment);

    int invalidAppointment(Appointment appointment);
    int effectAppointment(Appointment appointment);

    int cancelAppoint(Appointment appointment);

    int changeAppointDate(Appointment appointment);

    List<Appointment> myAppointList(Appointment appointment);
}
