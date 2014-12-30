package be.groept.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

@Theme("ie2")
public class MyUi extends UI {

	@Override
	protected void init(VaadinRequest request) {

		// setContent(new SearchOrder());

		Panel panel = new Panel();
		panel.setSizeFull();

		Navigator navigator = new Navigator(getUI(), panel);
		setNavigator(navigator);
		getNavigator().addView("OrderDetail", OrderDetail.class);
		getNavigator().addView("SearchOrder", SearchOrder.class);
		navigator.navigateTo("SearchOrder");
		setContent(panel);
	}

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyUi.class)
	public static class Servlet extends VaadinServlet {
	}

}
