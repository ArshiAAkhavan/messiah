package chert;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomListDeserializer extends StdDeserializer<Int> {

    public CustomListDeserializer() {
        this(null);
    }

    protected CustomListDeserializer(Class<?> vc) {
        super(vc);
    }



//    protected CustomListDeserializer(Class<?> vc) {
//        super(vc);
//    }
//
//    public CustomListDeserializer(JavaType valueType) {
//        super(valueType);
//    }
//
//    public CustomListDeserializer(StdDeserializer<?> src) {
//        super(src);
//    }

    @Override
    public Int deserialize(
            JsonParser jsonparser,
            DeserializationContext context)
            throws IOException, JsonProcessingException {

        return new Impl2();
    }


}