package biz.dealnote.web.web.editors;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeEditor extends PropertyEditorSupport {
	private final String format;

	public DateTimeEditor(String format) {
		this.format = format;
	}

	@Override
	public String getAsText() {
		if (getValue() != null && getValue() != "") {
			return new SimpleDateFormat(this.format)
					.format(((DateTime) getValue()).toDate());
		}
		return null;
	}

	@Override
	public void setAsText(String text) {
		if (text != null && !text.isEmpty()) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormat
					.forPattern(this.format);
			try {
				setValue(dateTimeFormatter.parseDateTime(text));
			} catch (IllegalArgumentException e) {
				setValue(null);
			}
		}
	}
}
