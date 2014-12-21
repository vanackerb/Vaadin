package be.groept;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("reindeer")
public class MyUi extends UI {

	@Override
	protected void init(VaadinRequest request) {

		// setContent(new Ex1());
		// setContent(new Ex2());
		// setContent(new Ex3());
		// setContent(new Ex4());
		// setContent(new Ex5());
		setContent(new Ex6());
	}

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyUi.class)
	public static class Servlet extends VaadinServlet {
	}
}
