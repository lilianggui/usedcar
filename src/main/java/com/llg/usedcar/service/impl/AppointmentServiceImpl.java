package com.llg.usedcar.service.impl;

import com.llg.usedcar.entity.Appointment;
import com.llg.usedcar.mapper.dao.AppointmentMapper;
import com.llg.usedcar.service.interfaces.AppointmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Resource
    private AppointmentMapper appointmentMapper;

    @Override
    public List<Appointment> listAppointment(Appointment appointment) {
        return appointmentMapper.listAppointment(appointment);
    }

    @Override
    public List<Appointment> appointmentRemind(Appointment appointment) {
        return appointmentMapper.appointmentRemind(appointment);
    }

    @Override
    public int invalidAppointment(Appointment appointment) {
        return appointmentMapper.invalidAppointment(appointment);
    }

    @Override
    public int effectAppointment(Appointment appointment) {
        return appointmentMapper.effectAppointment(appointment);
    }

    @Override
    public int cancelAppoint(Appointment appointment) {
        return appointmentMapper.cancelAppoint(appointment);
    }

    @Override
    public int changeAppointDate(Appointment appointment) {
        return appointmentMapper.changeAppointDate(appointment);
    }

    @Override
    public List<Appointment> myAppointList(Appointment appointment) {
        return appointmentMapper.myAppointList(appointment);
    }
}
