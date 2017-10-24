package com.rcodingschool.carrepair.Model;


import com.rcodingschool.carrepair.Validators.Date.FutureDateConstraint;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class RepairForm {

    private Long repairID;

    @FutureDateConstraint
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime repairDateTime;

    @NotNull(message="This field is required!")
    @Size(max=32, message="The status can not contain up to 32 characters!")
    @Pattern(regexp="^[a-zA-Z ]{1,32}", message="The status can contain only characters!")
    private String repairStatus;

    @NotNull(message="This field is required!")
    @Size(max=1024, message="The tasks can contain until 1024 characters!")
    @Pattern(regexp="^[a-zA-Z0-9 ]{1,1024}", message="The tasks can contain only characters!")
    private String repairTasks;

    @NotNull(message="This field is required!")
    @Size(min=1, max=9, message="The AFM should be exactly 9 digits!")
    @Pattern(regexp="^[1-9][0-9]{0,9}", message="The cost must contain only digits!")
    private String repairTotalCost;

    @NotNull(message="This field is required!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value=1, message="The repairID must be greater or equal than 1!")
    private Short repairTypeID;

    @NotNull(message="This field is required!")
    @Pattern(regexp="^[A-Z]{3}-[0-9]{4}", message="Plate number must have the format 'ABC-1234'!")
    private String repairVehicleID;

    public Long getRepairID() {
        return repairID;
    }

    public void setRepairID(Long repairID) {
        this.repairID = repairID;
    }

    public LocalDateTime getRepairDateTime() {
        return repairDateTime;
    }

    public void setRepairDateTime(LocalDateTime repairDateTime) {
        this.repairDateTime = repairDateTime;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    public String getRepairTasks() {
        return repairTasks;
    }

    public void setRepairTasks(String repairTasks) {
        this.repairTasks = repairTasks;
    }

    public Short getRepairTypeID() {
        return repairTypeID;
    }

    public void setRepairTypeID(Short repairTypeID) {
        this.repairTypeID = repairTypeID;
    }

    public String getRepairVehicleID() {
        return repairVehicleID;
    }

    public void setRepairVehicleID(String repairVehicleID) {
        this.repairVehicleID = repairVehicleID;
    }

    public String getRepairTotalCost() {
        return repairTotalCost;
    }

    public void setRepairTotalCost(String repairTotalCost) {
        this.repairTotalCost = repairTotalCost;
    }
}