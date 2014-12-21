package be.groept.controllers;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.NotBlank;

@ManagedBean
@SessionScoped
public class Ex3_A_backingAction {

	@NotBlank(message = "{name.not.blank}")
	private String name;

	@Digits(integer = 3, fraction = 0)
	private String serial;

	@Future
	private Date date;

	@NullOrNumber
	private String nullOrNumber;

	public void submit(ActionEvent event) {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getNullOrNumber() {
		return nullOrNumber;
	}

	public void setNullOrNumber(String nullOrNumber) {
		this.nullOrNumber = nullOrNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
