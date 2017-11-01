package attapp.model;

public class RepresentativeAgreement {
	private String name;
	private String socialSecurityNumber;
	private String address;
	private String date;
	private String agreementnumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAgreementnumber() {
		return agreementnumber;
	}

	public void setAgreementnumber(String agreementnumber) {
		this.agreementnumber = agreementnumber;
	}
}
