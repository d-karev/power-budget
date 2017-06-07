package bean;

import java.time.LocalDate;

public class generalBean {

	private final java.lang.Integer currentYear = LocalDate.now().getYear();
	private final java.lang.String devTeamName = "Power Rangers Team";
	private final java.lang.String productName = "Power Budget";
	
	public java.lang.Integer getCurrentYear() {
		return currentYear;
	}
	
	public java.lang.String getDevTeamName() {
		return devTeamName;
	}
	
	public java.lang.String getProductName() {
		return productName;
	}
}
