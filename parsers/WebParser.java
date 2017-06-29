package weatherapp.parsers;

import weatherapp.domain.weather.MeteoDataContainer;
import weatherapp.enums.ModuleType;

public abstract class WebParser {
    
    protected ModuleType moduleType;
    
    public abstract ModuleType getModuleType();
    
    public abstract MeteoDataContainer getMeteoData();
    
    public abstract MeteoDataContainer getTestMeteoData();
    
    protected abstract void healthCheck();
    
    protected abstract void refreshMeteoData();
    
}
