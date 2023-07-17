package com.epharmacy.dto;

import com.epharmacy.entity.PrimePlans;

//Write the necessary annotations to validate the fields 
public class PrimePlansDTO {
	
	private Integer planId;
	private String planName;
	private String planDescription;
	
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanDescription() {
		return planDescription;
	}
	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}
	
	public PrimePlansDTO entityToDto(PrimePlans plans) {
		PrimePlansDTO primePlansDTO = new PrimePlansDTO();
		primePlansDTO.setPlanDescription(plans.getPlanDescription());
		primePlansDTO.setPlanId(plans.getPlanId());
		primePlansDTO.setPlanName(plans.getPlanName());
		return primePlansDTO;
	}
	
}

