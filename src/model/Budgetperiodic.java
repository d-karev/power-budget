package model;
// Generated Jun 29, 2017 5:56:37 PM by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.util.Date;

/**
 * Budgetperiodic generated by hbm2java
 */
public class Budgetperiodic implements java.io.Serializable {

	private Integer idbudgetperiodic;
	private Integer userid;
	private Integer nomenid;
	private Integer type;
	private String comment;
	private Date initialdate;
	private Integer period;
	private Integer periodtype;
	private Integer repeatcountmax;
	private Integer repeatcountcurrent;
	private BigDecimal moneyvalue;

	public Budgetperiodic() {
	}

	public Budgetperiodic(Integer userid, Integer nomenid, Integer type, String comment, Date initialdate,
			Integer period, Integer periodtype, Integer repeatcountmax, Integer repeatcountcurrent,
			BigDecimal moneyvalue) {
		this.userid = userid;
		this.nomenid = nomenid;
		this.type = type;
		this.comment = comment;
		this.initialdate = initialdate;
		this.period = period;
		this.periodtype = periodtype;
		this.repeatcountmax = repeatcountmax;
		this.repeatcountcurrent = repeatcountcurrent;
		this.moneyvalue = moneyvalue;
	}

	public Integer getIdbudgetperiodic() {
		return this.idbudgetperiodic;
	}

	public void setIdbudgetperiodic(Integer idbudgetperiodic) {
		this.idbudgetperiodic = idbudgetperiodic;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getNomenid() {
		return this.nomenid;
	}

	public void setNomenid(Integer nomenid) {
		this.nomenid = nomenid;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getInitialdate() {
		return this.initialdate;
	}

	public void setInitialdate(Date initialdate) {
		this.initialdate = initialdate;
	}

	public Integer getPeriod() {
		return this.period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getPeriodtype() {
		return this.periodtype;
	}

	public void setPeriodtype(Integer periodtype) {
		this.periodtype = periodtype;
	}

	public Integer getRepeatcountmax() {
		return this.repeatcountmax;
	}

	public void setRepeatcountmax(Integer repeatcountmax) {
		this.repeatcountmax = repeatcountmax;
	}

	public Integer getRepeatcountcurrent() {
		return this.repeatcountcurrent;
	}

	public void setRepeatcountcurrent(Integer repeatcountcurrent) {
		this.repeatcountcurrent = repeatcountcurrent;
	}

	public BigDecimal getMoneyvalue() {
		return this.moneyvalue;
	}

	public void setMoneyvalue(BigDecimal moneyvalue) {
		this.moneyvalue = moneyvalue;
	}

}
