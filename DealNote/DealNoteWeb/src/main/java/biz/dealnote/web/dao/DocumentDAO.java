package biz.dealnote.web.dao;

import java.util.Date;
import java.util.Map;

public interface DocumentDAO {
	/**
	 * �������� ���������� ���������� �� ������� ������ �� ������
	 * @param dateStart ������ �������
	 * @param dateEnd ����� �������
	 * @return �����, ��� ���� ��� ��� ������, � �������� ���������� ����������
	 */
	public Map<Integer, Integer> getDocCountByPeriod(Date dateStart, Date dateEnd);
}
