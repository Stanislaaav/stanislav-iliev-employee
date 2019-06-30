package com.sirmasolution.siliev.services;


import java.io.File;
import java.util.List;

import com.sirmasolution.siliev.model.Employee;
import com.sirmasolution.siliev.model.PairedEmployees;

/**
 * Generic interface for Employee Data Parser
 * 
 * @author Stan
 *
 */
public interface ParserService {

	public List<Employee> parseEmployeeData(File file) throws Exception;
	
	public List<PairedEmployees> getListOfPairedEmployeesWorkedLongestInProject(List<Employee> employeeProjectEngagements) throws Exception;

}

