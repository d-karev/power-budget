package model;
// Generated Jun 5, 2017 1:55:43 AM by Hibernate Tools 5.2.3.Final

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Integer iduser;
	private String username;
	private String realnamefirst;
	private String realnamelast;

	public User() {
	}

	public User(String username, String realnamefirst, String realnamelast) {
		this.username = username;
		this.realnamefirst = realnamefirst;
		this.realnamelast = realnamelast;
	}

	public Integer getIduser() {
		return this.iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealnamefirst() {
		return this.realnamefirst;
	}

	public void setRealnamefirst(String realnamefirst) {
		this.realnamefirst = realnamefirst;
	}

	public String getRealnamelast() {
		return this.realnamelast;
	}

	public void setRealnamelast(String realnamelast) {
		this.realnamelast = realnamelast;
	}

}
