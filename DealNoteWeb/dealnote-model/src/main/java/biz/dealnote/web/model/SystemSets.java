package biz.dealnote.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SYSSETS")
public class SystemSets {
	@Id
	private Integer id;
	@Column(name = "WEBSERVICEADDRESS")
	@NotNull(message = "{message.field.notnull}")
	private String webServiceAdderess;
	@Column(name = "BD_VER")
	@NotNull(message = "{message.field.notnull}")
	private String dbVersion;
	@Column(name = "DBFTYPE")
	@NotNull(message = "{message.field.notnull}")
	private String dbType;
	@Column(name = "MOBILESWAPVERSION")
	@NotNull(message = "{message.field.notnull}")
	private Integer mobileSwapVersion;
	
	public SystemSets() {}
	
	public String getWebServiceAdderess() {
		return webServiceAdderess;
	}
	
	public void setWebServiceAdderess(String webServiceAdderess) {
		this.webServiceAdderess = webServiceAdderess;
	}
	
	public String getDbVersion() {
		return dbVersion;
	}
	
	public void setDbVersion(String dbVersion) {
		this.dbVersion = dbVersion;
	}
	
	public String getDbType() {
		return dbType;
	}
	
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	
	public Integer getMobileSwapVersion() {
		return mobileSwapVersion;
	}
	
	public void setMobileSwapVersion(Integer mobileSwapVersion) {
		this.mobileSwapVersion = mobileSwapVersion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isNew(){
		return this.id == null;
	}
	
	@Override
	public String toString() {
		return "SystemSets [webServiceAdderess=" + webServiceAdderess
				+ ", dbVersion=" + dbVersion + ", dbType=" + dbType
				+ ", mobileSwapVersion=" + mobileSwapVersion + "]";
	}
}
