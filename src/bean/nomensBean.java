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
import model.Budgetnomen;
import model.BudgetnomenHome;

public class nomensBean {

	private bean.userAuth userAuth;
	private List<Budgetnomen> nomenList = new ArrayList<Budgetnomen>();
	
	public bean.userAuth getUserAuth() {
		return userAuth;
	}
	
	public void setUserAuth(bean.userAuth userAuth) {
		this.userAuth = userAuth;
	}
	
	public List<Budgetnomen> getNomenList() {
		return nomenList;
	}
	
	@PostConstruct
	public void init() {
		loadNomens();
	}
	
	private void loadNomens() {
		Budgetnomen bNomen = new Budgetnomen();
		bNomen.setUserid(userAuth.getLoggedUser().getIduser());
		
		BudgetnomenHome bNomenDb = new BudgetnomenHome();
		nomenList = bNomenDb.findByExample(bNomen);
	}
	
	public String getDeleteConfirmationMessage(String name) {
		return String.format(
				userAuth.getSessionLocale().getMsgResource().getString("dialogDeleteEntry"), 
				name);
	}
	
	public String deleteEntry(int id) {		
		String fMsg;
		Severity fSvr;
		boolean success;
		
		FacesContext context = FacesContext.getCurrentInstance();	
		
		try {
			BudgetnomenHome bNomenDb = new BudgetnomenHome();
			bNomenDb.deleteNomenAndUpdateEntries(id);
			loadNomens();
			
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
	
	public String getStringValueForType(int nomenType) {
		String result = "";
		
		if (nomenType == userAuth.getGeneralBean().getEnumBudgetType().getIncome())
			return userAuth.getSessionLocale().getMsgResource().getString("commonIncome");
		else if (nomenType == userAuth.getGeneralBean().getEnumBudgetType().getExpense())
			return userAuth.getSessionLocale().getMsgResource().getString("commonExpense");
		
		return result;
	}
}
