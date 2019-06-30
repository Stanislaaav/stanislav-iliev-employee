package com.sirmasolution.siliev.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sirmasolution.siliev.model.Employee;



/**
 * 
 * This class contains the business logic to 
 * filter, group , sort and extract employees
 * who have worked the longest in a project
 * 
 * @author Stan
 *
 */
@Component
public class EmployeeLogic {
	
	
	public Map<String, List<Employee>> createMapOfProjectIdAndEmployeeId(List<Employee> employeeProject) {
		
		List<Employee> listOfEmployee;
		
		Map<String, List<Employee>> mapOfmployee = new HashMap<String, List<Employee>>();
		
		for (Employee employee: employeeProject) {
			
			String projectId = employee.getProjectId();
			
			
			if (mapOfmployee.containsKey(projectId)) {			
				listOfEmployee = mapOfmployee.get(projectId);
				boolean skipAdd = false;
 				for(Employee localEmployee : listOfEmployee) {
					
					if(localEmployee.getEmpId().equals(employee.getEmpId())){
						localEmployee.addDateDiff(employee.getPeriod());
						skipAdd = true;
					}
				}
				if(!skipAdd) {
					listOfEmployee.add(employee);
					employee.addDateDiff(employee.getPeriod());
				}
			} else {
				employee.addDateDiff(employee.getPeriod());
				listOfEmployee = new ArrayList<Employee>();
				listOfEmployee.add(employee);
				mapOfmployee.put(projectId, listOfEmployee);
			}
		}		
		
		// Sort the employees based on project duration
		for (List<Employee> employeesList: mapOfmployee.values()) {
				Collections.sort(employeesList, Collections.reverseOrder());
				if(employeesList.size() > 2) {
					employeesList.subList(2, employeesList.size()).clear();
				}
		}
		
		return mapOfmployee;
	}	
}
