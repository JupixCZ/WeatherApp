package weatherapp.workers;

import java.util.List;
import weatherapp.domain.weather.MeteocentrumDataContainer;
import weatherapp.enums.ModuleType;
import weatherapp.parsers.MeteocentrumWebParser;
import weatherapp.parsers.WebParser;

public class DataProvider {

    private MeteocentrumDataContainer meteocentrumDataContainer;

    public DataProvider() {
    }

    public void prepareModulesData(List<ModuleType> moduleTypes) {
        for (ModuleType moduleType : moduleTypes) {
            prepareModuleData(moduleType);
        }
    }
    
    public MeteocentrumDataContainer getMeteocentrumData(){
        return meteocentrumDataContainer;
    }

    private void prepareModuleData(ModuleType moduleType) {
        WebParser webParser = null;

        switch (moduleType) {
            case METEOCENTRUM:
                webParser = new MeteocentrumWebParser();
                meteocentrumDataContainer = (MeteocentrumDataContainer) webParser.getMeteoData();
                break;
            default:
                throw new IllegalArgumentException(String.format("Creating web parser for module type %1$d not implemented", moduleType));
        }

    }

}
