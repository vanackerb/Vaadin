package be.groept.jsf.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Digits;

@ManagedBean
@SessionScoped
public class Ie1Bean {

	private String langChoice1 = "English";
	private String langChoice2 = "Nederlands";
	public String copyright = "Copyrighted! \nDo not re-use without written authorization";

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public static void setLanguages(Map<String, String> languages) {
		Ie1Bean.languages = languages;
	}

	@Digits(integer = 5, fraction = 0)
	private String minAmInput, maxAmInput, numberOfProducts;
	private String prodName, email;

	private static Map<String, String> languages;
	static {
		languages = new LinkedHashMap<String, String>();
		languages.put("English", "English");
		languages.put("Nederlands", "Nederlands");
	}

	public static Map<String, String> getLanguages() {
		return languages;
	}

	public String getLangChoice1() {
		return langChoice1;
	}

	public void setLangChoice1(String langChoice1) {
		this.langChoice1 = langChoice1;
	}

	public String getLangChoice2() {
		return langChoice2;
	}

	public void setLangChoice2(String langChoice2) {
		this.langChoice2 = langChoice2;
	}

	public String toSearch() {
		return "success";
	}

	public String toDetail() {
		return "to_detail";
	}

	public String getMinAmInput() {
		return minAmInput;
	}

	public void setMinAmInput(String minAmInput) {
		this.minAmInput = minAmInput;
	}

	public String getMaxAmInput() {
		return maxAmInput;
	}

	public void setMaxAmInput(String maxAmInput) {
		this.maxAmInput = maxAmInput;
	}

	public String getNumberOfProducts() {
		return numberOfProducts;
	}

	public void setNumberOfProducts(String numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void Clear() {

		minAmInput = null;
		maxAmInput = null;
		numberOfProducts = null;
		prodName = null;
		email = null;

	}

}
