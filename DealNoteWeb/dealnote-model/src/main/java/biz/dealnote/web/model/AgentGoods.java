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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "AGENTSGOODS")
public class AgentGoods {
	
	@Id
	@SequenceGenerator(name = "gen_goods", sequenceName = "GEN_AGENTSGOODS_ID", allocationSize = 1)
	@GeneratedValue(generator = "gen_goods", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GOODSID")
	@NotNull(message = "{message.field.notnull}")
	private Goods goods;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENTID")
	@NotNull(message = "{message.field.notnull}")
	private Agent agent;
	
	private Double price;
	private Double available;
	
	public AgentGoods(){
		this.price = 0.0;
		this.available = 0.0;
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

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAvailable() {
		return available;
	}

	public void setAvailable(Double available) {
		this.available = available;
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
		AgentGoods other = (AgentGoods) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AgentGoods [id=" + id + ", goods=" + goods + ", agent=" + agent
				+ ", price=" + price + ", available=" + available + "]";
	}
	
}
