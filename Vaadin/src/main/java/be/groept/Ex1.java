package be.groept;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Ex1 extends HorizontalLayout {

	{
		setSpacing(true);
	}

	public Ex1() {

		Button b = new Button("Klikkerdeklik");
		Label l = new Label("Haaaaiii");
		l.setVisible(false);

		b.addClickListener(event -> l.setVisible(!l.isVisible()));

		addComponent(b);
		addComponent(l);
	}

}
