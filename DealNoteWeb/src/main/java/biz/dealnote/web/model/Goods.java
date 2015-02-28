package biz.dealnote.web.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@Column(name="ASSOTRMENT")
	private Integer assortment;
	
	private Integer vatType;
	
	@Column(name="PRIORITETID")
	private Integer priority;
	private Integer clientTypeMask;
	
	@Size(max=35, message="{error.message.field.sizeTooLong}")
	private String name;
	
	@Size(max=50, message="{error.message.field.sizeTooLong}")
	private String sertificat;
	
	@Size(max=5, message="{error.message.field.sizeTooLong}")
	private String ekka;
	
	@Size(max=84, message="{error.message.field.sizeTooLong}")
	private String fName;
	
	@Size(max=5, message="{error.message.field.sizeTooLong}")
	private String class1;
	
	private Double weight;
	private Integer isActive;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRIORITETCOLORID")
	private PriorityColor priorityColor;
	
	private Integer outerId;
	
	@NotNull
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

	public Integer getAssortment() {
		return assortment;
	}

	public void setAssortment(Integer assortment) {
		this.assortment = assortment;
	}

	public Integer getVatType() {
		return vatType;
	}

	public void setVatType(Integer vatType) {
		this.vatType = vatType;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getClientTypeMask() {
		return clientTypeMask;
	}

	public void setClientTypeMask(Integer clientTypeMask) {
		this.clientTypeMask = clientTypeMask;
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

	public String getEkka() {
		return ekka;
	}

	public void setEkka(String ekka) {
		this.ekka = ekka;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getClass1() {
		return class1;
	}

	public void setClass1(String class1) {
		this.class1 = class1;
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

	public byte[] getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(byte[] goodsImage) {
		this.goodsImage = goodsImage;
	}

	public char[] getGoodsData() {
		return goodsData;
	}

	public void setGoodsData(char[] goodsData) {
		this.goodsData = goodsData;
	}

	public String getGoodsDataAsString() {
		return goodsData!=null ? String.valueOf(goodsData) : null;
	}

	public void setGoodsDataAsString(String goodsData) {
		this.goodsData = goodsData!=null ? goodsData.toCharArray() : null;
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
