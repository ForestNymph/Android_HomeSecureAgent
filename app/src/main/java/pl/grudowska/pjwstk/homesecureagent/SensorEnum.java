package pl.grudowska.pjwstk.homesecureagent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s.grudowska on 12.07.2015.
 */
public enum SensorEnum {
    TEMPERATURE("Temperature"),
    HUMADITY("humadity"),
    GAS("Gas"),
    SMOKE("Smoke"),
    CARBON_MONOXIDE("Carbon_monoxide"),
    DISTANCE("Distance"),
    MOTION("Motion");

    private String stringSensor;

    SensorEnum(String toString) {
        stringSensor = toString;
    }

    @Override
    public String toString() {
        return stringSensor;
    }

    public static List getSensorList() {
        List<SensorEnum> enums = new ArrayList<SensorEnum>();
        return enums;
    }
}