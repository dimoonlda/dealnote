package org.dtrader.server.beans;

public class BPOptions {
	/**
	 * ������ ����� �������
	 * sings after point
	 */
	private int accdiv;
	/**
	 * Client ID from clients.
	 * This client is used for printing bills etc.
	 * ��� ������� �� ����������� ��������, 
	 * ��� ��������� ����� �������������� ��� ��������� ���������� 
	 * (���� ���������) ��� ������ ���������.
	 */
	private int myclientuid;
	/**
	 * TAX * 100
	 * ������ ��� � ���������, ���������� �� 100. 
	 * ��������, 20% ���������� ��� 2000
	 */
	private int vatrate;
	/**
	 * Permit to ship.
	 * If client has prohibition to ship then 
	 * sales rep won't have opportunity to create order for client.
	 * ������ ������ ��������. 
	 * �������� ������������� ��������� ���������, 
	 * ���� �� ������� ���������� ������ ��������. 
	 * ��� False ����� ���������� ������ ��������������.
	 */
	private int strictstopship;
	/**
	 * Prefix for invoice's number on form #1.
	 * Used for invoice printing.
	 * ������� ��������������� ������� ��������� �� ����� ������ 1. 
	 * ������������ ��� ������ ���������. 
	 * ��������� ������� ��������� ������������� ����� 
	 * ���� ���������� �������� ��������������� ������� ���������.
	 */
	private String regnumprefix1;
	/**
	 * Next register number on form #1.
	 * ��������� ��������������� ����� ��������� �� ����� ������ 1. 
	 * ��� ���� ������������� ������������� ��� �������� ������� ������ 
	 * ������������ ��������� � ��� �������� 
	 * (��� �������� ���������������� ������ �� ����������) .
	 */
	private int regnumnext1;
	/**
	 * Prefix for invoice's number on form #2.
	 * Used for invoice printing.
	 * ������� ��������������� ������� ��������� �� ����� ������ 2. 
	 * ������������ ��� ������ ���������. 
	 * ��������� ������� ��������� ������������� ����� 
	 * ���� ���������� �������� ��������������� ������� ���������.
	 */
	private String regnumprefix2;
	/**
	 * Next register number on form #2.
	 * ��������� ��������������� ����� ��������� �� ����� ������ 2. 
	 * ��� ���� ������������� ������������� ��� �������� ������� ������ 
	 * ������������ ��������� � ��� �������� 
	 * (��� �������� ���������������� ������ �� ����������) .
	 */
	private int regnumnext2;
	/**
	 * Type of works for sales rep.
	 * 1 - pre-selling
	 * 2 - van-selling
	 * 3 - pre-selling + van-selling 
	 *��������� ������� ��������� ����� ������: 
	 *1 �����������, 2 �����������. 
	 *�������� ��������� ����� ������. 
	 *��������:  1+2=3  � ����������+���������� 
	 */
	private int vsandps;
	private int autoDiscount;
	/**
	 * Full name of sales rep
	 * Used for invoice printing.
	 * ��� ��������� ������������� (������������ ��� ������ ���������).
	 */
	private String fio;

	/**
	 * Encoded administrator password.
	 * ������������� ������ ��������������
	 */
	private String admin_pass;
	/**
	 * Currency name.
	 * �������� ������
	 */
	private String moneyname;
	/**
	 * Currency format.
	 * ������ ������
	 */
	private String moneyformat;
	/**
	 * quantity format.
	 * ������ ����������
	 */
	private String qtyformat;
	/**
	 * Delete documents which are older than, days
	 * ������� ��������� ������, ����
	 */
	private int dayDelDoc;
	/**
	 * Path to goods images.
	 * ���� � ������ ����������� �������
	 */
	private String pathToImg;
	/**
	 * Supervisor can see all sales reps.
	 * 1 - yes, 0 - no.
	 * ����������� ����� ���� �������, 1-��, 0-���
	 */
	private int isSuperviserGetAllAgents;
	/**
	 * Use GPS.
	 * 1 - yes, 0 - no.
	 * ������������ GPS, 1-��, 0-���
	 */
	private int isGPS;
	/**
	 * Frequency of gettin GPS coordinates, min
	 * ������� ��������� GPS, ���
	 */
	private int frequencyGetGPS;
	/**
	 * Frequency of sending GPS coordinates to server, min
	 * ������� �������� GPS, ���
	 */
	private int frequencySendGPS;
	/**
	 * Start getting GPS coordinates from, hour
	 * ������ GPS c, ���
	 */
	private int gpsFromHour;
	/**
	 * Stop getting GPS coordinates to, hour
	 * ������ GPS ��, �
	 */
	private int gpsToHour;
	/**
	 * Gets GPS coordinates by date. It is a string with 0 and 1.
	 * Dates start from Monday.
	 * If is nesossary to get GPS use 1, if not - 0.
	 * ������ GPS �� ����, ������ � ������� �� ������ ����, ����������� ��������
	 * ��� ���������� � ������������
	 */
	private String gpsByDay;
	/**
	 * Path to Web-service.
	 * ���� � Web-�������
	 */
	private String wsServiceName;
	/**
	 * Web-service username.
	 * ������������ ��� Web-�������
	 */
	private String wsUserName;
	/**
	 * Encoded password for Web-service.
	 * ������������� ������ ��� ������������
	 * Web-�������
	 */
	private String wsUserPass;	
	/**
	 * Gets statistix for running app.
	 * 1 - yes, 0 - no.
	 * �������� ���������� �� �����������
	 * 1-��, 0-���
	 */
	private int isAppStat;
	/**
	 * Gets GPS coordinates before creates document(order).
	 * 1 - yes, 0 - no.
	 * �������� GPS ���������� ����� ��������� ���������
	 * 1-��, 0-���
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
