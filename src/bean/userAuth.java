package bean;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Date;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import customEnum.ActionOutcome;
import model.User;
import model.UserHome;

public class userAuth {

	private bean.generalBean generalBean;
	private bean.localeBean sessionLocale;
	private model.User loggedUser; 
	
	private Boolean displayUserContent = false;
	private Boolean allowAccessToUserPages = false;		
	
	private String tokenId;	
	
	public Boolean getDisplayUserContent() {
		return displayUserContent;
	}
	
	public Boolean getAllowAccessToUserPages() {
		return allowAccessToUserPages;
	}

	public String getTokenId() {
		return tokenId;
	}
	
	public void setTokenId(String value) {
		tokenId = value;
	}
	
	public String signIn() {
		
		if (tokenId == null || tokenId == "")
			return ActionOutcome.outcomeFailure;
		
		String tkn = tokenId;
		tokenId = null;
		
		JsonFactory jsonFactory = new JacksonFactory();
		HttpTransport transport = new NetHttpTransport();
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
		    .setAudience(Collections.singletonList(generalBean.getGoogleClientId()))
		    .build();

		GoogleIdToken googleToken = null;
		try {
			googleToken = verifier.verify(tkn);
		} catch (GeneralSecurityException e) {			
			System.out.println("Security exception occured while validating token!");
			return ActionOutcome.outcomeFailure;
		} catch (IOException e) {
			System.out.println("IOException occured while validating token!");
			return ActionOutcome.outcomeFailure;
		}
		
		if (googleToken != null) {
			Payload payload = googleToken.getPayload();

			String userId = payload.getSubject();
			System.out.println("User ID: " + userId);
			
			User usr = new User();
			usr.setGoogleid(userId);
			
			UserHome usrDb = new UserHome();
			List<User> usrResult = usrDb.findByExample(usr);
			
			// Get profile information from payload
			String email = payload.getEmail();
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			
			if (usrResult.size() == 0) {
				usr.setGoogleid(userId);
			} else {
				usr = usrResult.get(0);
			}
			
			usr.setEmail(email);
			usr.setPicturelink(pictureUrl);
			usr.setUsername(name);
			usr.setRealnamefirst(givenName);
			usr.setRealnamelast(familyName);
			
			if (usr.getLocale() == null || usr.getLocale().equals(""))
				usr.setLocale(locale.equals("bg") ? "bg" : "en");									
			
			try {
				Date userDate = new Date();
				usr.setLastlogin(userDate);
				
				if (usrResult.size() == 0) {
					usr.setRegisterdate(userDate);
					usrDb.persist(usr);
				} else {
					usrDb.merge(usr);
				}
			} catch (Exception ex) {
				System.out.println("Exception was raised while persisting/merging user!");
				return ActionOutcome.outcomeFailure;
			}
			
			
			setLoggedUser(usr);
					
		} else {
			System.out.println("Invalid ID token.");
			return ActionOutcome.outcomeFailure;
		}
		
		displayUserContent = true;
		allowAccessToUserPages = true;
		
		System.out.println("Sign In completed successfully!");		
		return ActionOutcome.outcomeSuccess;
	}
	
	public String signOut() {
		displayUserContent = false;
		allowAccessToUserPages = false;
		tokenId = null;
		
		if (loggedUser != null) {
			// TODO If user info has to be updated before logging out - do it here...
			loggedUser = null;
		}
				
		System.out.println("Sign Out completed successfully!");
		return ActionOutcome.outcomeSuccess;
	}
	
	public bean.generalBean getGeneralBean() {
		return generalBean;
	}
	
	public void setGeneralBean(bean.generalBean generalBean) {
		this.generalBean = generalBean;
	}
	
	public model.User getLoggedUser() {
		return loggedUser;
	}
	
	public void setLoggedUser(model.User value) {
		loggedUser = value;
	}
	
	public bean.localeBean getSessionLocale() {
		return sessionLocale;
	}
	
	public void setSessionLocale(bean.localeBean sessionLocale) {
		this.sessionLocale = sessionLocale;
	}
}
