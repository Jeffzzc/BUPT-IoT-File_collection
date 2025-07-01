package Test.Read;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import main.Entity.Module;
import main.Read.ModuleReader;

public class ModuleReaderTest {
    @Test
    void testInitialize() {
        ModuleReader.initialize();
        int i = 0;
        for (Module module : ModuleReader.modules){
            assertEquals(module, ModuleReader.modules.get(i));
            i++;
        }
    }
}
