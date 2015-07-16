package pl.grudowska.pjwstk.homesecureagent;

/**
 * Created by s.grudowska on 12.07.2015.
 */
public enum Sensor {
    TEMPERATURE("temperature"),
    HUMADITY("humadity"),
    GAS("gas"),
    SMOKE("smoke"),
    CARBON_MONOXIDE("carbon_monoxide"),
    DISTANCE("distance"),
    MOTION("motion");

    private String stringSensor;

    Sensor(String toString) {
        stringSensor = toString;
    }

    @Override
    public String toString() {
        return stringSensor;
    }
}