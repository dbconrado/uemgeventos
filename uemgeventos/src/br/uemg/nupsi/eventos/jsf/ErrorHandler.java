package br.uemg.nupsi.eventos.jsf;

import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
/**
 * Copied from: http://www.javabeat.net/jsf-custom-error-pages/
 * some additions have been made.
 * @author DB
 *
 */
public class ErrorHandler {
	public String getStatusCode() {

		String val = String.valueOf((Integer) FacesContext.getCurrentInstance()
				.getExternalContext().

				getRequestMap().get("javax.servlet.error.status_code"));

		return val;

	}

	public String getMessage() {

		String val = (String) FacesContext.getCurrentInstance()
				.getExternalContext().

				getRequestMap().get("javax.servlet.error.message");

		return val;

	}

	public String getExceptionType() {

		String val = FacesContext.getCurrentInstance().getExternalContext().

		getRequestMap().get("javax.servlet.error.exception_type").toString();

		return val;

	}

	public String getException() {

		String val = (String) ((Exception) FacesContext.getCurrentInstance()
				.getExternalContext().

				getRequestMap().get("javax.servlet.error.exception"))
				.toString();

		return val;

	}

	public String getRequestURI() {

		return (String) FacesContext.getCurrentInstance().getExternalContext().

		getRequestMap().get("javax.servlet.error.request_uri");

	}

	public String getServletName() {

		return (String) FacesContext.getCurrentInstance().getExternalContext().

		getRequestMap().get("javax.servlet.error.servlet_name");

	}
	
	private String[] messages = new String[]{
			"\"ai, desculpa!\", diz o servidor",
			"Ops! Acho que o servidor trope�ou =/",
			"Desculpe. N�o � voc�, somos n�s =(",
			"Houston! Temos um problema! ��'",
			"Ouch!!... Essa doeu =[",
			"Oh n�o! =((",
			"Servidor! Que coisa feia! >("};
	
	public String getRandomMessage() {
		return messages[new Random().nextInt(messages.length)];
	}

}
