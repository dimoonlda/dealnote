package biz.dealnote.web.model;

import java.util.HashSet;
import java.util.Set;

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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="AGENTS")
public class Agent {
	@Id
	@SequenceGenerator(name="gen_agent", sequenceName="GEN_AGENTS_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_agent")
	private Integer id;
	
	@NotEmpty(message = "{error.message.field.notempty}")
	@Size(max = 35, message = "{error.message.field.sizeTooLong}")
	@Column(name="SNAME")
	private String name;
	
	/**
	 * if value 1 - active, 0 - not active
	 */
	@NotNull
	@Column(name="ISACTIVE")
	private Integer active;
	
	private Integer outerId;
	
	@NotNull
	private Integer roleCode;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="agent")
	private Set<Client> clients = new HashSet<Client>();
	
	public Agent(){
		this.roleCode = 1;
		this.active = 0;
	};
	
	public Agent(int id, String name){
		this();
		this.id = id;
		this.name = name;
	}
	
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

	public int getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = (active == null ? 0 : active);		 
	}

	public Boolean getActiveAsBoolean(){
		return (this.active == null || this.active == 0) ? false : true;
	}
	
	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public Integer getOuterId() {
		return outerId;
	}

	public void setOuterId(Integer outerId) {
		this.outerId = outerId;
	}

	public Integer getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(Integer roleCode) {
		this.roleCode = roleCode;
	}

	public boolean isNew(){
		return (this.id == null);
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
		Agent other = (Agent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", name=" + name + "]";
	}
	
	
}
