package be.groept;

import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class Ex2 extends HorizontalLayout {

	{
		setSpacing(true);
	}

	public Ex2() {

		/*
		 * final SearchCriteria searchCriteria = new SearchCriteria(); MethodProperty p = new
		 * MethodProperty<Integer>(searchCriteria, "age"); final TextField tf = new TextField(p);
		 */

		final TextField tf = new TextField();
		tf.setNullRepresentation("");
		tf.setValidationVisible(true);
		tf.setConverter(new StringToIntegerConverter());
		tf.addValidator(new IntegerRangeValidator("foutieve ingave", 0, 999));
		tf.setImmediate(true);

		final Button b = new Button("Klik");
		final Label l = new Label();

		l.setVisible(false);

		b.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				if (tf.isValid()) {
					l.setValue(tf.getValue());
					l.setVisible(true);
				} else {
					l.setValue("");
				}

			}
		});
		/*
		 * @Override public void buttonClick(ClickEvent event) { if (tf.isValid()) { l.setValue("" +
		 * searchCriteria.getAge()); l.setVisible(true); } else { l.setValue(""); }
		 */

		// b.addClickListener(event -> l.setVisible(!l.isVisible()));

		addComponent(tf);
		addComponent(b);
		addComponent(l);

	}

}
/*
 * public class SearchCriteria {
 *
 * public int age;
 *
 * public int getAge(){ return age; }
 *
 * public void setAge(int age) { this.age= age } }
 */

