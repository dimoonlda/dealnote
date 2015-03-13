package biz.dealnote.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "PRIORITETCOLOR")
public class PriorityColor {
	@Id
	@SequenceGenerator(name="gen_priority_color", sequenceName="GEN_PRIORITETCOLOR_ID", allocationSize=1)
	@GeneratedValue(generator="gen_priority_color", strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Size(max = 35, message="{error.message.field.sizeTooLong}")
	@NotEmpty(message = "{error.message.field.notempty}")
	private String colorCode;
	
	@Column(name="DISCRIPTION")
	@Size(max = 255, message="{error.message.field.sizeTooLong}")
	private String description;
	
	public PriorityColor() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		PriorityColor other = (PriorityColor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PriorityColor [id=" + id + ", colorCode=" + colorCode
				+ ", description=" + description + "]";
	}
}
