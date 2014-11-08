package org.dtrader.server.beans;

public class BPOptions {
	/**
	 * знаков после запятой
	 * sings after point
	 */
	private int accdiv;
	/**
	 * Client ID from clients.
	 * This client is used for printing bills etc.
	 * Код клиента из справочника клиентов, 
	 * чьи реквизиты будут использоваться как реквизиты поставщика 
	 * (наши реквизиты) при печати накладных.
	 */
	private int myclientuid;
	/**
	 * TAX * 100
	 * Ставка НДС в процентах, умноженная на 100. 
	 * Например, 20% передается как 2000
	 */
	private int vatrate;
	/**
	 * Permit to ship.
	 * If client has prohibition to ship then 
	 * sales rep won't have opportunity to create order for client.
	 * Полный запрет отгрузки. 
	 * Означает невозможность создавать документы, 
	 * если по клиенту установлен запрет отгрузки. 
	 * При False будет выдаваться только предупреждение.
	 */
	private int strictstopship;
	/**
	 * Prefix for invoice's number on form #1.
	 * Used for invoice printing.
	 * префикс регистрационных номеров накладных по форме оплаты 1. 
	 * Используется для печати накладных. 
	 * Позволяет каждому торговому представителю иметь 
	 * свой уникальный диапазон регистрационных номеров накладных.
	 */
	private String regnumprefix1;
	/**
	 * Next register number on form #1.
	 * следующий регистрационный номер накладной по форме оплаты 1. 
	 * Это поле автоматически увеличивается при создании каждого нового 
	 * отгрузочного документа в вэн селлинге 
	 * (при создании предварительного заказа не изменяется) .
	 */
	private int regnumnext1;
	/**
	 * Prefix for invoice's number on form #2.
	 * Used for invoice printing.
	 * префикс регистрационных номеров накладных по форме оплаты 2. 
	 * Используется для печати накладных. 
	 * Позволяет каждому торговому представителю иметь 
	 * свой уникальный диапазон регистрационных номеров накладных.
	 */
	private String regnumprefix2;
	/**
	 * Next register number on form #2.
	 * следующий регистрационный номер накладной по форме оплаты 2. 
	 * Это поле автоматически увеличивается при создании каждого нового 
	 * отгрузочного документа в вэн селлинге 
	 * (при создании предварительного заказа не изменяется) .
	 */
	private int regnumnext2;
	/**
	 * Type of works for sales rep.
	 * 1 - pre-selling
	 * 2 - van-selling
	 * 3 - pre-selling + van-selling 
	 *Управляет выбором возможных видов работы: 
	 *1 –преселлинг, 2 –вэнселлинг. 
	 *Возможно сочетание видов работы. 
	 *Например:  1+2=3  – преселлинг+вэнселлинг 
	 */
	private int vsandps;
	private int autoDiscount;
	/**
	 * Full name of sales rep
	 * Used for invoice printing.
	 * ФИО торгового представителя (используется при печати накладных).
	 */
	private String fio;

	/**
	 * Encoded administrator password.
	 * Зашифрованный пароль администратора
	 */
	private String admin_pass;
	/**
	 * Currency name.
	 * Название валюты
	 */
	private String moneyname;
	/**
	 * Currency format.
	 * Формат валюты
	 */
	private String moneyformat;
	/**
	 * quantity format.
	 * Формат количества
	 */
	private String qtyformat;
	/**
	 * Delete documents which are older than, days
	 * Удалять документы старше, дней
	 */
	private int dayDelDoc;
	/**
	 * Path to goods images.
	 * Путь к файлам изображений товаров
	 */
	private String pathToImg;
	/**
	 * Supervisor can see all sales reps.
	 * 1 - yes, 0 - no.
	 * Супервайзер видит всех агентов, 1-да, 0-нет
	 */
	private int isSuperviserGetAllAgents;
	/**
	 * Use GPS.
	 * 1 - yes, 0 - no.
	 * Использовать GPS, 1-да, 0-нет
	 */
	private int isGPS;
	/**
	 * Frequency of gettin GPS coordinates, min
	 * Частота получения GPS, мин
	 */
	private int frequencyGetGPS;
	/**
	 * Frequency of sending GPS coordinates to server, min
	 * Частота отправки GPS, мин
	 */
	private int frequencySendGPS;
	/**
	 * Start getting GPS coordinates from, hour
	 * Читать GPS c, час
	 */
	private int gpsFromHour;
	/**
	 * Stop getting GPS coordinates to, hour
	 * Читать GPS по, с
	 */
	private int gpsToHour;
	/**
	 * Gets GPS coordinates by date. It is a string with 0 and 1.
	 * Dates start from Monday.
	 * If is nesossary to get GPS use 1, if not - 0.
	 * Читать GPS по дням, строка в которой на каждый день, установлено значение
	 * Дни начинаются с понедельника
	 */
	private String gpsByDay;
	/**
	 * Path to Web-service.
	 * Путь к Web-сервису
	 */
	private String wsServiceName;
	/**
	 * Web-service username.
	 * Пользователь для Web-сервиса
	 */
	private String wsUserName;
	/**
	 * Encoded password for Web-service.
	 * Зашифрованный пароль для пользователя
	 * Web-сервиса
	 */
	private String wsUserPass;	
	/**
	 * Gets statistix for running app.
	 * 1 - yes, 0 - no.
	 * Собирать статистику по приложениям
	 * 1-да, 0-нет
	 */
	private int isAppStat;
	/**
	 * Gets GPS coordinates before creates document(order).
	 * 1 - yes, 0 - no.
	 * Получать GPS координаты перед созданием документа
	 * 1-да, 0-нет
	 */
	private int isGPSBeforeOrder;
	
