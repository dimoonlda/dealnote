package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.Route;

public interface RouteDao {
	public Collection<Route> getRoutes();
	public Route getRouteById(int routeId);
}
