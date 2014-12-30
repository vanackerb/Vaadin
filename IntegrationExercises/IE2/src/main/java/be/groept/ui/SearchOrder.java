package be.groept.ui;

import java.math.BigDecimal;
import java.util.List;

import be.groept.vaadin.model.Order;
import be.groept.vaadin.model.OrderSearchCriteria;
import be.groept.vaadin.model.OrderServiceImpl;
import be.groept.vaadin.model.Product;

import com.vaadin.data.validator.DoubleRangeValidator;
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

	private String orderId;
	private String customerId;
	private List<Product> prods;
	private boolean delivered;
	private int deliveryDays;
	private BigDecimal total;
	int products = 0;

	@Override
	protected Component getBody() {

		OrderServiceImpl osimp = new OrderServiceImpl();
		int i = 0;

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
		tfmina.addValidator(new DoubleRangeValidator("Value must be a decimal number!", 0.00, 999999999.99));
		tfmaxa.addValidator(new DoubleRangeValidator("Value must be a decimal number!", 0.00, 999999999.99));
		tfnuprod.addValidator(new IntegerRangeValidator("Value must be an integer number!", 0, 999999999));
		tfemail.addValidator(new EmailValidator("Must be a valid e-mail address"));

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

		// create table with it's column headers
		Table t = new Table("Orders found");
		t.setVisible(false);

		t.addContainerProperty("orderId", String.class, null);
		t.addContainerProperty("customerId", String.class, null);
		t.addContainerProperty("#producten", Integer.class, null);
		t.addContainerProperty("Delivered?", Boolean.class, null);
		t.addContainerProperty("DeliveryDays", Integer.class, null);
		t.addContainerProperty("Total Price", BigDecimal.class, null);
		t.addContainerProperty("Details", Button.class, null);

		// fill table with data from list
		for (Order o : osimp.getAllOrdersForCustomer()) {

			// count number of products from order
			for (Product p : o.getProducts()) {
				products++;
			}
			i++;
			orderId = o.getOrderId();
			customerId = o.getCustomerId();
			prods = o.getProducts();
			delivered = o.isDelivered();
			deliveryDays = o.getDeliveryDays();
			total = o.getTotalOrderPrice();

			Button detail = new Button("Detail");
			// add items to table
			t.addItem(new Object[] { orderId, customerId, products, delivered, deliveryDays, total, detail }, i);

			Integer itemId = new Integer(i);
			/*
			 * Object newItemId = t.addItem(); Item row = t.getItem(newItemId);
			 * row.getItemProperty("orderId").setValue(o.getOrderId());
			 * row.getItemProperty("customerId").setValue(o.getCustomerId());
			 * row.getItemProperty("#producten").setValue(products);
			 * row.getItemProperty("Delivered?").setValue(o.isDelivered());
			 * row.getItemProperty("DeliveryDays").setValue(o.getDeliveryDays());
			 * row.getItemProperty("Total Price").setValue(o.getTotalOrderPrice()); Button detail = new
			 * Button("Detail"); row.getItemProperty("Details").setValue(detail);
			 */
			detail.setData(itemId);

			// add navigation to detail page
			detail.addClickListener(new ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {

					Integer iid = (Integer) event.getButton().getData();
					UI.getCurrent().getNavigator().navigateTo("OrderDetail");
				}
			});

			products = 0;
		}

		// Show exactly the currently contained rows (items)
		t.setPageLength(t.size());
		t.setImmediate(true);

		// add components to layout
		gl.addComponents(mina, tfmina, maxa, tfmaxa, nuprod, tfnuprod, del, cbdel, proname, tfproname, email, tfemail, buttons);
		gl.addComponent(t, 0, 4, 3, 4);

		searchpanel.setImmediate(true);
		searchpanel.setContent(gl);

		// if button "clear" is clicked all textfields & the checkbox become null again
		clear.addClickListener(event -> {

			tfmina.setValue(null);
			tfmaxa.setValue(null);
			tfnuprod.setValue(null);
			cbdel.setValue(null);
			tfproname.setValue(null);
			tfemail.setValue(null);
			t.setVisible(false);
		});

		// when search is clicked, results SHOULD come up...
		search.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				OrderSearchCriteria ocrit = new OrderSearchCriteria();
				osimp.searchOrders(ocrit);
				t.setVisible(true);
			}
		});

		// return the panel to be placed in the body
		return searchpanel;
	}

}
