package bean;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

public class localeBean {	
    private Locale locale;
	private ResourceBundle msgResource;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        System.out.println("Locale initialized to: " + locale.getLanguage());
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
    	String localeStr = locale.getLanguage();
    	//System.out.println("Current locale: " + localeStr);		
        return localeStr;
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext cont = FacesContext.getCurrentInstance(); 
        cont.getViewRoot().setLocale(locale);
        setMsgResource(cont.getApplication().getResourceBundle(cont, "msg"));
        System.out.println("Locale set to: " + locale.getLanguage());
    }

	
	public ResourceBundle getMsgResource() {
		return msgResource;
	}

	
	public void setMsgResource(ResourceBundle msgResource) {
		this.msgResource = msgResource;
	}


}
