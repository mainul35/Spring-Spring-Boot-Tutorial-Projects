package com.mainul35.example.model;

import java.util.Date;

public class EmployeeEvent {
    private Employee employee;
    private Date date;

    public EmployeeEvent() {
    }

    public EmployeeEvent(Employee employee, Date date) {
        this.employee = employee;
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEvent that = (EmployeeEvent) o;

        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = employee != null ? employee.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmployeeEvent{" +
                "employee=" + employee +
                ", date=" + date +
                '}';
    }
}
