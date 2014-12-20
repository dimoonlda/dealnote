package biz.dealnote.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import biz.dealnote.web.beans.Client;

public class ClientDaoJdbc implements ClientDAO {

	public static final String TABLE_NAME = "CLIENT";
	public static final String COL_CLIENT_ID = "ID";
	public static final String COL_CLIENT_FNAME = "FNAME";
	public static final String COL_CLIENT_ADDRESS_LOCATION = "ADDRESSLOCATION";
	public static final String COL_CLIENT_AGENT_ID = "AGENTID";
	
	
	
	private DAOFactory daoFactory;
	
	public ClientDaoJdbc(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Client> getClientsByAgent(int agentId) {
		List<Client> clientsList = new ArrayList<Client>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rsClients = null;
		try {
			conn = daoFactory.getConnection();
			ps = conn.prepareStatement("SELECT "
					+ COL_CLIENT_ID + ", "
					+ COL_CLIENT_FNAME + ", "
					+ COL_CLIENT_ADDRESS_LOCATION 
					+ " FROM " + TABLE_NAME
					+ " WHERE " + COL_CLIENT_AGENT_ID + " = ?");
			ps.setInt(0, agentId);
			rsClients = ps.executeQuery();
			while(rsClients.next()){
				Client client = new Client();
				client.setId(rsClients.getInt(COL_CLIENT_ID));
				client.setFName(rsClients.getString(COL_CLIENT_FNAME));
				client.setAddressLocation(rsClients.getString(COL_CLIENT_ADDRESS_LOCATION));
				clientsList.add(client);
			}
		} catch (SQLException e) {
			DAOUtil.close(conn, ps, rsClients);
		}
		return clientsList;
	}

	@Override
	public Client getClietnById(int clientId) {
		return null;
	}

}
