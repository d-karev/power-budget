package validators;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import model.Budgetnomen;
import model.BudgetnomenHome;

public class NomenNameValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent comp, Object value) throws ValidatorException {
		String nomenName = (String)value;
		int userId = (int)(comp.getValueExpression("userId").getValue(context.getELContext()));
		Object entryIdObj = comp.getValueExpression("entryId").getValue(context.getELContext());
		int entryId = entryIdObj == null ? 0 : (int)entryIdObj;
		
		if (nomenExists(nomenName, userId, entryId)) {
			ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
			String messageStr = String.format(bundle.getString("commonEntryExists"), nomenName);
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, messageStr, null));
		}
	}
	
	private boolean nomenExists(String nomenName, int userId, int entryId) {		
		Budgetnomen nomen = new Budgetnomen();
		nomen.setUserid(userId);
		nomen.setName(nomenName);
		
		BudgetnomenHome nomenDb = new BudgetnomenHome();
		List<Budgetnomen> nomenList = nomenDb.findByExample(nomen);
		
		return !nomenList.isEmpty() && (entryId < 1 || nomenList.get(0).getIdbudgetnomens() != entryId);
	}

}
