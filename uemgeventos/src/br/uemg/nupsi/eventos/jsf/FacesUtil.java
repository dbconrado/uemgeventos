package br.uemg.nupsi.eventos.jsf;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	public static final Logger LOGGER = Logger.getGlobal();
	
	public static void logException(String msgGeral, Throwable e) {
		LOGGER.log(Level.SEVERE, msgGeral, e);
		
		String det = e.getClass().getSimpleName() + ":" + e.getMessage();
		
		addMessage(msgGeral, det);
	}
	public static void addMessage(String _id, String sum, String det) {
		FacesContext.getCurrentInstance().addMessage(_id, new FacesMessage(sum, det));
	}
	
	public static void addMessage(String sum, String det) {
		addMessage(null, sum, det);
	}
	
	public static void addMessage(String sum) {
		addMessage(sum, null);
	}
	
	public static void keepMessages() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public static boolean isNullOrEmpty(String s) {
		return s == null || "".equals(s);
	}
	
}
