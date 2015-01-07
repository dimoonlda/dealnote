package biz.dealnote.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DocumentDAOJDBC implements DocumentDAO {
	
	public static final String colDocs_clientid = "clientid";   //ID �������
	public static final String colDocs_agentid = "agentid";
	public static final String colDocs_docdate = "docdate"; 	//���� ���������
	public static final String colDocs_doctime = "doctime"; 	//����� ���������
	public static final String colDocs_discount = "discount"; 	//������
	public static final String colDocs_casecount = "casecount";
	public static final String colDocs_doctypeid = "doctypeid";	//��� ��������� � ������� �-��
	public static final String colDocs_mainsumm = "mainsumm"; 	//����� ���������
	public static final String colDocs_descript = "descript"; 	//�����������
	public static final String colDocs_docstate = "docstate"; 	//��������� ���������
	public static final String colDocs_doctypename = "doctypename"; //�������� ���� ���������
	public static final String colDocs_preorvan = "preorvan"; //�����(0) ��� ��������(1)
	public static final String colDocs_termdate = "termdate";	//���� ������ 
	public static final String colDocs_ctpaytype = "ctpaytype"; //����� ������ � ������� �-��
	public static final String colDocs_sumwithoutvat = "sumwithoutvat"; //�������� ����� ��� ���
	public static final String colDocs_sumwithvat = "sumwithvat"; //�������� ����� � ���
	public static final String colDocs_regnum = "regnum"; //���. ����� ���������
	public static final String colDocs_BeePressLinkID = "beepresslinkid"; 

	private DAOFactory factory;
	
	public DocumentDAOJDBC(DAOFactory factory){
		this.factory = factory;
	}
	
	@Override
	public Map<Integer, Integer> getDocCountByPeriod(Date dateStart, Date dateEnd) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rsMap = null;
		Calendar cal = Calendar.getInstance();
		HashMap<Integer, Integer> mapDocCount = new HashMap<Integer, Integer>();
		try{
			conn = factory.getConnection();
			ps = conn.prepareStatement("SELECT count(ID) as doccount, " + colDocs_agentid + " from document where " + colDocs_docdate + ">= ? and " + colDocs_docdate + "< ? group by " + colDocs_agentid);
			ps.setDate(1, new java.sql.Date(dateStart.getTime()));
			cal.setTime(dateEnd);
			cal.add(Calendar.DATE, 1);
			ps.setDate(2, new java.sql.Date(cal.getTime().getTime()));
			rsMap = ps.executeQuery();
			while(rsMap.next()){
				mapDocCount.put(rsMap.getInt(colDocs_agentid), rsMap.getInt("doccount"));
			}
		}catch(SQLException e){
			throw new DAOException(e);
		}finally{
			DAOUtil.close(conn, ps, rsMap);
		}
		return mapDocCount;
	}

}
