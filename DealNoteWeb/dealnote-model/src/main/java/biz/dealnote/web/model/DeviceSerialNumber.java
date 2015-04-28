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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DEVICESERIALNUMBERS")
public class DeviceSerialNumber {
	
	@Id
	@SequenceGenerator(name = "gen_sn_id", sequenceName = "GEN_DEVICESERIALNUMBER_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_sn_id", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "SN")
	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	@NotNull(message = "{message.field.notnull}")
	private String serialNumber;
	
	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	private User user;
	
	public DeviceSerialNumber() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isNew(){
		return getId() == null;
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
		DeviceSerialNumber other = (DeviceSerialNumber) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeviceSerialNumber [id=" + id + ", serialNumber="
				+ serialNumber + "]";
	}
}
