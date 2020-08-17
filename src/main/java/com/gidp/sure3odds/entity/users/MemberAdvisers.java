package com.gidp.sure3odds.entity.users;

import com.gidp.sure3odds.entity.users.Users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sure_member_advisers")
public class MemberAdvisers {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_member_advisers_seq")
	@SequenceGenerator(name = "sure_member_advisers_seq", sequenceName = "sure_member_advisers_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name ="memberuserid")
	private Users memberUserID;

	@ManyToOne
	@JoinColumn(name ="adviseruserid")
	private Users adviserUserID;

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

	public Users getMemberUserID() {
		return memberUserID;
	}

	public void setMemberUserID(Users memberUserID) {
		this.memberUserID = memberUserID;
	}

	public Users getAdviserUserID() {
		return adviserUserID;
	}

	public void setAdviserUserID(Users adviserUserID) {
		this.adviserUserID = adviserUserID;
	}

	
}
