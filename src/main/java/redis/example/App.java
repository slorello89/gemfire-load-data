package redis.example;

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
        String gemfireHost = "geode";
        int gemfirePort = 10334;

        if(System.getProperty("gemfire.host") != null){
            gemfireHost = System.getProperty("gemfire.host");
        }

        if(System.getProperty("gemfire.port") != null){
            gemfirePort = Integer.parseInt(System.getProperty("gemfire.port"));
        }


        System.out.println(String.format("connecting to geode on %s port %d", gemfireHost, gemfirePort));
        try(ClientCache cache = new ClientCacheFactory().addPoolLocator(gemfireHost, gemfirePort).setPoolSubscriptionEnabled(true).create()){
            Region<String, Map<String,Object>> region = cache.<String,Map<String,Object>>createClientRegionFactory(ClientRegionShortcut.PROXY).create("test");
//            Region<String, Customer> customerRegion = cache.<String,Customer>createClientRegionFactory(ClientRegionShortcut.PROXY).create("customerRegion");
            region.registerInterestForAllKeys();
            int i = 0;
            while(true){
                Map<String,Object> entry = new HashMap<>();
                entry.put("name", String.format("foobar:%d", i));
                entry.put("num", i);
                entry.put("id", UUID.randomUUID());
                String keyName = String.format("entry:%d",i);
                region.put(keyName, entry);
//                customerRegion.put(String.format("customer:%d", i), new Customer(String.format("foobar:%d", i), i, UUID.randomUUID()));
                Map<String,Object> rematerialied = region.get(keyName);
//                Customer customerRematerialized = customerRegion.get(String.format("customer:%d", i));
                System.out.println(rematerialied);
//                System.out.println(customerRematerialized);
                Thread.sleep(5000);
                i++;
            }
        }
    }
}
