package biz.dealnote.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SERVICECLIENT")
public class ServiceClient {

	@Id
	@SequenceGenerator(name = "service_client_gen", sequenceName = "GEN_SERVICECLIENT", allocationSize = 1)
	@GeneratedValue(generator = "service_client_gen", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "TYPECODE")
	@NotNull(message = "{message.field.notnull}")
	private Integer typeCode;
	
	@Column(name = "NAME")
	@NotNull(message = "{message.field.notnull}")
	@NotEmpty(message = "{error.message.field.notempty}")
	private String name;

	@Column(name = "FILEPATH")
	@NotNull(message = "{message.field.notnull}")
	@NotEmpty(message = "{error.message.field.notempty}")
	private String url;

	@Column(name = "FILEVERSION")
	@NotNull(message = "{message.field.notnull}")
	@NotEmpty(message = "{error.message.field.notempty}")
	private String version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public boolean isNew(){
		return this.id == null;
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
		ServiceClient other = (ServiceClient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServiceClient [id=" + id + ", typeCode=" + typeCode + ", name="
				+ name + ", url=" + url + ", version=" + version + "]";
	}
}
