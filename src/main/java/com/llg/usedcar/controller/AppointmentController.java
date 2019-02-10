package com.llg.usedcar.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.llg.usedcar.entity.Appointment;
import com.llg.usedcar.entity.vo.PageQO;
import com.llg.usedcar.result.Result;
import com.llg.usedcar.service.interfaces.AppointmentService;
import com.llg.usedcar.service.interfaces.CarService;
import com.llg.usedcar.utils.CommonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AppointmentController {

    @Resource
    private AppointmentService appointmentService;

    @Resource
    private CarService carService;

    /**
     * 失效预约
     * @param appointment
     * @return
     */
    @PostMapping("invalidAppointment")
    public Result invalidAppointment(Appointment appointment){
        //校验是否为登陆人的店铺的预约 todo
        appointmentService.invalidAppointment(appointment);
        return Result.buildBaseSuccess();
    }

    /**
     * 生效预约
     * @param appointment
     * @return
     */
    @PostMapping("effectAppointment")
    public Result effectAppointment(Appointment appointment){
        appointmentService.effectAppointment(appointment);
        return Result.buildBaseSuccess();
    }

    /**
     * 后台预约列表
     * @param appointment
     * @return
     */
    @GetMapping("listAppointment")
    public Result<Appointment> listAppointment(PageQO pageQO, Appointment appointment, HttpServletRequest request){
        appointment.setShopId(CommonUtils.sessionUser(request).getShopId());
        Page<Appointment> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        List<Appointment> appointments = appointmentService.listAppointment(appointment);
        return Result.buildPageSuccess(page,appointments);
    }

    /**
     * 预约提醒列表
     * @param appointment
     * @return
     */
    @GetMapping("appointmentRemind")
    public Result<Appointment> appointmentRemind(PageQO pageQO, Appointment appointment, HttpServletRequest request){
        appointment.setShopId(CommonUtils.sessionUser(request).getShopId());
        Page<Appointment> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        List<Appointment> appointments = appointmentService.appointmentRemind(appointment);
        return Result.buildPageSuccess(page,appointments);
    }


    /**
     * 小程序车辆预约
     * @param appointment
     * @return
     */
    @PostMapping("/wx/wxCarAppoint")
    public Result wxCarAppoint(Appointment appointment, HttpServletRequest request){
        Long userId = CommonUtils.wxSessionUser(request).getUserId();
        appointment.setUserId(userId);
        appointment.setAppointmentStatus(1);
        return Result.buildBaseSuccess(carService.wxCarAppoint(appointment));
    }

    /**
     * 小程序取消预约
     * @param appointment
     * @return
     */
    @PostMapping("/wx/cancelAppoint")
    public Result cancelAppoint(Appointment appointment){
        return Result.buildBaseSuccess(appointmentService.cancelAppoint(appointment));
    }

    /**
     * 小程序修改预约时间
     * @param appointment
     * @return
     */
    @PostMapping("/wx/changeAppointDate")
    public Result changeAppointDate(Appointment appointment){
        return Result.buildBaseSuccess(appointmentService.changeAppointDate(appointment));
    }


    @GetMapping("/wx/myAppointList")
    public Result myAppointList(PageQO pageQO, Appointment appointment, HttpServletRequest request){
        appointment.setUserId(CommonUtils.wxSessionUser(request).getUserId());
        Page<Appointment> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        List<Appointment> appointments = appointmentService.myAppointList(appointment);
        return Result.buildPageSuccess(page, appointments);
    }


}
