package biz.dealnote.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "WSSERVERS")
public class WsServer {
	
	@Id
	@SequenceGenerator(name = "gen_server", sequenceName = "GEN_WSSERVERS", allocationSize = 1)
	@GeneratedValue(generator = "gen_server", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	private String serverAddress;
	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	private String description;
	private Short isDefault;
	
	public WsServer() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getServerAddress() {
		return serverAddress;
	}
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Short getIsDefault() {
		return isDefault;
	}
	/**
	 * Set default server or not
	 * @param isDefault 1 - if default, 0 - if not
	 */
	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}
	
	public Boolean isDefault(){
		return getIsDefault() == 1 ? true : false;
	}
	
	public Boolean isNew(){
		return id == null;
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
		WsServer other = (WsServer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WsServer [id=" + id + ", serverAddress=" + serverAddress
				+ ", description=" + description + ", isDefault=" + isDefault
				+ "]";
	}
}
