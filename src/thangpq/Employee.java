/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thangpq;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ThangPQ
 */
public class Employee {
    private String Name;
    private String Department;
    private double Salary;
    private double Bonus;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public double getBonus() {
        return Bonus;
    }

    public void setBonus(double Bonus) {
        this.Bonus = Bonus;
    }

    public Employee(String Name, String Department, double Salary, double Bonus) {
        this.Name = Name;
        this.Department = Department;
        this.Salary = Salary;
        this.Bonus = Bonus;
    }

    public Employee() {
    }
    
    public String displayTotalPersonalIncome(Employee emp){    
    	return "<p>"+"Họ tên: "+ emp.getName()+". Tổng thu nhập: "+  formatDecimal(emp.getSalary() + emp.getBonus())+"</p>";
    }
    
    public static String formatDecimal(double input){
		NumberFormat formatter = new DecimalFormat("#0.00");     
		String result = formatter.format(input);
		
		return result;
	}
    
    public static String displayTotalSalaryOfDepartment(HashMap<String, Double> departIncome){
    	StringBuffer result = new StringBuffer();
    	for (HashMap.Entry<String, Double> entry : departIncome.entrySet())
		{
		    result.append("<p>Tiền lương chi cho phòng " + entry.getKey() + ": " + formatDecimal(entry.getValue())+"</p>");
		}
		return result.toString();
    	
    }
    
    public static String displayTotalCompanyIncome(List<Employee> emps){
    	double income = 0; 
    	for (Employee emp : emps) {
			income = income + emp.getSalary() + emp.getBonus();
		}
    	return "<p>Tổng thu nhập toàn công ty: " + formatDecimal(income);
    }          
}
