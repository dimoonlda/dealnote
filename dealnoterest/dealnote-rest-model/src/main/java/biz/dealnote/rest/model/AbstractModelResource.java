package biz.dealnote.rest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractModelResource<T> {
	private int size;
	private Collection<T> dataArray;
	
	public AbstractModelResource(){
		this(new ArrayList<T>());
	}
	
	public AbstractModelResource(Collection<T> data){
		if(data != null){
			this.dataArray = data;
			this.size = data.size();
		}
	}
	
	public Collection<T> getDataArray() {
		return dataArray;
	}
	
	public void setDataArray(List<T> dataArray) {
		this.dataArray = dataArray;
		this.size = dataArray.size();
	}
	
	public int getSize(){
		return this.size;
	}
}
