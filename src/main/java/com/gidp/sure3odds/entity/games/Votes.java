package com.gidp.sure3odds.entity.games;

import com.gidp.sure3odds.entity.users.Users;

import javax.persistence.*;

@Entity
@Table(name = "sure_votes")
public class Votes {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_votes_seq")
	@SequenceGenerator(name = "sure_votes_seq", sequenceName = "sure_votes_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	private Games game;

	@ManyToOne
	private Users user;

	private long userVote;

	private long homeVote;

	private long awayVote;

	private long drawVote;

	/**
	 *
	 */
	public Votes() {
		super();
	}

	public Votes(long userVote, long homeVote, long awayVote, long drawVote) {
		this.userVote = userVote;
		this.homeVote = homeVote;
		this.awayVote = awayVote;
		this.drawVote = drawVote;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUserVote() {
		return userVote;
	}

	public void setUserVote(long userVote) {
		this.userVote = userVote;
	}

	public long getHomeVote() {
		return homeVote;
	}

	public void setHomeVote(long homeVote) {
		this.homeVote = homeVote;
	}

	public long getAwayVote() {
		return awayVote;
	}

	public void setAwayVote(long awayVote) {
		this.awayVote = awayVote;
	}

	public long getDrawVote() {
		return drawVote;
	}

	public void setDrawVote(long drawVote) {
		this.drawVote = drawVote;
	}

	public Games getGame() {
		return game;
	}

	public void setGame(Games game) {
		this.game = game;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
}
