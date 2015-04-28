package biz.dealnote.web.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="GOODS")
public class Goods {
	@Id
	@SequenceGenerator(name="gen_goods", sequenceName="GEN_GOOD_ID", allocationSize=1)
	@GeneratedValue(generator="gen_goods", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATEGORYID")
	private GoodsGroup goodsGroup;
	
	private Integer sortPos;
	
	private Double vatCoef;
	
	@Size(max=35, message="{error.message.field.sizeTooLong}")
	@NotEmpty(message="{error.message.field.notempty}")
	private String name;
	
	@Size(max=50, message="{error.message.field.sizeTooLong}")
	private String sertificat;
		
	@Size(max=84, message="{error.message.field.sizeTooLong}")
	@NotEmpty(message="{error.message.field.notempty}")
	private String fullName;
	
	private Double weight;
	private Integer isActive;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRIORITETCOLORID")
	private PriorityColor priorityColor;
	
	private Integer outerId;
	
	@NotNull(message="{error.message.field.notnull}")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MEASUREID")
	private Measure measure;
	
	@Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="GOODSIMAGE", columnDefinition="mediumblob")
	private byte[] goodsImage;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name="GOODSDATA", columnDefinition="mediumblob")
    private char[] goodsData;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "goods")
    private Set<MeasureLink> measureLinks = new HashSet<MeasureLink>();
    
    public Goods() {	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GoodsGroup getGoodsGroup() {
		return goodsGroup;
	}

	public void setGoodsGroup(GoodsGroup goodsGroup) {
		this.goodsGroup = goodsGroup;
	}

	public Integer getSortPos() {
		return sortPos;
	}

	public void setSortPos(Integer sortPos) {
		this.sortPos = sortPos;
	}

	public Double getVatCoef() {
		return vatCoef;
	}

	public void setVatCoef(Double vatCoef) {
		this.vatCoef = vatCoef;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSertificat() {
		return sertificat;
	}

	public void setSertificat(String sertificat) {
		this.sertificat = sertificat;
	}

	public String getfName() {
		return fullName;
	}

	public void setfName(String fName) {
		this.fullName = fName;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public PriorityColor getPriorityColor() {
		return priorityColor;
	}

	public void setPriorityColor(PriorityColor priorityColor) {
		this.priorityColor = priorityColor;
	}

	public Integer getOuterId() {
		return outerId;
	}

	public void setOuterId(Integer outerId) {
		this.outerId = outerId;
	}

	public Measure getMeasure() {
		return measure;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	@JsonIgnore
	public byte[] getGoodsImage() {
		return goodsImage;
	}

	@JsonIgnore
	public void setGoodsImage(byte[] goodsImage) {
		this.goodsImage = goodsImage;
	}

	@JsonIgnore
	public char[] getGoodsData() {
		return goodsData;
	}

	@JsonIgnore
	public void setGoodsData(char[] goodsData) {
		this.goodsData = goodsData;
	}

	public String getGoodsDataAsString() {
		return goodsData!=null ? String.valueOf(goodsData) : null;
	}

	public void setGoodsDataAsString(String goodsData) {
		this.goodsData = goodsData!=null ? goodsData.toCharArray() : null;
	}

	@JsonIgnore
	public Set<MeasureLink> getMeasureLinks() {
		return measureLinks;
	}

	@JsonIgnore
	public void setMeasureLinks(Set<MeasureLink> measureLinks) {
		this.measureLinks = measureLinks;
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
		Goods other = (Goods) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", goodsGroup=" + goodsGroup + ", name="
				+ name + ", isActive=" + isActive + ", outerId=" + outerId
				+ ", measure=" + measure + "]";
	}
}
