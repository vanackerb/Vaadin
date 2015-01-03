package be.groept.ui;

import java.math.BigDecimal;

import be.groept.vaadin.model.Order;
import be.groept.vaadin.model.Product;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class OrderDetail extends Template {

	{
		setSpacing(true);
	}

	@Override
	protected Component getBody() {

		Order o = (Order) getUI().getSession().getAttribute("order");
		int numberProducts = 0;
		int i = 0;

		// count number of products in order
		for (Product p : o.getProducts()) {

			numberProducts++;
		}

		// add components
		Panel detail = new Panel("Detail");
		VerticalLayout page = new VerticalLayout();
		GridLayout gl = new GridLayout(4, 3);
		Label title = new Label("Order details");
		Label orderId = new Label("Order ID: ");
		Label customerId = new Label("Customer ID: ");
		Label products = new Label("Number of products: ");
		Label delivered = new Label("Delivered: ");
		Label deldays = new Label("Delivery days: ");
		Label total = new Label("Total price: ");
		Label oId = new Label(o.getOrderId());
		Label cId = new Label(o.getCustomerId());
		Label pr = new Label("" + numberProducts);
		Label del = new Label("" + o.isDelivered());
		Label deDays = new Label("" + o.getDeliveryDays());
		Label tot = new Label("" + o.getTotalOrderPrice());
		Button back = new Button("Back");
		Table t = new Table("Products detail");

		// set width for the gridlayout
		gl.setWidth(50, Unit.PERCENTAGE);
		gl.setSpacing(true);

		// add columnheaders
		t.addContainerProperty("ProductId", String.class, null);
		t.addContainerProperty("Product Name", String.class, null);
		t.addContainerProperty("Description", String.class, null);
		t.addContainerProperty("Price", BigDecimal.class, null);

		// fill table with products
		for (Product p : o.getProducts()) {
			i++;
			t.addItem(new Object[] { p.getProductId(), p.getProductName(), p.getProductDescription(), p.getProductPrice() }, i);

		}

		// set size of table
		t.setPageLength(t.size());
		// t.setWidth(80, Unit.PERCENTAGE);

		// add components to layout
		gl.addComponents(orderId, oId, customerId, cId, products, pr, delivered, del, deldays, deDays, total, tot);
		page.addComponents(title, gl, t, back);
		detail.setContent(page);

		// navigate back
		back.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				UI.getCurrent().getNavigator().navigateTo("SearchOrder");

			}
		});

		// add stylename to be used in styles.scss
		back.setStyleName("button");
		title.setStyleName("title");
		gl.setStyleName("grid");
		detail.setStyleName("detail");
		t.setStyleName("tableDetail");

		return detail;
	}

}
