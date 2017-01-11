package impl.miw.presentation;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	
	@Autowired
    private ServletContext context;
	
	public void setServletContext(ServletContext servletContext) {
	     this.context = servletContext;
	}

	@RequestMapping("/")
	public String welcome(Model m) {
		//Primera vez que se ejecuta la aplicacion
		if(context.getAttribute("contador") == null)
			context.setAttribute("contador", 1);
		//Proximas veces de ejecucion de la aplicacion
		else{
			int visitas = (Integer) context.getAttribute("contador");
			context.setAttribute("contador", visitas + 1);
		}
		//System.out.println(context.getAttribute("contador"));
		
		return "redirect:searchVuelos";
	}
	
}
