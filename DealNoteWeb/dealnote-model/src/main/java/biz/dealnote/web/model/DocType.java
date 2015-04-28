package biz.dealnote.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

@Entity
public class DocType {
	@Id
	@SequenceGenerator(name = "gen_type", sequenceName = "GEN_DOCTYPE_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_type", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PAYFORMID")
	private PayForm payForm;
	private Integer outerId;
	private Integer days;
	private Short accIncrNoVat;
	private Short accIncrWithVat;
	
	@Column(name = "SNAME")
	@Size(max = 50, message = "{error.message.field.sizeTooLong}")
	private String name;
	private Short discountFirst;
	private Short vatOverSum;
	
	public DocType(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PayForm getPayForm() {
		return payForm;
	}
	public void setPayForm(PayForm payForm) {
		this.payForm = payForm;
	}
	public Integer getOuterId() {
		return outerId;
	}
	public void setOuterId(Integer outerId) {
		this.outerId = outerId;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Short getAccIncrNoVat() {
		return accIncrNoVat;
	}
	public void setAccIncrNoVat(Short accIncrNoVat) {
		this.accIncrNoVat = accIncrNoVat;
	}
	public Short getAccIncrWithVat() {
		return accIncrWithVat;
	}
	public void setAccIncrWithVat(Short accIncrWithVat) {
		this.accIncrWithVat = accIncrWithVat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Short getDiscountFirst() {
		return discountFirst;
	}
	public void setDiscountFirst(Short discountFirst) {
		this.discountFirst = discountFirst;
	}
	public Short getVatOverSum() {
		return vatOverSum;
	}
	public void setVatOverSum(Short vatOverSum) {
		this.vatOverSum = vatOverSum;
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
		DocType other = (DocType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DocType [id=" + id + ", name=" + name + "]";
	}
}
