package pl.grudowska.pjwstk.homesecureagent;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by s.grudowska on 18.07.2015.
 */
public class Sensor {

    public enum SensorType {

        TEMPERATURE("Temperature"),
        HUMIDITY("Humidity"),
        GAS("Gas"),
        SMOKE("Smoke"),
        CARBON_MONOXIDE("Carbon Monoxide"),
        DISTANCE("Distance"),
        MOTION("Motion");

        private String stringSensor;

        SensorType(String toString) {
            stringSensor = toString;
        }

        @Override
        public String toString() {
            return stringSensor;
        }

        public static ArrayList getSensorList() {
            // Create ArrayList of enum values
            ArrayList enums = new ArrayList<>(Arrays.asList(SensorType.values()));
            ArrayList sensors = new ArrayList();

            for (int i = 0; i < enums.size(); ++i) {
                sensors.add(i, enums.get(i).toString());
            }
            return sensors;
        }
    }

    private String nameSensor;
    private String valueSensor;
    private int imageSensor;

    public Sensor(String name, String value, int image) {
        this.nameSensor = name;
        this.valueSensor = value;
        this.imageSensor = image;
    }

    public void setNameSensor(String nameSensor) {

        this.nameSensor = nameSensor;
    }

    public void setValueSensor(String valueSensor) {

        this.valueSensor = valueSensor;
    }

    public void setImageSensor(int imageSensor) {

        this.imageSensor = imageSensor;
    }

    public String getNameSensor() {
        return nameSensor;
    }

    public String getValueSensor() {
        return valueSensor;
    }

    public int getImageSensor() {
        return imageSensor;
    }
}
