package be.groept.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class BasicJSFExercise1BackingAction {

	private boolean onOff;

	public boolean isOnOff() {
		return onOff;
	}

	public void OnOff() {

		onOff = !onOff;
	}

}
