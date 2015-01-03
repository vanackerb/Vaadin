package be.groept.ui;

import java.math.BigDecimal;

import be.groept.vaadin.model.Order;
import be.groept.vaadin.model.OrderSearchCriteria;
import be.groept.vaadin.model.OrderServiceImpl;
import be.groept.vaadin.model.Product;

import com.vaadin.data.util.converter.StringToBigDecimalConverter;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

public class SearchOrder extends Template {

	int products = 0;
	OrderServiceImpl osimp = new OrderServiceImpl();
	int i = 0;

	// create table
	Table t = new Table("Orders found");

	@Override
	protected Component getBody() {

		// Creation of all the components inside the body
		Panel searchpanel = new Panel("Search orders");
		GridLayout gl = new GridLayout(4, 5);
		Label mina = new Label("Minimum Amount:");
		TextField tfmina = new TextField();
		Label maxa = new Label("Maximum Amount:");
		TextField tfmaxa = new TextField();
		Label nuprod = new Label("Number of products:");
		TextField tfnuprod = new TextField();
		Label del = new Label("Delivered:");
		CheckBox cbdel = new CheckBox();
		cbdel.setRequired(true);
		Label proname = new Label("Product name:");
		TextField tfproname = new TextField();
		Label email = new Label("E-mail:");
		TextField tfemail = new TextField();
		Button clear = new Button("Clear");
		Button search = new Button("Search");

		// set the null-representation of the textfield
		tfmina.setNullRepresentation("");
		tfmaxa.setNullRepresentation("");
		tfnuprod.setNullRepresentation("");
		tfproname.setNullRepresentation("");
		tfemail.setNullRepresentation("");

		// set row expandratio
		gl.setRowExpandRatio(4, 15);
		gl.setWidth(75, Unit.PERCENTAGE);
		gl.setHeightUndefined();
		gl.setSpacing(true);
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addComponents(clear, search);
		buttons.setSpacing(true);

		// set stylename for styling via sass
		searchpanel.setStyleName("panel");
		gl.addStyleName("search");

		// add validators to the textfields
		tfmina.addValidator(new AbstractValidator<BigDecimal>("Must be decimal number") {

			@Override
			protected boolean isValidValue(BigDecimal value) {

				return true;
			}

			@Override
			public Class<BigDecimal> getType() {

				return BigDecimal.class;
			}

		});

		tfmaxa.addValidator(new AbstractValidator<BigDecimal>("Must be decimal number") {

			@Override
			protected boolean isValidValue(BigDecimal value) {

				return true;
			}

			@Override
			public Class<BigDecimal> getType() {

				return BigDecimal.class;
			}

		});

		tfnuprod.addValidator(new IntegerRangeValidator("Value must be an integer number!", 0, 999999999));
		tfemail.addValidator(new EmailValidator("Must be a valid e-mail address"));

		// set converter to textfields
		tfmina.setConverter(new StringToBigDecimalConverter());
		tfmaxa.setConverter(new StringToBigDecimalConverter());
		tfnuprod.setConverter(new StringToIntegerConverter());

		// set to immediate for immediate validation when focus is lost (eg, user clicks somewhere else in the screen)
		tfmina.setImmediate(true);
		tfmaxa.setImmediate(true);
		tfnuprod.setImmediate(true);
		tfemail.setImmediate(true);

		// start with values of textfields and validators set to null
		tfmina.setValue(null);
		tfmaxa.setValue(null);
		tfnuprod.setValue(null);
		cbdel.setValue(null);
		tfproname.setValue(null);
		tfemail.setValue(null);

		// create column headers and fill table with start data
		t.addContainerProperty("orderId", String.class, null);
		t.addContainerProperty("customerId", String.class, null);
		t.addContainerProperty("#producten", Integer.class, null);
		t.addContainerProperty("Delivered?", Boolean.class, null);
		t.addContainerProperty("DeliveryDays", Integer.class, null);
		t.addContainerProperty("Total Price", BigDecimal.class, null);
		t.addContainerProperty("Details", Button.class, null);

		fillTable();

		// add components to layout
		gl.addComponents(mina, tfmina, maxa, tfmaxa, nuprod, tfnuprod, del, cbdel, proname, tfproname, email, tfemail, buttons);
		gl.addComponent(t, 0, 4, 3, 4);

		searchpanel.setImmediate(true);
		searchpanel.setContent(gl);

		// if button "clear" is clicked all textfields & the checkbox become null again
		clear.addClickListener(event -> {

			t.removeAllItems();
			tfmina.setValue(null);
			tfmaxa.setValue(null);
			tfnuprod.setValue(null);
			cbdel.setValue(null);
			tfproname.setValue(null);
			tfemail.setValue(null);
			fillTable();
		});

		// when search is clicked, results SHOULD come up...
		search.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				int y = 0;
				OrderSearchCriteria ocrit = new OrderSearchCriteria();
				ocrit.setMinAmount((BigDecimal) tfmina.getConvertedValue());
				ocrit.setMaxAmount((BigDecimal) tfmaxa.getConvertedValue());
				ocrit.setNumberOfProducts((Integer) tfnuprod.getConvertedValue());
				ocrit.setDelivered(cbdel.getValue());
				ocrit.setProductName(tfproname.getValue());
				ocrit.setEmail(tfemail.getValue());
				t.removeAllItems();
				for (Order o : osimp.searchOrders(ocrit)) {

					// count number of products from order
					for (Product p : o.getProducts()) {
						products++;
					}
					y++;

					// create a button to go to detail of the order
					Button detail = new Button("Detail");

					t.addItem(
							new Object[] { o.getOrderId(), o.getCustomerId(), products, o.isDelivered(), o.getDeliveryDays(),
									o.getTotalOrderPrice(), detail }, y);

					// add navigation to detail page
					detail.addClickListener(new ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {

							getUI().getSession().setAttribute("order", o);
							UI.getCurrent().getNavigator().navigateTo("OrderDetail");
						}
					});

					t.setPageLength(t.size());
					products = 0;
				}

			}
		});

		// return the panel to be placed in the body
		return searchpanel;
	}

	public void fillTable() {
		// fill table with data from list
		for (Order o : osimp.getAllOrdersForCustomer()) {

			// count number of products from order
			for (Product p : o.getProducts()) {
				products++;
			}
			i++;

			// create a button to go to detail of the order
			Button detail = new Button("Detail");

			// add items to table
			t.addItem(
					new Object[] { o.getOrderId(), o.getCustomerId(), products, o.isDelivered(), o.getDeliveryDays(),
							o.getTotalOrderPrice(), detail }, i);

			// add navigation to detail page
			detail.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {

					getUI().getSession().setAttribute("order", o);
					UI.getCurrent().getNavigator().navigateTo("OrderDetail");
				}
			});

			products = 0;

			// Show exactly the currently contained rows (items)
			t.setPageLength(t.size());
			t.setImmediate(true);
		}
	}
}
