package biz.dealnote.web.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
public class Document {

	@Id
	@SequenceGenerator(name = "gen_doc", sequenceName = "GEN_DOCUMENT_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_doc", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@NotNull(message = "{message.field.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTID")
	private Client client;

	@NotNull(message = "{message.field.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENTID")
	private Agent agent;

	@NotNull(message = "{message.field.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCTYPEID")
	private DocType docType;

	@Column(name = "OUTERCLIENTLINKID")
	private Integer linkId;
	
	@NotNull(message = "{message.field.notnull}")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime docDate;
	
	private Double discount;
	
	@NotNull(message = "{message.field.notnull}")
	private Short saleType;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime termDate;
	private Double sumWithoutVat;
	private Double sumWithVat;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime sendingDate;
	
	private Short exported;
	
	@Size(max = 255, message = "{error.message.field.sizeTooLong}")
	private String descript;
	
	@Size(max = 35, message = "{error.message.field.sizeTooLong}")
	private String regNum;
	private Double longitude;
	private Double latitude;
	private Double itemCount;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "document")
	private List<DocumentDetail> details = new LinkedList<DocumentDetail>();
	
	public Document(){
		this.exported = 0;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public DocType getDocType() {
		return docType;
	}
	public void setDocType(DocType docType) {
		this.docType = docType;
	}
	public Integer getLinkId() {
		return linkId;
	}
	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}
	public DateTime getDocDate() {
		return docDate;
	}
	public void setDocDate(DateTime docDate) {
		this.docDate = docDate;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Short getSaleType() {
		return saleType;
	}
	public void setSaleType(Short saleType) {
		this.saleType = saleType;
	}
	public DateTime getTermDate() {
		return termDate;
	}
	public void setTermDate(DateTime termDate) {
		this.termDate = termDate;
	}
	public Double getSumWithoutVat() {
		return sumWithoutVat;
	}
	public void setSumWithoutVat(Double sumWithoutVat) {
		this.sumWithoutVat = sumWithoutVat;
	}
	public Double getSumWithVat() {
		return sumWithVat;
	}
	public void setSumWithVat(Double sumWithVat) {
		this.sumWithVat = sumWithVat;
	}
	public DateTime getSendingDate() {
		return sendingDate;
	}
	public void setSendingDate(DateTime sendingDate) {
		this.sendingDate = sendingDate;
	}
	public Short getExported() {
		return exported;
	}
	public void setExported(Short exported) {
		this.exported = exported;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getItemCount() {
		return itemCount;
	}
	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}
	
	public List<DocumentDetail> getDetails() {
		return details;
	}

	public void setDetails(List<DocumentDetail> details) {
		this.details = details;
	}

	public void addDetail(DocumentDetail detail){
		this.details.add(detail);
		detail.setDocument(this);
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
		Document other = (Document) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Document [id=" + id + ", client=" + client + ", agent=" + agent
				+ ", regNum=" + regNum + "]";
	}
}
