package biz.dealnote.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="PAYFORMS")
public class PayForm {
	
	@Id
	@SequenceGenerator(name = "gen_payForm", sequenceName = "GEN_PAYFORM_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_payForm", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "SNAME")
	private String name;
	
	private Integer outerId;
	
	
	public PayForm() {}
	
	public Integer getId() {
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
	public Integer getOuterId() {
		return outerId;
	}
	public void setOuterId(Integer outerId) {
		this.outerId = outerId;
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
		PayForm other = (PayForm) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "PayForm [id=" + id + ", name=" + name + ", outerId=" + outerId
				+ "]";
	}
	
}
