package biz.dealnote.web.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Class describes what kind of job user can do
 * with particular logical part of Application
 * @author Lutay D.A.
 *
 */
@Entity
@Table(name = "PARTJOBSFORUSERS")
public class PartJob {
	
	@Id
	@SequenceGenerator(name = "gen_part_job_id", sequenceName = "GEN_PARTJOBSFORUSERS_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_part_job_id", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@JoinColumn(name = "userid")
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "{message.field.notnull}")
	private User user;
	
	@JoinColumn(name = "partid")
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "{message.field.notnull}")
	private Part part;

	@JoinColumn(name = "jobsid")
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "{message.field.notnull}")
	private Job job;
	
	@NotNull(message = "{message.field.notnull}")
	private Short isActive;

	public PartJob() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Short getIsActive() {
		return isActive;
	}

	public void setIsActive(Short isActive) {
		this.isActive = isActive;
	}

	public boolean isActive(){
		return getIsActive() == 1;
	}
	
	public boolean isNew(){
		return getId() == null;
	}
	
	public String getFullRoleName(){
		if(Objects.nonNull(job.getRoleName())
				&& Objects.nonNull(part.getRoleName())
				&& !job.getRoleName().isEmpty() 
				&& !part.getRoleName().isEmpty()){
			return part.getRoleName() + "_" + job.getRoleName();
		}
		return null;
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
		PartJob other = (PartJob) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PartJob [id=" + id + "]";
	}
	
}
