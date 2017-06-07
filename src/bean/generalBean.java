package bean;

import java.time.LocalDate;

public class generalBean {

	private final java.lang.Integer currentYear = LocalDate.now().getYear();
	
	public java.lang.Integer getCurrentYear() {
		return currentYear;
	}
}
