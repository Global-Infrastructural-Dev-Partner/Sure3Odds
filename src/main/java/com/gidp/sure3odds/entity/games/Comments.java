package com.gidp.sure3odds.entity.games;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gidp.sure3odds.entity.users.Users;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sure_comments")
public class Comments {

	@Id
	@Column(name = "id", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_comments_seq")
	@SequenceGenerator(name = "sure_comments_seq", sequenceName = "sure_comments_seq", initialValue = 1, allocationSize = 1)
	private Long id;

	@Lob
	private String comments;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Africa/Lagos")
	private Date date;

	@JsonFormat(shape = JsonFormat.Shape.STRING,  pattern="HH:mm:ss", timezone = "Africa/Lagos")
	private Date time;

	@ManyToOne
	private Users user;

	@ManyToOne
	private Games game;

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
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Games getGame() {
		return game;
	}

	public void setGame(Games game) {
		this.game = game;
	}

	/**
	 *
	 */
	public Comments() {
		super();
	}

	/**
	 * @param id
	 * @param comments
	 * @param date
	 * @param time
	 */
	public Comments(Long id, String comments, Date date, Date time) {
		super();
		this.id = id;
		this.comments = comments;
		this.date = date;
		this.time = time;
	}

	/**
	 * @param comments
	 * @param date
	 * @param time
	 */
	public Comments(String comments, Date date, Date time) {
		super();
		this.comments = comments;
		this.date = date;
		this.time = time;
	}

}
