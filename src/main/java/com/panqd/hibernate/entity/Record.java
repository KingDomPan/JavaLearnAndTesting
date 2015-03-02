package com.panqd.hibernate.entity;

import java.util.Date;

public class Record {
    private Long id;
    private Date contractDate;
    private String contractNumber;
    private String projectName;
    private String contractAttainment;
    private String sendPackageName;
    private String projectDept;
    private Long income;
    private Long outlay4Material;
    private Long outlay4Service;
    private Long outlay4Mechanic;
    private String remark;
    
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getContractDate() {
        return this.contractDate;
    }
    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }
    public String getContractNumber() {
        return this.contractNumber;
    }
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
    public String getProjectName() {
        return this.projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getContractAttainment() {
        return this.contractAttainment;
    }
    public void setContractAttainment(String contractAttainment) {
        this.contractAttainment = contractAttainment;
    }
    public String getSendPackageName() {
        return this.sendPackageName;
    }
    public void setSendPackageName(String sendPackageName) {
        this.sendPackageName = sendPackageName;
    }
    public String getProjectDept() {
        return this.projectDept;
    }
    public void setProjectDept(String projectDept) {
        this.projectDept = projectDept;
    }
    public Long getIncome() {
        return this.income;
    }
    public void setIncome(Long income) {
        this.income = income;
    }
    public Long getOutlay4Material() {
        return this.outlay4Material;
    }
    public void setOutlay4Material(Long outlay4Material) {
        this.outlay4Material = outlay4Material;
    }
    public Long getOutlay4Service() {
        return this.outlay4Service;
    }
    public void setOutlay4Service(Long outlay4Service) {
        this.outlay4Service = outlay4Service;
    }
    public Long getOutlay4Mechanic() {
        return this.outlay4Mechanic;
    }
    public void setOutlay4Mechanic(Long outlay4Mechanic) {
        this.outlay4Mechanic = outlay4Mechanic;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
