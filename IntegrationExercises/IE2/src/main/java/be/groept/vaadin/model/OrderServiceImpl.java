package be.groept.vaadin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class OrderServiceImpl implements OrderService, Serializable {

	private static final String FIXED_CUSTOMER = "john";
	private static final long serialVersionUID = 1L;

	private final Map<String, List<Order>> map = new HashMap<>();

	public OrderServiceImpl() {
		createTestData();
	}

	@Override
	public List<String> getMatchingProductNamesWithinAllOrders(String productName) {
		if (StringUtils.isEmpty(productName)) {
			return new ArrayList<String>();
		}

		List<String> productNames = new ArrayList<>();
		List<Order> orders = map.get(FIXED_CUSTOMER);

		for (Order order : orders) {
			for (Product product : order.getProducts()) {
				if (product.getProductName().toLowerCase().startsWith(productName.toLowerCase())) {
					productNames.add(product.getProductName());
				}
			}
		}
		return productNames;
	}

	@Override
	public List<Order> searchOrders(OrderSearchCriteria orderSearchCriteria) {
		List<Order> matchedOrders = new ArrayList<Order>();
		List<Order> orders = map.get(FIXED_CUSTOMER);

		for (Order order : orders) {
			boolean shouldAdd = false;

			if (orderSearchCriteria.hasPriceRange()) {
				if (orderSearchCriteria.isWithinRange(order.getTotalOrderPrice())) {
					shouldAdd = true;
				}
			}

			if (!shouldAdd && StringUtils.isNotBlank(orderSearchCriteria.getProductName())) {
				for (Product product : order.getProducts()) {
					if (product.getProductName().toLowerCase().startsWith(orderSearchCriteria.getProductName().toLowerCase())) {
						shouldAdd = true;
						break;
					}
				}
			}

			if (!shouldAdd) {
				int count = 0;
				for (Product p : order.getProducts()) {
					count++;
				}
				if (orderSearchCriteria.getNumberOfProducts() != null && orderSearchCriteria.getNumberOfProducts() == count) {

					shouldAdd = true;
				}
			}

			if (!shouldAdd && orderSearchCriteria.getDelivered() != null
					&& orderSearchCriteria.getDelivered() == order.isDelivered()) {
				shouldAdd = true;
			}

			// TODO add code for filtering orders based on the criteria
			// 'numberOfProducts' and 'delivered'

			if (shouldAdd) {
				matchedOrders.add(order);
			}
		}

		return matchedOrders;
	}

	@Override
	public List<Order> getAllOrdersForCustomer() {
		return map.get(FIXED_CUSTOMER);
	}

	@SuppressWarnings("boxing")
	private void createTestData() {
		List<Order> orders = new ArrayList<Order>();

		// Order1
		Product lcdTv = new Product(1L, "P1", "Samsung 60\" LCD", "Samsung UN60F6300", new BigDecimal("1200.33"));
		Product smartphone = new Product(2L, "P2", "Samsung galaxy S4", "Samsung android smartphone", new BigDecimal("500.50"));
		Product tabled = new Product(3L, "P3", "HP Elitepad", "HP Elitepad 900", new BigDecimal("999.99"));
		Order order = new Order(1L, "Order1", FIXED_CUSTOMER, true, 3, lcdTv, smartphone, tabled);
		orders.add(order);

		// Order2
		Product elitebook = new Product(4L, "P4", "HP Elitebook", "HP Elitebook 8570w LY556EA Azerty", new BigDecimal("2350.45"));
		order = new Order(2L, "Order2", FIXED_CUSTOMER, false, 1, elitebook);
		orders.add(order);

		// Order3
		Product plasmaTv = new Product(5L, "P5", "LG 60\" PLASMA", "LG 50 inch", new BigDecimal("1500.33"));
		Product pda = new Product(6L, "P6", "Apple IPhone", "IPhone 6", new BigDecimal("585.50"));
		Product tablet = new Product(7L, "P7", "Apple IPad", "IPad 128 Gb", new BigDecimal("799.99"));
		Product loadingDock = new Product(8L, "P8", "Apple Dock", "Loading Dock", new BigDecimal("350.99"));
		order = new Order(3L, "Order3", FIXED_CUSTOMER, true, 10, plasmaTv, pda, tablet, loadingDock);
		orders.add(order);

		map.put(FIXED_CUSTOMER, orders);
	}
}
