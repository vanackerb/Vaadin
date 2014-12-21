package be.groept;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class Ex3 extends VerticalLayout {

	{
		setSpacing(true);
	}

	private int counter = 0;
	public String value;

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Ex3() {

		HorizontalLayout h1 = new HorizontalLayout();
		final Button b1 = new Button("Klik 1");
		final Label l1 = new Label();
		h1.setSpacing(true);
		h1.addComponents(b1, l1);

		HorizontalLayout h2 = new HorizontalLayout();
		final Button b2 = new Button("Klik 2");
		final Label l2 = new Label();
		h2.setSpacing(true);
		h2.addComponents(b2, l2);

		HorizontalLayout h3 = new HorizontalLayout();
		final Button b3 = new Button("Klik 3");
		final Label l3 = new Label();
		h3.setSpacing(true);
		h3.addComponents(b3, l3);

		HorizontalLayout h4 = new HorizontalLayout();
		final Button b4 = new Button("Update Everything");
		h4.setSpacing(true);
		h4.addComponent(b4);

		b1.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				increment();
				value = "" + counter;
				l1.setValue(value);
				l1.setVisible(true);
			}

		});

		b2.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				increment();
				value = "" + counter;
				l2.setValue(value);
				l2.setVisible(true);
			}

		});

		b3.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				increment();
				value = "" + counter;
				l3.setValue(value);
				l3.setVisible(true);
			}

		});

		b4.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				l1.setValue(value);
				l2.setValue(value);
				l3.setValue(value);
			}

		});

		addComponent(h1);
		addComponent(h2);
		addComponent(h3);
		addComponent(h4);
	}

	public void increment() {
		counter++;
	}

}
