/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Employee;

/**
 *
 * @author magda
 */
public class EmployeeDTO {
    private static int id;
    private static String name;
    private static String address;

    public EmployeeDTO(Employee employee) {
        employee.setAddress(this.address);
        employee.setId(this.id);
        employee.setName(this.name);
    }
    
    
    
}
