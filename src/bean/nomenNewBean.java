package bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import customEnum.ActionOutcome;
import model.Budgetnomen;
import model.BudgetnomenHome;

public class nomenNewBean {	
	private Budgetnomen entry = new Budgetnomen();
	private bean.userAuth userAuth;	
	
	private boolean editMode = false;	
	private int id;
	
	public boolean getEditMode() {
		return editMode;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int value) {
		id = value;
	}
	
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
	
	public String prepareEdit() {
		if (getId() < 1)
			return ActionOutcome.outcomeEmpty;
		
		Budgetnomen findEntry = null;
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			BudgetnomenHome findEntryDb = new BudgetnomenHome();
			findEntry = findEntryDb.findById(id);			
		} catch (Exception ex) {
			Severity fSvr = FacesMessage.SEVERITY_ERROR;
			String fMsg = userAuth.getSessionLocale().getMsgResource().getString("commonEntryLoadFail");
			context.addMessage(null, new FacesMessage(fSvr, fMsg, null));
			context.getExternalContext().getFlash().setKeepMessages(true);
			System.out.println(ex.getMessage());
			return ActionOutcome.outcomeFailure;
		}
		
		if (findEntry != null && findEntry.getUserid() == userAuth.getLoggedUser().getIduser()) {
			setEntry(findEntry);
			editMode = true;
			return ActionOutcome.outcomeSuccess;
		} else {
			Severity fSvr = FacesMessage.SEVERITY_ERROR;
			String fMsg = userAuth.getSessionLocale().getMsgResource().getString("commonEntryLoadFail");
			context.addMessage(null, new FacesMessage(fSvr, fMsg, null));
			context.getExternalContext().getFlash().setKeepMessages(true);
			return ActionOutcome.outcomeFailure;
		}				
	}
	
	public String saveEntry(Boolean makeNew) {	
		String fMsg;
		Severity fSvr;
		boolean success;						
		
		try {		
			if (entry.getUserid() == null || entry.getUserid() < 1)
				entry.setUserid(userAuth.getLoggedUser().getIduser());
			BudgetnomenHome nomenDb = new BudgetnomenHome();
			if(entry.getIdbudgetnomens() != null && entry.getIdbudgetnomens() > 1)
				nomenDb.merge(entry);
			else
				nomenDb.persist(entry);						
			
			success = true;
			fSvr = FacesMessage.SEVERITY_INFO;
			fMsg = userAuth.getSessionLocale().getMsgResource().getString("commonEntrySaveSuccess");									
		} catch (Exception ex) {
			success = false;
			fSvr = FacesMessage.SEVERITY_ERROR;
			fMsg = userAuth.getSessionLocale().getMsgResource().getString("commonEntrySaveFail");
			System.out.println(ex.getMessage());	
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		
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
