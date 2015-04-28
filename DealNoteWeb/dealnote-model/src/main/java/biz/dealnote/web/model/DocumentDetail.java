package biz.dealnote.web.model;

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

@Entity
@Table(name = "DOCUMENTDET")
public class DocumentDetail {
	
	@Id
	@SequenceGenerator(name = "gen_det", sequenceName = "GEN_DOCDETAIL_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_det", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@NotNull(message = "{message.field.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCID")
	private Document document;

	@NotNull(message = "{message.field.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODSID")
	private Goods goods;
	
	@NotNull(message = "{message.field.notnull}")
	private Double itemcount;
	private Double priceWithoutVat;
	private Double priceWithVat;
	
	public DocumentDetail(){}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Double getItemcount() {
		return itemcount;
	}
	public void setItemcount(Double itemcount) {
		this.itemcount = itemcount;
	}
	public Double getPriceWithoutVat() {
		return priceWithoutVat;
	}
	public void setPriceWithoutVat(Double priceWithoutVat) {
		this.priceWithoutVat = priceWithoutVat;
	}
	public Double getPriceWithVat() {
		return priceWithVat;
	}
	public void setPriceWithVat(Double priceWithVat) {
		this.priceWithVat = priceWithVat;
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
		DocumentDetail other = (DocumentDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DocumentDetail [id=" + id + ", document=" + document
				+ ", goods=" + goods + ", itemcount=" + itemcount + "]";
	}
	
}
