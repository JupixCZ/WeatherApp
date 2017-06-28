package weatherapp.enums;

import java.util.Arrays;
import java.util.List;

public enum ModuleType {

    METEOCENTRUM;

    public static List<ModuleType> getAllModules() {
        return Arrays.asList(ModuleType.values());
    }

}
