package biz.dealnote.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="MEASURES")
public class Measure {
	@Id
	@SequenceGenerator(name="gen_measure", sequenceName="GEN_MEASURES_ID", allocationSize=1)
	@GeneratedValue(generator="gen_measure", strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@NotNull(message = "{error.message.field.notempty}")
	private Integer outerId;
	
	@NotEmpty(message = "{error.message.field.notempty}")
	@Size(max = 35, message = "{error.message.field.sizeTooLong}")
	private String name;
	
	public Measure() {}

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

	@JsonIgnore
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
		Measure other = (Measure) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Measure [id=" + id + ", outerId=" + outerId + ", name=" + name
				+ "]";
	}
}
