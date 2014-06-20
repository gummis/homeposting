package homeposting.app.common.security;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import homeposting.app.domain.entities.Subsystem;
import homeposting.app.domain.entities.User;
import homeposting.app.web.SessionBean;

public class Security {
	public static void logged(){
		if(SessionBean.getInstance().getUser() == null){
			redirectMain();
			throw new RuntimeException("Użytkownik musi być zalogowany w celu przeglądania tej strony");
		}
	}
	public static void unlogged() {
		if(SessionBean.getInstance().getUser() != null)
			throw new RuntimeException("Użytkownik musi być wylogowany w celu przeglądania tej strony");
	}
	
	public static void choiceOwnSubsystem(){
		User user = SessionBean.getInstance().getUser();
		if(user == null)
			throw new RuntimeException("Użytkownik musi być zalogowany w celu przeglądania tej strony");
		Subsystem subsystem = SessionBean.getInstance().getSelectedSubsystem();
		if(subsystem == null)
			throw new RuntimeException("Użytkownik musi mieć wybrany podsystem w celu przeglądania tej strony");
		if(!subsystem.getOwner().getId().equals(user.getId())){
			throw new RuntimeException("Użytkownik musi mieć wybrany własny podsystem w celu przeglądania tej strony");
		}
	}


	private static void redirectMain(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/");
		} catch (IOException e) {
			throw new RuntimeException("Nieudane przekierowanie na stronę główną");
		}
	}
}
