package be.groept.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class TestBackingAction {

	private String text;

	public void search(ActionEvent actionEvent) {
		System.err.println("You searched for: " + text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
