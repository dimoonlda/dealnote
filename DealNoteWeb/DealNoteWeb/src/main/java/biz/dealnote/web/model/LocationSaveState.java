package biz.dealnote.web.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOCATION_SAVE_STATE")
public class LocationSaveState implements Comparable<LocationSaveState>{
	@Id
	private Integer id;
	private String name;
	
	public LocationSaveState() {	}

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
		LocationSaveState other = (LocationSaveState) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocationSaveState [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int compareTo(LocationSaveState o) {
		return this.getId().compareTo(o.getId());
	}
	
}
