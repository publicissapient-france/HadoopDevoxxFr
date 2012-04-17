package fr.xebia.devoxx.hadoop.common.output.utils;

import fr.xebia.devoxx.hadoop.occurence.model.TwitterWordCount;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;


public class TwitterWordCountSerializer extends JsonSerializer<TwitterWordCount> {
    
    @Override
    public Class<TwitterWordCount> handledType() {
        return TwitterWordCount.class;
    }

    @Override
    public void serialize(TwitterWordCount twitterWordCount, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("count");
        jsonGenerator.writeNumber(twitterWordCount.getCount().get());
        jsonGenerator.writeFieldName("word");
        jsonGenerator.writeString(twitterWordCount.getWord().toString());
        jsonGenerator.writeFieldName("job");
        jsonGenerator.writeString("occurence");
        jsonGenerator.writeEndObject();
    }
}
