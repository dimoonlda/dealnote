package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.Measure;

public interface MeasureDao {
	public Collection<Measure> getAllMeasure();
	public Measure getMeasureById(int id);
	public void save(Measure measure);
	public void delete(Measure measure);
}
