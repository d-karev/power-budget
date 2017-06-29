package bean;

import customEnum.ActionOutcome;
import model.Budgetentry;
import model.BudgetentryHome;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import bean.userAuth;

public class budgetNewBean {

	private userAuth userAuth;
	private int id;
	private boolean editMode = false;	
	
	private Budgetentry entry = new Budgetentry();
	
	public boolean getEditMode() {
		return editMode;
	}
	
	public userAuth getUserAuth() {
		return userAuth;
	}
	
	public void setUserAuth(userAuth authBean) {
		this.userAuth = authBean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Budgetentry getEntry() {
		return entry;
	}

	public void setEntry(Budgetentry entry) {
		this.entry = entry;
	}
	
	public String prepareEdit() {
		if (getId() < 1)
			return ActionOutcome.outcomeEmpty;
		
		Budgetentry findEntry = null;
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			BudgetentryHome findEntryDb = new BudgetentryHome();
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
	
	public String saveEntry(boolean makeNew) {
		String fMsg;
		Severity fSvr;
		boolean success;	
		
		try {		
			if (entry.getUserid() == null || entry.getUserid() < 1)
				entry.setUserid(userAuth.getLoggedUser().getIduser());
			BudgetentryHome nomenDb = new BudgetentryHome();
			if(entry.getIdbudgetentry() != null && entry.getIdbudgetentry() > 1)
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
