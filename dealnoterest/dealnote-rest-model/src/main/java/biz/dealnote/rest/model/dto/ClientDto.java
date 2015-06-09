package biz.dealnote.rest.model.dto;

public class ClientDto {
	public Integer id;
	public String name;
	public String phone;
	public String taxCode;
	public String addressLocation;
	public String taxNum;
	public String okpo;
	public String mfo;
	public String bankName;
	public String bankAccount;
	public String dogNum;
	public String fName;
	public String addressLaw;
	public Short stopShipment;
	public Integer routeId;
	public Integer groupId;
	public Integer defaultDiscount;
	public Double longitude;
	public Double latitude;
	public Integer outerId;
	public Integer isNotActive;
	
	@Override
	public String toString() {
		return "ClientDto [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", taxCode=" + taxCode + ", addressLocation="
				+ addressLocation + ", taxNum=" + taxNum + ", okpo=" + okpo
				+ ", mfo=" + mfo + ", bankName=" + bankName + ", bankAccount="
				+ bankAccount + ", dogNum=" + dogNum + ", fName=" + fName
				+ ", addressLaw=" + addressLaw + ", stopShipment="
				+ stopShipment + ", routeId=" + routeId + ", groupId="
				+ groupId + ", defaultDiscount=" + defaultDiscount
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", outerId=" + outerId + ", isNotActive=" + isNotActive + "]";
	}
}
