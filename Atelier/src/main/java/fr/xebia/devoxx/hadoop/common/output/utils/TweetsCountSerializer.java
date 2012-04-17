package fr.xebia.devoxx.hadoop.common.output.utils;

import fr.xebia.devoxx.hadoop.tweetscount.model.TweetsCount;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;


public class TweetsCountSerializer extends JsonSerializer<TweetsCount> {
    
    @Override
    public Class<TweetsCount> handledType() {
        return TweetsCount.class;
    }

    @Override
    public void serialize(TweetsCount tweetsCount, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("count");
        jsonGenerator.writeNumber(tweetsCount.getCount().get());
        jsonGenerator.writeFieldName("hashtag");
        jsonGenerator.writeString(tweetsCount.getHashtag().toString());
        jsonGenerator.writeFieldName("job");
        jsonGenerator.writeString("tweetscount");
        jsonGenerator.writeEndObject();
    }
}
