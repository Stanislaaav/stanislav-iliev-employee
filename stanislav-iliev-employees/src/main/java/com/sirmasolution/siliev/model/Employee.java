package com.sirmasolution.siliev.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;



/**
 * Bean for capturing the employee project engagement period.
 * 
 * @author Stan
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee implements Comparable<Employee>, Serializable {

	private static final long serialVersionUID = 637123923937245166L;

	@JacksonXmlProperty(localName = "empId")
	private String empId;
	@JacksonXmlProperty(localName = "projectId")
	private String projectId;
	@JacksonXmlProperty(localName = "dateFrom")
	private Date dateFrom;
	@JacksonXmlProperty(localName = "dateTo")
	private Date dateTo;
	private Long diff = 0L ;

	public Employee() {

	}
	
	public void addDateDiff(Long diff) {
		this.diff = this.diff + diff;
	}

	public Long getPeriod() {
		if (dateTo == null) {
			dateTo = new Date();
		}
		
		Long dateDiff = dateTo.getTime() - dateFrom.getTime();
		return dateDiff + diff;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getEmpId() {
		return empId;
	}

	public String getProjectId() {
		return projectId;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	// Bean isolation methods------------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateFrom == null) ? 0 : dateFrom.hashCode());
		result = prime * result + ((dateTo == null) ? 0 : dateTo.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (dateFrom == null) {
			if (other.dateFrom != null)
				return false;
		} else if (!dateFrom.equals(other.dateFrom))
			return false;
		if (dateTo == null) {
			if (other.dateTo != null)
				return false;
		} else if (!dateTo.equals(other.dateTo))
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeProjectEngagement [empId=" + empId + ", projectId=" + projectId + ", dateFrom=" + dateFrom
				+ ", dateTo=" + dateTo + "]";
	}

	public int compareTo(Employee o) {
		 return this.getPeriod().compareTo( o.getPeriod());
	}

}
