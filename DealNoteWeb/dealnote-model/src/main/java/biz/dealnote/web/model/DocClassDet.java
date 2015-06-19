package biz.dealnote.web.model;

import javax.persistence.Column;
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
 * Extra data for {@link Document} which depends from {@link Agent}.
 * For instance, counters for {@code Document.regNum}.
 * @author Lutay D.A.
 *
 */
@Entity
@Table(name = "DOCCLASSDET")
public class DocClassDet {
	
	@Id
	@SequenceGenerator(name = "gen_docclassdet", sequenceName = "GEN_DOCCLASSDET_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_docclassdet", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@NotNull(message = "{message.field.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENTID")
	private Agent agent;
	
	@NotNull(message = "{message.field.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCCLASSID")
	private DocClass docClass;
	
	@NotNull(message = "{message.field.notnull}")
	@Column(name = "REGNUMNEXT")
	private Integer regNumNext;
	
	@Column(name = "REGNUMPREFIX")
	private String regNumPrefix;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public DocClass getDocClass() {
		return docClass;
	}
	public void setDocClass(DocClass docClass) {
		this.docClass = docClass;
	}
	public Integer getRegNumNext() {
		return regNumNext;
	}
	public void setRegNumNext(Integer regNumNext) {
		this.regNumNext = regNumNext;
	}
	public String getRegNumPrefix() {
		return regNumPrefix;
	}
	public void setRegNumPrefix(String regNumPrefix) {
		this.regNumPrefix = regNumPrefix;
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
		DocClassDet other = (DocClassDet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "DocClassDet [id=" + id + ", docClass=" + docClass
				+ ", regNumNext=" + regNumNext + ", regNumPrefix="
				+ regNumPrefix + "]";
	}
	
	
}
