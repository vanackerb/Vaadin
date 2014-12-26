package be.groept.ui;

import java.math.BigDecimal;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

public class SearchOrder extends Template {

	@Override
	protected Component getBody() {

		Panel searchpanel = new Panel("Search orders");
		searchpanel.setStyleName("panel");
		GridLayout gl = new GridLayout(4, 5);
		Label mina = new Label("Minimum Amount:");
		TextField tfmina = new TextField();
		tfmina.setNullRepresentation("");
		Label maxa = new Label("Maximum Amount:");
		TextField tfmaxa = new TextField();
		tfmaxa.setNullRepresentation("");
		Label nuprod = new Label("Number of products:");
		TextField tfnuprod = new TextField();
		tfnuprod.setNullRepresentation("");
		Label del = new Label("Delivered:");
		TextField tfdel = new TextField();
		tfdel.setNullRepresentation("");
		Label proname = new Label("Product name:");
		TextField tfproname = new TextField();
		tfproname.setNullRepresentation("");
		Label email = new Label("E-mail:");
		TextField tfemail = new TextField();
		tfemail.setNullRepresentation("");
		gl.setRowExpandRatio(4, 4);
		Button clear = new Button("Clear");
		Button search = new Button("Search");
		gl.setWidth(75, Unit.PERCENTAGE);
		gl.setSpacing(true);
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addComponents(clear, search);
		buttons.setSpacing(true);

		Label empty = new Label(" ");
		Label empty1 = new Label(" ");
		Label empty2 = new Label(" ");

		Table t = new Table("Orders");
		t.setVisible(false);
		t.addContainerProperty("orderId", String.class, null);
		t.addContainerProperty("customerId", String.class, null);
		t.addContainerProperty("#producten", List.class, null);
		t.addContainerProperty("Delivered?", boolean.class, null);
		t.addContainerProperty("DeliveryDays", int.class, null);
		t.addContainerProperty("Total Price", BigDecimal.class, null);

		gl.addComponents(mina, tfmina, maxa, tfmaxa, nuprod, tfnuprod, del, tfdel, proname, tfproname, email, tfemail, buttons);
		gl.addComponent(t, 0, 4, 3, 4);
		// gl.addComponent(t, 1, 5);
		gl.addStyleName("search");

		searchpanel.setImmediate(true);
		searchpanel.setContent(gl);

		clear.addClickListener(event -> {

			tfmina.setValue(null);
			tfmaxa.setValue(null);
			tfnuprod.setValue(null);
			tfdel.setValue(null);
			tfproname.setValue(null);
			tfemail.setValue(null);
		});

		search.addClickListener(event -> t.setVisible(true));

		return searchpanel;
	}
}
