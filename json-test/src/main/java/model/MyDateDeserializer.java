package model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lutay.d on 16.08.2015.
 */
public class MyDateDeserializer extends JsonDeserializer<Date> {

    private static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String dateFromJson = jsonParser.getValueAsString();
        try {
            return DATE_FORMATER.parse(dateFromJson);
        } catch (ParseException e) {
            return null;
        }
    }
}
