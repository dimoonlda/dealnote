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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MEASURELINK")
public class MeasureLink {

	@Id
	@SequenceGenerator(name = "gen_link", sequenceName = "GEN_MEASURELINK_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_link", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@NotNull(message = "{message.field.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODSID")
	private Goods goods;
	
	@NotNull(message = "{message.field.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEASURESRCID")
	private Measure measureSrc;
	
	@NotNull(message = "{message.field.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEASUREDSTID")
	private Measure measureDst;
	
	@NotNull(message = "{message.field.notnull}")
	@Column(name = "SRCVALUE")
	private Double srcValue;
	
	@NotNull(message = "{message.field.notnull}")
	@Column(name = "DSTVALUE")
	private Double dstValue;
	
	public MeasureLink(){
		this.dstValue = 0.0;
		this.srcValue = 0.0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Measure getMeasureSrc() {
		return measureSrc;
	}

	public void setMeasureSrc(Measure measureSrc) {
		this.measureSrc = measureSrc;
	}

	public Measure getMeasureDst() {
		return measureDst;
	}

	public void setMeasureDst(Measure measureDst) {
		this.measureDst = measureDst;
	}

	public Double getSrcValue() {
		return srcValue;
	}

	public void setSrcValue(Double srcValue) {
		this.srcValue = srcValue;
	}

	public Double getDstValue() {
		return dstValue;
	}

	public void setDstValue(Double dstValue) {
		this.dstValue = dstValue;
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
		MeasureLink other = (MeasureLink) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeasureLink [id=" + id + "]";
	}
	
}
