package biz.dealnote.rest.model.dto;

public class RestServiceInfoDto {
	/**
	 * Database version 
	 */
	public String dbVersion;
	/**
	 * Version of data's swap for mobile clients 
	 */
	public Integer mobileSwapVersion;
	/**
	 * Url for downloading current mobile version
	 */
	public String mobileClientUrlUpdate;
	/**
	 * Current mobile version
	 */
	public String mobileClientCurrentVersion;
	
	@Override
	public String toString() {
		return "RestServiceInfoDto [dbVersion=" + dbVersion
				+ ", mobileSwapVersion=" + mobileSwapVersion
				+ ", mobileClientUrlUpdate=" + mobileClientUrlUpdate
				+ ", mobileClientCurrentVersion=" + mobileClientCurrentVersion
				+ "]";
	}
}
