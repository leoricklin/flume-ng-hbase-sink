package org.apache.flume.sink.hbase;

import static org.junit.Assert.*;

import org.hbase.async.PutRequest;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.flume.Event;
import org.apache.flume.event.EventBuilder;
import org.apache.flume.Context;

public class TestSimpleAsyncHbaseEventSerializer {
   @Test
   public void testSetEvent() {
      // Event
      byte[] body = new String("BODY123").getBytes();
      Map <String, String> headers = new HashMap<String, String>();
      for (int icnt=0;icnt<5;icnt++) {
         headers.put("h"+icnt, "v"+icnt);
      }
      headers.put("host", "10.144.123.19");
      Event event = EventBuilder.withBody(body, headers);
      assertEquals("HEADER1","v1",event.getHeaders().get("h1"));
      assertEquals("BODY","BODY123",new String(event.getBody()));
      // Context
      Context cotx = new Context();
      cotx.put("payloadColumn", "evtBody");
      cotx.put("incrementColumn", "iCol");
      cotx.put("rowPrefix", "evKey");
      cotx.put("suffix", "timestamp");
      cotx.put("incrementRow", "iRow");      
      // Serializer
      SimpleAsyncHbaseEventSerializer ser = new SimpleAsyncHbaseEventSerializer();
      ser.initialize("t1".getBytes(), "fam1".getBytes());
      ser.configure(cotx);
      ser.setEvent(event);
      List<PutRequest> actions = ser.getActions();
      for(PutRequest req:actions){
         System.out.println(new String("T=["+new String(req.table())
         +"],K=["+new String(req.key())
         +"],F=["+new String(req.family())
         +"],Q=["+new String(req.qualifier())
         +"],V=["+new String(req.value())+"]"));
      }
      
   }

}
