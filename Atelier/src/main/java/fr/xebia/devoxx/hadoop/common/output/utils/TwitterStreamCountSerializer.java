package fr.xebia.devoxx.hadoop.common.output.utils;

import fr.xebia.devoxx.hadoop.mostRt.model.TwitterStreamCount;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;

public class TwitterStreamCountSerializer extends JsonSerializer<TwitterStreamCount> {

    @Override
    public Class<TwitterStreamCount> handledType() {
        return TwitterStreamCount.class;
    }

    @Override
    public void serialize(TwitterStreamCount twitterStreamCount, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("count");
        jsonGenerator.writeNumber(twitterStreamCount.getCount().get());
        jsonGenerator.writeFieldName("user");
        jsonGenerator.writeString(twitterStreamCount.getUser().toString());
        jsonGenerator.writeFieldName("message");
        jsonGenerator.writeString(twitterStreamCount.getMessage().toString());
        jsonGenerator.writeFieldName("job");
        jsonGenerator.writeString("mostRt");
        jsonGenerator.writeEndObject();
    }
}
