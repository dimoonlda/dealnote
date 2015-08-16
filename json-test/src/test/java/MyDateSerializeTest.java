import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import model.MyDateSerializer;
import model.User;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lutay.d on 16.08.2015.
 */
public class MyDateSerializeTest {

    private static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("dd.MM.yyyy");

    @Test
    public void testDateSerializerAndDeserializer() throws IOException {
        User user = new User();
        user.setAge(122);
        user.setName("Dima");
        user.setBirthday(new Date());

        ObjectMapper mapper = new ObjectMapper();
        //SimpleModule module = new SimpleModule();
        //module.addSerializer(Date.class, new MyDateSerializer());
        //mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(user);
        System.out.println("Serialized: " + serialized);

        User user2 = mapper.readerFor(User.class).readValue(serialized);
        System.out.println("Deserialized date: " + DATE_FORMATER.format(user2.getBirthday()));
    }
}
