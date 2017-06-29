package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import customEnum.ActionOutcome;
import model.Budgetentry;
import model.BudgetentryHome;

public class budgetMainBean {

	private bean.userAuth userAuth;
	private List<Budgetentry> entryList = new ArrayList<Budgetentry>();
	
	public bean.userAuth getUserAuth() {
		return userAuth;
	}
	
	public void setUserAuth(bean.userAuth userAuth) {
		this.userAuth = userAuth;
	}

	public List<Budgetentry> getEntryList() {
		return entryList;
	}
	
	@PostConstruct
	public void init() {
		loadEntries();
	}
	
	private void loadEntries() {
		Budgetentry bEntry = new Budgetentry();
		bEntry.setUserid(userAuth.getLoggedUser().getIduser());
		
		BudgetentryHome bNomenDb = new BudgetentryHome();
		entryList = bNomenDb.findByExample(bEntry);
	}
	
	public String getDeleteConfirmationMessage(int id) {
		String descr = "";
		
		return String.format(
				userAuth.getSessionLocale().getMsgResource().getString("dialogDeleteEntry"), 
				descr);
	}
	
	public String getStringValueForType(int nomenType) {
		String result = "";
		
		if (nomenType == userAuth.getGeneralBean().getEnumBudgetType().getIncome())
			return userAuth.getSessionLocale().getMsgResource().getString("commonIncome");
		else if (nomenType == userAuth.getGeneralBean().getEnumBudgetType().getExpense())
			return userAuth.getSessionLocale().getMsgResource().getString("commonExpense");
		
		return result;
	}
	
	public String deleteEntry(int id) {		
		String fMsg;
		Severity fSvr;
		boolean success;
		
		FacesContext context = FacesContext.getCurrentInstance();	
		
		try {			
			BudgetentryHome bEntryDb = new BudgetentryHome();
			Budgetentry bEntry = bEntryDb.findById(id);
			bEntryDb.delete(bEntry);
			loadEntries();
			
			success = true;
			fSvr = FacesMessage.SEVERITY_INFO;
			fMsg = userAuth.getSessionLocale().getMsgResource().getString("commonOperationSuccess");	
		} catch (Exception ex) {
			success = false;
			fSvr = FacesMessage.SEVERITY_ERROR;
			fMsg = userAuth.getSessionLocale().getMsgResource().getString("commonEntryDeleteFail");
			System.out.println(ex.getMessage());
		}
		
		context.addMessage(null, new FacesMessage(fSvr, fMsg, null));
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		if (success) {		
			return ActionOutcome.outcomeSuccess;
		} else {
			return ActionOutcome.outcomeFailure;
		}
	}
}
