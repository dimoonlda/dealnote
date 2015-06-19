package biz.dealnote.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * RegNumber value in {@link Document} depends from this class. 
 * @author Lutay D.A.
 *
 */
@Entity
@Table(name = "DOCCLASS")
public class DocClass {
	
	@Id
	@SequenceGenerator(name = "gen_doc_class", sequenceName = "GEN_DOCCLASS_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_doc_class", strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "OUTERID")
	private Integer outerId;
	
	@NotNull(message = "{message.field.notnull}")
	@NotEmpty(message = "{error.message.field.notempty}")
	@Size(max = 50, message = "{error.message.field.sizeTooLong}")
	@Column(name = "NAME")
	private String name;

	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	@Column(name = "DESCRIPTION")
	private String description;
	
	public DocClass() {}
	
	public DocClass(Integer id, Integer outerId, String name, String description) {
		this.id = id;
		this.outerId = outerId;
		this.name = name;
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOuterId() {
		return outerId;
	}
	public void setOuterId(Integer outerId) {
		this.outerId = outerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean isNew(){
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
		DocClass other = (DocClass) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DocClass [id=" + id + ", outerId=" + outerId + ", name=" + name
				+ "]";
	}
	
}
