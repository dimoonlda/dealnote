package biz.dealnote.web.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Client info
 * @author Lutay D.A.
 *
 */
@Entity
public class Client {
	@Id
	@SequenceGenerator(name="gen_client", sequenceName="GEN_CLIENT_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_client")
	private Integer id;
	private String name;
	private String phone;
	private Integer clientTypeId;
	private String taxCode;
	//private Integer agentId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="agentid")
	private Agent agent;
	
	private String addressLocation;
	private String taxNum;
	private String okpo;
	private String mfo;
	private String bankName;
	private String bankAccount;
	private String dogNum;
	private String fName;
	private String addressLaw;
	private Integer debtSumm1;
	private Integer debtDays1;
	private Integer debtSumm2;
	private Integer debtDays2;
	private Short stopShipment;
	private Short routeId;
	private Integer defaultDiscount;
	private Integer parentId;
	private Double longitude;
	private Double latitude;
	private Integer outerid;

	public Client(){}
	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getClientTypeId() {
		return clientTypeId;
	}

	public void setClientTypeId(Integer clientTypeId) {
		this.clientTypeId = clientTypeId;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
/*
	public int getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
*/
	public String getAddressLocation() {
		return addressLocation;
	}

	public void setAddressLocation(String addressLocation) {
		this.addressLocation = addressLocation;
	}

	public String getTaxNum() {
		return taxNum;
	}

	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}

	public String getOkpo() {
		return okpo;
	}

	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}

	public String getMfo() {
		return mfo;
	}

	public void setMfo(String mfo) {
		this.mfo = mfo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getDogNum() {
		return dogNum;
	}

	public void setDogNum(String dogNum) {
		this.dogNum = dogNum;
	}

	public String getFName() {
		return fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getAddressLaw() {
		return addressLaw;
	}

	public void setAddressLaw(String addressLaw) {
		this.addressLaw = addressLaw;
	}

	public int getDebtSumm1() {
		return debtSumm1;
	}

	public void setDebtSumm1(Integer debtSumm1) {
		this.debtSumm1 = debtSumm1;
	}

	public int getDebtDays1() {
		return debtDays1;
	}

	public void setDebtDays1(Integer debtDays1) {
		this.debtDays1 = debtDays1;
	}

	public int getDebtSumm2() {
		return debtSumm2;
	}

	public void setDebtSumm2(Integer debtSumm2) {
		this.debtSumm2 = debtSumm2;
	}

	public int getDebtDays2() {
		return debtDays2;
	}

	public void setDebtDays2(Integer debtDays2) {
		this.debtDays2 = debtDays2;
	}

	public short getStopShipment() {
		return stopShipment;
	}

	public void setStopShipment(Short stopShipment) {
		this.stopShipment = stopShipment;
	}

	public short getRouteId() {
		return routeId;
	}

	public void setRouteId(Short routeId) {
		this.routeId = routeId;
	}

	public int getDefaultDiscount() {
		return defaultDiscount;
	}

	public void setDefaultDiscount(Integer defaultDiscount) {
		this.defaultDiscount = defaultDiscount;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public int getOuterId() {
		return outerid;
	}

	public void setOuterId(Integer outerId) {
		this.outerid = outerId;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + "]";
	}

}