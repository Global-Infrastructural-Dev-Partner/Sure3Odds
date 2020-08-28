package com.gidp.sure3odds.entity.users;

import javax.persistence.*;

@Entity
@Table(name = "sure_member_advisers")
public class MemberAdvisers {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_member_advisers_seq")
	@SequenceGenerator(name = "sure_member_advisers_seq", sequenceName = "sure_member_advisers_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	private Users memberuser;

	@ManyToOne
	private Users adviseruser;

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

	public Users getMemberuser() {
		return memberuser;
	}

	public void setMemberuser(Users memberuser) {
		this.memberuser = memberuser;
	}

	public Users getAdviseruser() {
		return adviseruser;
	}

	public void setAdviseruser(Users adviseruser) {
		this.adviseruser = adviseruser;
	}
}
