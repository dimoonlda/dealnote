package biz.dealnote.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "GOODSGROUP")
public class GoodsGroup {
	@Id
	@SequenceGenerator(name="gen_group", sequenceName="GEN_GOODSGROUP_ID", initialValue=1)
	@GeneratedValue(generator="gen_group", strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	private Integer outerId;
	@Size(max=35, message="{message.field.size35}")
	private String name;
	private Integer parentId;
	
	public GoodsGroup() {}

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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
		GoodsGroup other = (GoodsGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GoodsGroup [id=" + id + ", outerId=" + outerId + ", name="
				+ name + ", parentId=" + parentId + "]";
	}
}
