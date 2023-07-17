package com.epharmacy.entity;

import com.epharmacy.dto.PrimePlansDTO;

import jakarta.persistence.*;

@Entity
@Table(name="PRIME_PLANS")
public class PrimePlans {
	@Id
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

	public PrimePlans dtoToEntity(PrimePlansDTO plansDTO) {
		PrimePlans primePlans = new PrimePlans();
		primePlans.setPlanDescription(plansDTO.getPlanDescription());
		primePlans.setPlanId(plansDTO.getPlanId());
		primePlans.setPlanName(plansDTO.getPlanName());
		return primePlans;
	}
	
	
}
