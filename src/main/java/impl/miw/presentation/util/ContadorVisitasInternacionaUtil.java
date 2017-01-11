package impl.miw.presentation.util;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class ContadorVisitasInternacionaUtil {

	@Autowired
    private ServletContext context;
	@Autowired
    private MessageSource messageSource;
	
	public void setServletContext(ServletContext servletContext) {
	     this.context = servletContext;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}
	
	public int getVisitas(){
		return (Integer) context.getAttribute("contador");
	}
	
}
