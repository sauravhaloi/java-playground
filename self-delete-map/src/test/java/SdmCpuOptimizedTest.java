import org.example.SdmCpuOptimized;
import org.example.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SdmCpuOptimizedTest {

    private final SdmCpuOptimized<String, String> sdm = new SdmCpuOptimized<>();

    @AfterEach
    public void tearDown() {
        sdm.deleteAll();
    }

    @Test
    public void putTest() {
        sdm.put("key1", "value1", 5000);
        sdm.put("key2", "value2", 10000);
        sdm.put("key3", "value3", 15000);

        assert(sdm.size() == 3);

        assertEquals(sdm.get("key1"), "value1");
        assertEquals(sdm.get("key2"), "value2");
        assertEquals(sdm.get("key3"), "value3");
    }

    @Test
    public void getTest() {
        assertNull(sdm.get("key"));

        sdm.put("key", "value", 5000);

        assert(sdm.size() == 1);

        assertEquals(sdm.get("key"), "value");

        // wait for 5 seconds for the key to expire

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertNull(sdm.get("key"));

    }

    @Test
    public void removeTest() {

        sdm.put("key1", "value1", 5000);
        sdm.put("key2", "value2", 10000);
        sdm.put("key3", "value3", 15000);

        assert(sdm.size() == 3);

        sdm.remove("key1");
        assertNull(sdm.get("key1"));

        sdm.remove("key2");
        assertNull(sdm.get("key2"));

        sdm.remove("key3");
        assertNull(sdm.get("key3"));

        assert(sdm.size() == 0);
    }
}
