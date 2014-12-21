package be.groept;

import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class Ex5 extends VerticalLayout {

	{
		setSpacing(true);
	}

	public Ex5() {

		HorizontalLayout h1 = new HorizontalLayout();
		final TextField tf1 = new TextField();
		tf1.setNullRepresentation("");
		final TextField tf2 = new TextField();
		tf2.setNullRepresentation("");
		final TextField tf3 = new TextField();
		tf3.setNullRepresentation("");
		final Label l = new Label();
		h1.addComponents(tf1, tf2, tf3, l);

		HorizontalLayout h2 = new HorizontalLayout();
		final Button bs = new Button("Submit");

		bs.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				l.setValue("You have entered " + tf1.getValue() + ", " + tf2.getValue() + ", " + tf3.getValue());
			}
		});

		final Button bc = new Button("Clear");

		bc.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				tf1.setValue(null);
				tf2.setValue(null);
				tf3.setValue(null);
				l.setValue(null);

			}
		});

		h2.addComponents(bs, bc);

		addComponents(h1, h2);

		tf1.addValidator(new StringLengthValidator("Length must be at least 1 character", 1, 1, false));
		;
	}

}
