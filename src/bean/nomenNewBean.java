package bean;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import customEnum.ActionOutcome;
import model.Budgetnomen;
import model.BudgetnomenHome;

public class nomenNewBean {	
	private Budgetnomen entry = new Budgetnomen();
	private bean.userAuth userAuth;	
	
	public Budgetnomen getEntry() {
		return entry;
	}
	
	public void setEntry(Budgetnomen value) {
		entry = value;
	}
	
	public bean.userAuth getUserAuth() {
		return userAuth;
	}
	
	public void setUserAuth(bean.userAuth userAuth) {
		this.userAuth = userAuth;
	}

	public String saveEntry(Boolean makeNew) {	
		String fMsg;
		Severity fSvr;
		boolean success;
		
		FacesContext context = FacesContext.getCurrentInstance();		
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		
		try {		
			if (entry.getUserid() == null || entry.getUserid() < 1)
				entry.setUserid(userAuth.getLoggedUser().getIduser());
			BudgetnomenHome nomenDb = new BudgetnomenHome();
			nomenDb.persist(entry);						
			
			success = true;
			fSvr = FacesMessage.SEVERITY_INFO;
			fMsg = bundle.getString("commonEntrySaveSuccess");									
		} catch (Exception ex) {
			success = false;
			fSvr = FacesMessage.SEVERITY_ERROR;
			fMsg = bundle.getString("commonEntrySaveFail");
			System.out.println(ex.getMessage());	
		}
		
		context.addMessage(null, new FacesMessage(fSvr, fMsg, null));
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		if (success && makeNew) {
			return ActionOutcome.outcomeSuccessAndNew;
		} else if (success && !makeNew) {		
			return ActionOutcome.outcomeSuccess;
		} else {
			return ActionOutcome.outcomeFailure;
		}
	}	
}
