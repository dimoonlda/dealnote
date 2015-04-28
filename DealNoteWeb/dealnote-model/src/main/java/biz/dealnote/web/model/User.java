package biz.dealnote.web.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {
	
	@Id
	@SequenceGenerator(name = "gen_user_id", sequenceName = "GEN_USER_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_user_id", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "USERFIO")
	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	private String fio;
	
	@Column(name = "USERNAME")
	@Size(max = 35, message = "{error.message.field.sizeTooLong}")
	@NotNull(message = "{message.field.notnull}")
	private String name;
	
	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	@NotNull(message = "{message.field.notnull}")
	private String passwd;
	
	@Column(name = "ISACTIVE")
	private Short active;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<DeviceSerialNumber> serialNumbers = new LinkedList<DeviceSerialNumber>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<PartJob> partJobs = new LinkedList<PartJob>();
	
	public User() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Short getActive() {
		return active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public boolean isActive(){
		return this.active == 1;
	}
	
	public boolean isNew(){
		return this.id == null;
	}
	
	public List<DeviceSerialNumber> getSerialNumbers() {
		return serialNumbers;
	}

	public void setSerialNumbers(List<DeviceSerialNumber> serialNumbers) {
		this.serialNumbers = serialNumbers;
	}
	
	public void addDeviceSerialNumber(DeviceSerialNumber sn){
		sn.setUser(this);
		serialNumbers.add(sn);
	}
	
	public List<PartJob> getPartJobs() {
		return partJobs;
	}

	public void setPartJobs(List<PartJob> partJobs) {
		this.partJobs = partJobs;
	}

	public void addPartJob(PartJob partJob){
		partJob.setUser(this);
		partJobs.add(partJob);
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
