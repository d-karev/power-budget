package bean;

import java.time.LocalDate;

import customEnum.BudgetType;

public class generalBean {

	private final java.lang.Integer currentYear = LocalDate.now().getYear();
	private final String googleClientId = "126459043425-u66b22a0fgpgeja8eo9vd6fsjgqis7vs.apps.googleusercontent.com";
	private final BudgetType enumBudgetType = new BudgetType();
	
	public java.lang.Integer getCurrentYear() {
		return currentYear;
	}

	public String getGoogleClientId() {
		return googleClientId;
	}
	
	public BudgetType getEnumBudgetType() {
		return enumBudgetType;
	}
}
