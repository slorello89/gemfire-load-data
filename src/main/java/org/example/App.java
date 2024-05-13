package org.example;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        String gemfireHost = "localhost";
        int gemfirePort = 10334;

        if(System.getProperty("gemfire.host") != null){
            gemfireHost = System.getProperty("gemfire.host");
        }

        if(System.getProperty("gemfire.port") != null){
            gemfirePort = Integer.parseInt(System.getProperty("gemfire.port"));
        }


        try(ClientCache cache = new ClientCacheFactory().addPoolLocator("localhost", 10334).create()){
            Region<String, Map<String,Object>> region = cache.<String,Map<String,Object>>createClientRegionFactory(ClientRegionShortcut.PROXY).create("testRegion");
            int i = 0;
            while(true){
                Map<String,Object> entry = new HashMap<>();
                entry.put("name", String.format("foobar:%d", i));
                entry.put("num", i);
                entry.put("id", UUID.randomUUID());
                String keyName = String.format("entry:%d",i);
                region.put(keyName, entry);
                Map<String,Object> rematerialied = region.get(keyName);
                System.out.println(rematerialied);
                Thread.sleep(5000);
                i++;
            }
        }
    }
}
