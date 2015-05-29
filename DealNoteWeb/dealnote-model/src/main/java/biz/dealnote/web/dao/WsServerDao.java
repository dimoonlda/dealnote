package biz.dealnote.web.dao;

import java.util.Collection;
import java.util.Optional;

import biz.dealnote.web.model.WsServer;

public interface WsServerDao {
	public Collection<WsServer> getServers();
	public Optional<WsServer> getServerById(Integer id);
	public void save(WsServer server);
	public void delete(WsServer server);
}
