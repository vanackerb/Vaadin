package be.groept;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Ex4 extends HorizontalLayout implements View {

	private final List<String> options = new ArrayList<>();

	{
		setSpacing(true);
		options.add("Aalst");
		options.add("Aalter");
		options.add("Aarschot");
		options.add("Aartselaar");
		options.add("Affligem");
		options.add("Alken");
		options.add("Alveringem");
		options.add("Antwerpen");
		options.add("Anzegem");
		options.add("Ardooie");
		options.add("Arendonk");
		options.add("As");
		options.add("Asse");
		options.add("Assenede");
		options.add("Avelgem");
		options.add("Baarle-Hertog");
		options.add("Balen");
		options.add("Beernem");
		options.add("Beerse");
		options.add("Beersel");
		options.add("Begijnendijk");
		options.add("Bekkevoort");
		options.add("Beringen");
		options.add("Berlaar");
		options.add("Berlare");
		options.add("Bertem");
		options.add("Bever");
		options.add("Beveren");
		options.add("Bierbeek");
		options.add("Bilzen");
		options.add("Blankenberge");
		options.add("Bocholt");
		options.add("Boechout");
		options.add("Bonheiden");
		options.add("Boom");
		options.add("Boortmeerbeek");
		options.add("Borgloon");
		options.add("Bornem");
		options.add("Borsbeek");
		options.add("Boutersem");
		options.add("Brakel");
		options.add("Brasschaat");
		options.add("Brecht");
		options.add("Bredene");
		options.add("Bree");
		options.add("Brugge");
		options.add("Buggenhout");
		options.add("Damme");
		options.add("De Haan");
		options.add("De Panne");
		options.add("De Pinte");
		options.add("Deerlijk");
		options.add("Deinze");
		options.add("Denderleeuw");
		options.add("Dendermonde");
		options.add("Dentergem");
		options.add("Dessel");
		options.add("Destelbergen");
	}

	public Ex4() {

		ComboBox cb = new ComboBox("Enter municipality:", options);
		cb.setInputPrompt("Geen gemeente geselecteerd");
		final Label l = new Label();

		// Set full width
		cb.setWidth(100.0f, Unit.PERCENTAGE);

		// Set the appropriate filtering mode for this example
		cb.setFilteringMode(FilteringMode.CONTAINS);
		cb.setImmediate(true);

		// Disallow null selections
		cb.setNullSelectionAllowed(false);

		// Check if the caption for new item already exists in the list of item
		// captions before approving it as a new item.

		cb.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(final ValueChangeEvent event) {
				final String valueString = String.valueOf(event.getProperty().getValue());
				l.setValue(valueString);
			}
		});

		addComponents(cb, l);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
