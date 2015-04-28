package biz.dealnote.web.model;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="AGENTS")
public class Agent {
	@Id
	@SequenceGenerator(name="gen_agent", sequenceName="GEN_AGENTS_ID", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_agent")
	private Integer id;
	
	@NotEmpty(message = "{error.message.field.notempty}")
	@Size(max = 35, message = "{error.message.field.sizeTooLong}")
	@Column(name="SNAME")
	private String name;
	
	/**
	 * if value 1 - active, 0 - not active
	 */
	@NotNull(message = "{message.field.notnull}")
	@Column(name="ISACTIVE")
	private Integer active;
	
	private Integer outerId;
	
	/**
	 * User's role
	 */
	@NotNull(message = "{message.field.notnull}")
	private Integer roleCode;
	
	/**
	 * Код клиента из справочника клиентов, 
	 * чьи реквизиты будут использоваться как реквизиты поставщика 
	 * (наши реквизиты) при печати накладных.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MYCLIENTUID")
	private Client agentAsClient;
	/**
	 * Полный запрет отгрузки. 
	 * Означает невозможность создавать документы, 
	 * если по клиенту установлен запрет отгрузки. 
	 * При False будет выдаваться только предупреждение.
	 */
	private Integer strictstopship;
	/**
	 * префикс регистрационных номеров накладных по форме оплаты 1. 
	 * Используется для печати накладных. 
	 * Позволяет каждому торговому представителю иметь 
	 * свой уникальный диапазон регистрационных номеров накладных.
	 */
	private String regnumprefix1;
	/**
	 * следующий регистрационный номер накладной по форме оплаты 1. 
	 * Это поле автоматически увеличивается при создании каждого нового 
	 * отгрузочного документа в вэн селлинге 
	 * (при создании предварительного заказа не изменяется) .
	 */
	private Integer regnumnext1;
	/**
	 * префикс регистрационных номеров накладных по форме оплаты 2. 
	 * Используется для печати накладных. 
	 * Позволяет каждому торговому представителю иметь 
	 * свой уникальный диапазон регистрационных номеров накладных.
	 */
	private String regnumprefix2;
	/**
	 * следующий регистрационный номер накладной по форме оплаты 2. 
	 * Это поле автоматически увеличивается при создании каждого нового 
	 * отгрузочного документа в вэн селлинге 
	 * (при создании предварительного заказа не изменяется) .
	 */
	private Integer regnumnext2;
	/**
	 *Управляет выбором возможных видов работы: 
	 *1 –преселлинг, 2 –вэнселлинг. 
	 *Возможно сочетание видов работы. 
	 *Например:  1+2=3  – преселлинг+вэнселлинг 
	 */
	@NotNull(message = "{message.field.notnull}")
	private Integer vsandps;
	private Integer autoDiscount;
	/**
	 * ФИО торгового представителя (используется при печати накладных).
	 */
	@NotNull(message = "{message.field.notnull}")
	private String fio;

	private String email;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name="IMPORTDATETIME")
	private DateTime importDateTime;

	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name="UPDATEDATETIME")
	private DateTime updateDateTime;

	/**
	 * Зашифрованный пароль администратора
	 */
	@Column(name = "ADMIN_PASS")
	@NotNull(message = "{message.field.notnull}")
	private String adminPass;
	/**
	 * Название валюты
	 */
	@NotNull(message = "{message.field.notnull}")
	private String moneyname;
	/**
	 * Формат валюты
	 */
	@NotNull(message = "{message.field.notnull}")
	private String moneyformat;
	/**
	 * Формат количества
	 */
	@NotNull(message = "{message.field.notnull}")
	private String qtyformat;
	/**
	 * Удалять документы старше, дней
	 */
	@NotNull(message = "{message.field.notnull}")
	private Integer dayDelDoc;
	/**
	 * Использовать GPS, 1-да, 0-нет
	 */
	@NotNull(message = "{message.field.notnull}")
	private Integer isGPS;
	/**
	 * Частота получения GPS, мин
	 */
	@NotNull(message = "{message.field.notnull}")
	private Integer frequencyGetGPS;
	/**
	 * Частота отправки GPS, мин
	 */
	@NotNull(message = "{message.field.notnull}")
	private Integer frequencySendGPS;
	/**
	 * Читать GPS c, час
	 */
	@NotNull(message = "{message.field.notnull}")
	private Integer gpsFromHour;
	/**
	 * Читать GPS по, с
	 */
	@NotNull(message = "{message.field.notnull}")
	private Integer gpsToHour;
	/**
	 * Читать GPS по дням, строка в которой на каждый день, установлено значение
	 * Дни начинаются с понедельника
	 */
	@NotNull(message = "{message.field.notnull}")
	private String gpsByDay;
	/**
	 * Путь к Web-сервису
	 */
	@NotNull(message = "{message.field.notnull}")
	private String wsServiceName;
	/**
	 * Собирать статистику по приложениям
	 * 1-да, 0-нет
	 */
	private Integer isAppStat;
	/**
	 * Получать GPS координаты перед созданием документа
	 * 1-да, 0-нет
	 */
	@NotNull(message = "{message.field.notnull}")
	private Integer isGPSBeforeOrder;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="agent")
	private Set<Client> clients = new HashSet<Client>();
	
	public Agent(){
		this.roleCode = 1;
		this.active = 0;
		this.outerId = 0;
		this.strictstopship = 0;
		this.regnumnext1 = 0;
		this.regnumnext2 = 0;
		this.vsandps = 0;
		this.autoDiscount = 0;
		this.isGPS = 0;
		this.dayDelDoc = 0;
		this.frequencyGetGPS = 0;
		this.frequencySendGPS = 0;
		this.gpsFromHour = 0;
		this.gpsToHour = 0;
		this.isAppStat = 0;
		this.isGPSBeforeOrder = 0;
	};
	
	public Agent(int id, String name){
		this();
		this.id = id;
		this.name = name;
	}
	
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

	public int getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = (active == null ? 0 : active);		 
	}

	@JsonIgnore
	public Boolean getActiveAsBoolean(){
		return (this.active == null || this.active == 0) ? false : true;
	}
	
	@JsonIgnore
	public Set<Client> getClients() {
		return clients;
	}

	@JsonIgnore
	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public Integer getOuterId() {
		return outerId;
	}

	public void setOuterId(Integer outerId) {
		this.outerId = outerId;
	}

	public Integer getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(Integer roleCode) {
		this.roleCode = roleCode;
	}

	public Client getAgentAsClient() {
		return agentAsClient;
	}

	public void setAgentAsClient(Client agentAsClient) {
		this.agentAsClient = agentAsClient;
	}

	public Integer getStrictstopship() {
		return strictstopship;
	}

	public void setStrictstopship(Integer strictstopship) {
		this.strictstopship = strictstopship;
	}

	public String getRegnumprefix1() {
		return regnumprefix1;
	}

	public void setRegnumprefix1(String regnumprefix1) {
		this.regnumprefix1 = regnumprefix1;
	}

	public Integer getRegnumnext1() {
		return regnumnext1;
	}

	public void setRegnumnext1(Integer regnumnext1) {
		this.regnumnext1 = regnumnext1;
	}

	public String getRegnumprefix2() {
		return regnumprefix2;
	}

	public void setRegnumprefix2(String regnumprefix2) {
		this.regnumprefix2 = regnumprefix2;
	}

	public Integer getRegnumnext2() {
		return regnumnext2;
	}

	public void setRegnumnext2(Integer regnumnext2) {
		this.regnumnext2 = regnumnext2;
	}

	public Integer getVsandps() {
		return vsandps;
	}

	public void setVsandps(Integer vsandps) {
		this.vsandps = vsandps;
	}

	public Integer getAutoDiscount() {
		return autoDiscount;
	}

	public void setAutoDiscount(Integer autoDiscount) {
		this.autoDiscount = autoDiscount;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DateTime getImportDateTime() {
		return importDateTime;
	}

	public void setImportDateTime(DateTime importDateTime) {
		this.importDateTime = importDateTime;
	}

	public DateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(DateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	public String getMoneyname() {
		return moneyname;
	}

	public void setMoneyname(String moneyname) {
		this.moneyname = moneyname;
	}

	public String getMoneyformat() {
		return moneyformat;
	}

	public void setMoneyformat(String moneyformat) {
		this.moneyformat = moneyformat;
	}

	public String getQtyformat() {
		return qtyformat;
	}

	public void setQtyformat(String qtyformat) {
		this.qtyformat = qtyformat;
	}

	public Integer getDayDelDoc() {
		return dayDelDoc;
	}

	public void setDayDelDoc(Integer dayDelDoc) {
		this.dayDelDoc = dayDelDoc;
	}

	public Integer getIsGPS() {
		return isGPS;
	}

	public void setIsGPS(Integer isGPS) {
		this.isGPS = isGPS;
	}

	public Integer getFrequencyGetGPS() {
		return frequencyGetGPS;
	}

	public void setFrequencyGetGPS(Integer frequencyGetGPS) {
		this.frequencyGetGPS = frequencyGetGPS;
	}

	public Integer getFrequencySendGPS() {
		return frequencySendGPS;
	}

	public void setFrequencySendGPS(Integer frequencySendGPS) {
		this.frequencySendGPS = frequencySendGPS;
	}

	public Integer getGpsFromHour() {
		return gpsFromHour;
	}

	public void setGpsFromHour(Integer gpsFromHour) {
		this.gpsFromHour = gpsFromHour;
	}

	public Integer getGpsToHour() {
		return gpsToHour;
	}

	public void setGpsToHour(Integer gpsToHour) {
		this.gpsToHour = gpsToHour;
	}

	public String getGpsByDay() {
		return gpsByDay;
	}

	public void setGpsByDay(String gpsByDay) {
		this.gpsByDay = gpsByDay;
	}

	public String getWsServiceName() {
		return wsServiceName;
	}

	public void setWsServiceName(String wsServiceName) {
		this.wsServiceName = wsServiceName;
	}

	public Integer getIsAppStat() {
		return isAppStat;
	}

	public void setIsAppStat(Integer isAppStat) {
		this.isAppStat = isAppStat;
	}

	public Integer getIsGPSBeforeOrder() {
		return isGPSBeforeOrder;
	}

	public void setIsGPSBeforeOrder(Integer isGPSBeforeOrder) {
		this.isGPSBeforeOrder = isGPSBeforeOrder;
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
		Agent other = (Agent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", name=" + name + "]";
	}
	
	
}
