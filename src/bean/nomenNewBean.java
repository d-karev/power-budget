package bean;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import customEnum.ActionOutcome;
import model.Budgetnomen;

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
		//TODO business logic
		
		FacesContext context = FacesContext.getCurrentInstance();		
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		
		context.addMessage(null, new FacesMessage(bundle.getString("commonEntrySaveSuccess")));
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		if (makeNew) {
			return ActionOutcome.outcomeSuccessAndNew;
		} else {		
			return ActionOutcome.outcomeSuccess;
		}
	}	
}
