package biz.dealnote.rest.model.dto;

public class DocClassDetDto {
	public Integer id;
	public Integer docClassId;
	public Integer regNumNext;
	public String regNumPrefix;

	public DocClassDetDto() {}
	
	public DocClassDetDto(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DocClassDetDto [id=" + id + ", docClassId=" + docClassId
				+ ", regNumNext=" + regNumNext + ", regNumPrefix="
				+ regNumPrefix + "]";
	}
}
