package be.groept.ui;

import be.groept.vaadin.model.OrderServiceImpl;

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

		OrderServiceImpl osimp = new OrderServiceImpl();

		// add components
		Panel detail = new Panel("Detail");
		VerticalLayout page = new VerticalLayout();
		GridLayout gl = new GridLayout(2, 3);
		Label title = new Label("Order details");
		Label orderId = new Label("orderId: ");
		Label customerId = new Label("customerId: ");
		Label products = new Label("#producten: ");
		Label delivered = new Label("Delivered: ");
		Label deldays = new Label("DeliveryDays: ");
		Label total = new Label("Total Price: ");

		gl.setSpacing(true);

		Table t = new Table("Products detail");

		Button back = new Button("Back");

		t.setPageLength(t.size());

		// add components to layout
		gl.addComponents(orderId, customerId, products, delivered, deldays, total);
		page.addComponents(title, gl, t, back);
		detail.setContent(page);

		//
		back.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				UI.getCurrent().getNavigator().navigateTo("SearchOrder");

			}
		});

		// add stylename to be used in styles.scss
		title.setStyleName("title");

		return detail;
	}

}
