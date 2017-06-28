package weatherapp.domain.weather;

public class MeteocentrumDataContainer extends MeteoDataContainer {

    private Bio bio;

    public MeteocentrumDataContainer() {
        bio = new Bio(1);
    }

    public Bio getBio() {
        return bio;
    }
}
