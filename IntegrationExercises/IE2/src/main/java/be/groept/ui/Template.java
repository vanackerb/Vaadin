package be.groept.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public abstract class Template extends VerticalLayout implements View {

	{
		setSpacing(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {

		Image i = new Image("", new ThemeResource("search.jpg"));
		Label l = new Label("Welcome to search-app");

		VerticalLayout header = new VerticalLayout();
		header.addComponent(i);
		header.addComponent(l);
		header.setWidth(100, Unit.PERCENTAGE);
		header.addStyleName("header");

		Label l2 = new Label("(c) Björn Van Acker");
		Label l3 = new Label("It is ILLEGAL to make copies of this website!");
		l2.setSizeUndefined();
		l3.setSizeUndefined();
		VerticalLayout v = new VerticalLayout(l2, l3);
		HorizontalLayout footer = new HorizontalLayout();
		footer.addComponent(v);
		footer.setWidth(100, Unit.PERCENTAGE);
		footer.addStyleName("footer");

		addComponent(header);
		addComponent(getBody());
		addComponent(footer);

	}

	protected abstract Component getBody();

}
