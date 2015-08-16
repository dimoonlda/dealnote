package model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lutay.d on 16.08.2015.
 */
public class MyDateSerializer extends JsonSerializer<Date> {
    private static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(DATE_FORMATER.format(date));
    }
}
