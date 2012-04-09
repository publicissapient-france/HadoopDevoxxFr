package fr.xebia.devoxx.hadoop;


import fr.xebia.devoxx.hadoop.fr.xebia.devoxx.common.output.TwitterStreamCountSerializer;
import fr.xebia.devoxx.hadoop.mostRt.model.TwitterStreamCount;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class TestTwitterStreamCount {
    @Test
    public void testTwitterStreamCountComparison() {
        TwitterStreamCount twitterStreamCount = new TwitterStreamCount();
        twitterStreamCount.setMessage(new Text("Réseau mobile : Orange menace Free http://t.co/P1d0IuKu)"));
        twitterStreamCount.setUser(new Text("@itel"));
        twitterStreamCount.setCount(new LongWritable(1));
        TwitterStreamCount twitterStreamCount2 = new TwitterStreamCount();
        twitterStreamCount2.setMessage(new Text("Réseau mobile : Orange menace Free http://t.co/P1d0IuKu)"));
        twitterStreamCount2.setUser(new Text("@itel"));
        twitterStreamCount2.setCount(new LongWritable(2));
        assertEquals(-1, twitterStreamCount2.compareTo(twitterStreamCount));
        assertFalse(twitterStreamCount2.equals(twitterStreamCount));
        assertFalse(twitterStreamCount2.hashCode() == twitterStreamCount.hashCode());
    }

    @Test
    public void testSerialization() throws IOException {
        TwitterStreamCount twitterStreamCount = new TwitterStreamCount();
        twitterStreamCount.setMessage(new Text("Réseau mobile : Orange menace Free http://t.co/P1d0IuKu)"));
        twitterStreamCount.setUser(new Text("@itel"));
        twitterStreamCount.setCount(new LongWritable(1));
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule hadoopModule = new SimpleModule("HadoopModule", new Version(1, 0, 0, null));
        hadoopModule.addSerializer(new TwitterStreamCountSerializer());
        mapper.registerModule(hadoopModule);
        StringWriter out = new StringWriter();
        mapper.writeValue(out, twitterStreamCount);

        assertEquals("{\"count\":1,\"user\":\"@itel\",\"message\":\"Réseau mobile : Orange menace Free http://t.co/P1d0IuKu)\",\"job\":\"mostRt\"}", out.toString());

    }
}
