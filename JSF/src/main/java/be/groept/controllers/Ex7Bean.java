package be.groept.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Ex7Bean {

	private int counter;

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String toFirstPage() {
		counter++;
		return "eerste";
	}

	public String toSecondPage() {
		counter++;
		return "success";
	}

	public String toThirdPage() {
		counter++;
		return "success";
	}
}
