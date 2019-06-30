package com.sirmasolution.siliev.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sirmasolution.siliev.business.EmployeeLogic;
import com.sirmasolution.siliev.model.Employee;
import com.sirmasolution.siliev.model.PairedEmployees;


public class ParserServiceImpl {

	@Autowired
	private EmployeeLogic employeeEngagementLogic;

	public List<PairedEmployees> getListOfPairedEmployeesWorkedLongestInProject(List<Employee> employee) 
			throws Exception {

		Map<String, List<Employee>> mapOfEmployee = employeeEngagementLogic.createMapOfProjectIdAndEmployeeId(employee);

		List<PairedEmployees> listOfEmployeesWorkedLongest = new ArrayList<PairedEmployees>();

		PairedEmployees employeesWorkedLongestInProject = new PairedEmployees();
		long buff = 0;

		for (String projectId : mapOfEmployee.keySet()) {

			List<Employee> localEmployee = mapOfEmployee.get(projectId);

			if (localEmployee.size() == 2) {

				long period = localEmployee.get(1).getPeriod();

				if (buff < period) {

					buff = period;

					employeesWorkedLongestInProject.setProjectId(projectId);
					employeesWorkedLongestInProject.setFirstEmpId(localEmployee.get(0).getEmpId());
					employeesWorkedLongestInProject.setSecondEmpId(localEmployee.get(1).getEmpId());
				}
			}
		}
		listOfEmployeesWorkedLongest.add(employeesWorkedLongestInProject);
		
		return listOfEmployeesWorkedLongest;	
		
	}
}

	