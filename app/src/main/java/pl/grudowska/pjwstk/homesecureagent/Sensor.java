package pl.grudowska.pjwstk.homesecureagent;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by s.grudowska on 18.07.2015
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
    private String statusSensor;
    private int imageSensor;

    public Sensor(String name, String value, String status, int image) {
        this.nameSensor = name;
        this.valueSensor = value;
        this.statusSensor = status;
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

    public void setStatusSensor(String status) {

        this.statusSensor = status;
    }

    public String getNameSensor() {
        return nameSensor;
    }

    public String getValueSensor() {
        return valueSensor;
    }

    public String getStatusSensor() {
        return statusSensor;
    }

    public int getImageSensor() {
        return imageSensor;
    }

    static ArrayList sensorDataCreator() {
        ArrayList sensorArray = new ArrayList();

        Sensor temp = new Sensor("Temperature", "25℃", "Normal", R.drawable.icon_temperature);
        Sensor humidity = new Sensor("Humidity", "78%", "High", R.drawable.icon_humidity);
        Sensor gas = new Sensor("Gas", "1034", "Warning", R.drawable.icon_gas);
        Sensor smoke = new Sensor("Smoke", "---", "Warning", R.drawable.icon_smokefire);
        Sensor carbon_monoxide = new Sensor("Carbon Monoxide", "---", "Warning", R.drawable.icon_co2);
        Sensor distance = new Sensor("Distance", "---", "Warning", R.drawable.icon_distance);
        Sensor motion = new Sensor("Motion", "---", "Warning", R.drawable.icon_motion);

        sensorArray.add(temp);
        sensorArray.add(humidity);
        sensorArray.add(gas);
        sensorArray.add(smoke);
        sensorArray.add(carbon_monoxide);
        sensorArray.add(distance);
        sensorArray.add(motion);

        return sensorArray;
    }
}
