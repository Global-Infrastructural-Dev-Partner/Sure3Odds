package com.gidp.sure3odds.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sure_member_advisers")
public class MemberAdvisers {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private  Members memberUserID;
	
	@ManyToOne
	private Advisers adviserUserID;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the memberUserID
	 */
	public Members getMemberUserID() {
		return memberUserID;
	}

	/**
	 * @param memberUserID the memberUserID to set
	 */
	public void setMemberUserID(Members memberUserID) {
		this.memberUserID = memberUserID;
	}

	/**
	 * @return the adviserUserID
	 */
	public Advisers getAdviserUserID() {
		return adviserUserID;
	}

	/**
	 * @param adviserUserID the adviserUserID to set
	 */
	public void setAdviserUserID(Advisers adviserUserID) {
		this.adviserUserID = adviserUserID;
	}

	
	
	
}
