package fr.xebia.devoxx.hadoop;

import fr.xebia.devoxx.hadoop.common.output.utils.TwitterWordCountSerializer;
import fr.xebia.devoxx.hadoop.occurence.model.TwitterWordCount;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class TestTwitterWordCount {

    @Test
    public void testSerialization() throws IOException {
        TwitterWordCount twitterWordCount = new TwitterWordCount();
        twitterWordCount.setWord(new Text("Word"));
        twitterWordCount.setCount(new LongWritable(1));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule hadoopModule = new SimpleModule("HadoopModule", new Version(1, 0, 0, null));
        hadoopModule.addSerializer(new TwitterWordCountSerializer());
        mapper.registerModule(hadoopModule);
        StringWriter out = new StringWriter();
        mapper.writeValue(out, twitterWordCount);

        assertEquals("{\"count\":1,\"word\":\"Word\",\"job\":\"occurence\"}", out.toString());
    }
}
