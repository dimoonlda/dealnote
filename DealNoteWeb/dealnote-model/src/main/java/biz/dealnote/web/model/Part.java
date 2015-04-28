package biz.dealnote.web.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

/**
 * Class describes logical part of Application
 * for Security System
 * @author Lutay D.A.
 *
 */
@Entity
public class Part {
	
	@Id
	@SequenceGenerator(name = "gen_part_id", sequenceName = "GEN_PART_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_part_id", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@JoinColumn(name = "parentid")
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Part parent;
	
	/**
	 * Collection of sub-parts.
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private List<Part> parts = new LinkedList<Part>();
	
	@Column(name = "PARTNAME")
	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	private String name;
	
	private Integer sortPos;
	
	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	private String roleName;

	public Part() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Part getParent() {
		return parent;
	}

	public void setParent(Part parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSortPos() {
		return sortPos;
	}

	public void setSortPos(Integer sortPos) {
		this.sortPos = sortPos;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public boolean isNew(){
		return getId() == null;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	public void addPart(Part part){
		part.setParent(this);
		parts.add(part);
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
		Part other = (Part) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", name=" + name + ", roleName=" + roleName
				+ "]";
	}
	
}
