package biz.dealnote.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Class describes job, which you can do with 
 * logical parts of Application.
 * @author Lutay D.A.
 *
 */
@Entity
@Table(name = "JOBS")
public class Job {

	@Id
	@SequenceGenerator(name = "gen_job_id", sequenceName = "GEN_JOB_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_job_id", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	private String name;
	
	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	private String roleName;

	public Job() {}

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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isNew(){
		return getId() == null;
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
		Job other = (Job) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", roleName=" + roleName
				+ "]";
	}
	
}
