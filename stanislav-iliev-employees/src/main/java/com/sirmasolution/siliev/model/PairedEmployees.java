package com.sirmasolution.siliev.model;


import java.io.Serializable;

public class PairedEmployees implements Serializable {
	
	private static final long serialVersionUID = 455120051233000791L;
	
	private String firstEmpId;
	private String secondEmpId;
	private String projectId;
	
	public PairedEmployees() {
		
	}

	public void setFirstEmpId(String firstEmpId) {
		this.firstEmpId = firstEmpId;
	}
	public void setSecondEmpId(String secondEmpId) {
		this.secondEmpId = secondEmpId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getFirstEmpId() {
		return firstEmpId;
	}

	public String getSecondEmpId() {
		return secondEmpId;
	}

	public String getProjectId() {
		return projectId;
	}


	// Bean isolation methods------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstEmpId == null) ? 0 : firstEmpId.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((secondEmpId == null) ? 0 : secondEmpId.hashCode());
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
		PairedEmployees other = (PairedEmployees) obj;
		if (firstEmpId == null) {
			if (other.firstEmpId != null)
				return false;
		} else if (!firstEmpId.equals(other.firstEmpId))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (secondEmpId == null) {
			if (other.secondEmpId != null)
				return false;
		} else if (!secondEmpId.equals(other.secondEmpId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PairedEmployeesWorkedLongestInProject [firstEmpId=" + firstEmpId + ", secondEmpId=" + secondEmpId
				+ ", projectId=" + projectId  + "]";
	}
	
}

