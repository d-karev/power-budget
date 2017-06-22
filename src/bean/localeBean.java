package bean;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

public class localeBean {	
    private Locale locale;

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
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        System.out.println("Locale set to: " + locale.getLanguage());
    }
}
