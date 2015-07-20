package pl.grudowska.pjwstk.homesecureagent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by s.grudowska on 12.07.2015
 */


public enum SensorEnum {

    TEMPERATURE("Temperature"),
    HUMIDITY("Humidity"),
    GAS("Gas"),
    SMOKE("Smoke"),
    CARBON_MONOXIDE("Carbon Monoxide"),
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

        // Create ArrayList of enum values
        ArrayList enums = new ArrayList<SensorEnum>(Arrays.asList(SensorEnum.values()));
        return enums;
    }
}