	public int getAccdiv() {
		return accdiv;
	}
	public void setAccdiv(int accdiv) {
		this.accdiv = accdiv;
	}
	public int getMyclientuid() {
		return myclientuid;
	}
	public void setMyclientuid(int myclientuid) {
		this.myclientuid = myclientuid;
	}
	public int getVatrate() {
		return vatrate;
	}
	public void setVatrate(int vatrate) {
		this.vatrate = vatrate;
	}
	public int getStrictstopship() {
		return strictstopship;
	}
	public void setStrictstopship(int strictstopship) {
		this.strictstopship = strictstopship;
	}
	public String getRegnumprefix1() {
		return regnumprefix1;
	}
	public void setRegnumprefix1(String regnumprefix1) {
		this.regnumprefix1 = regnumprefix1;
	}
	public int getRegnumnext1() {
		return regnumnext1;
	}
	public void setRegnumnext1(int regnumnext1) {
		this.regnumnext1 = regnumnext1;
	}
	public String getRegnumprefix2() {
		return regnumprefix2;
	}
	public void setRegnumprefix2(String regnumprefix2) {
		this.regnumprefix2 = regnumprefix2;
	}
	public int getRegnumnext2() {
		return regnumnext2;
	}
	public void setRegnumnext2(int regnumnext2) {
		this.regnumnext2 = regnumnext2;
	}
	public int getVsandps() {
		return vsandps;
	}
	public void setVsandps(int vsandps) {
		this.vsandps = vsandps;
	}
	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public int getAutoDiscount() {
		return autoDiscount;
	}
	public void setAutoDiscount(int autoDiscount) {
		this.autoDiscount = autoDiscount;
	}
	public String getAdmin_pass() {
		return admin_pass;
	}

	public void setAdmin_pass(String admin_pass) {
		this.admin_pass = admin_pass;
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

	public int getDayDelDoc() {
		return dayDelDoc;
	}

	public void setDayDelDoc(int dayDelDoc) {
		this.dayDelDoc = dayDelDoc;
	}

	public String getPathToImg() {
		return pathToImg;
	}

	public void setPathToImg(String pathToImg) {
		this.pathToImg = pathToImg;
	}

	public int getIsSuperviserGetAllAgents() {
		return isSuperviserGetAllAgents;
	}

	public void setIsSuperviserGetAllAgents(int isSuperviserGetAllAgents) {
		this.isSuperviserGetAllAgents = isSuperviserGetAllAgents;
	}

	public int getIsGPS() {
		return isGPS;
	}

	public void setIsGPS(int isGPS) {
		this.isGPS = isGPS;
	}

	public int getFrequencyGetGPS() {
		return frequencyGetGPS;
	}

	public void setFrequencyGetGPS(int frequencyGetGPS) {
		this.frequencyGetGPS = frequencyGetGPS;
	}

	public int getFrequencySendGPS() {
		return frequencySendGPS;
	}

	public void setFrequencySendGPS(int frequencySendGPS) {
		this.frequencySendGPS = frequencySendGPS;
	}

	public int getGpsFromHour() {
		return gpsFromHour;
	}

	public void setGpsFromHour(int gpsFromHour) {
		this.gpsFromHour = gpsFromHour;
	}

	public int getGpsToHour() {
		return gpsToHour;
	}

	public void setGpsToHour(int gpsToHour) {
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

	public String getWsUserName() {
		return wsUserName;
	}

	public void setWsUserName(String wsUserName) {
		this.wsUserName = wsUserName;
	}

	public String getWsUserPass() {
		return wsUserPass;
	}

	public void setWsUserPass(String wsUserPass) {
		this.wsUserPass = wsUserPass;
	}
	public int getIsAppStat() {
		return isAppStat;
	}

	public void setIsAppStat(int isAppStat) {
		this.isAppStat = isAppStat;
	}
	public int getIsGPSBeforeOrder() {
		return isGPSBeforeOrder;
	}

	public void setIsGPSBeforeOrder(int isGPSBeforeOrder) {
		this.isGPSBeforeOrder = isGPSBeforeOrder;
	}
	
}
