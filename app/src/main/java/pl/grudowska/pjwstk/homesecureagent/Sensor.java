package pl.grudowska.pjwstk.homesecureagent;

import java.util.ArrayList;

/**
 * Created by s.grudowska on 18.07.2015
 */
public class Sensor {

    public enum SensorState {
        DANGEROUS("Dengerous"),
        WARNING("Warning"),
        HIGH("High"),
        NORMAL("Normal"),
        LOW("Low"),
        NONE("None");

        private String stringSensor;

        SensorState(String toString) {
            stringSensor = toString;
        }

        @Override
        public String toString() {
            return stringSensor;
        }
    }

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
        ArrayList<Sensor> sensorArray = new ArrayList();

        Sensor temp = new Sensor(SensorType.TEMPERATURE.toString(), "---",
                SensorState.NONE.toString(), R.drawable.icon_temperature);
        Sensor humidity = new Sensor(SensorType.HUMIDITY.toString(), "---",
                SensorState.NONE.toString(), R.drawable.icon_humidity);
        Sensor gas = new Sensor(SensorType.GAS.toString(), "---",
                SensorState.NONE.toString(), R.drawable.icon_gas);
        Sensor smoke = new Sensor(SensorType.SMOKE.toString(), "---",
                SensorState.NONE.toString(), R.drawable.icon_smokefire);
        Sensor carbon_monoxide = new Sensor(SensorType.CARBON_MONOXIDE.toString(), "---",
                SensorState.NONE.toString(), R.drawable.icon_co2);
        Sensor distance = new Sensor(SensorType.DISTANCE.toString(), "---",
                SensorState.NONE.toString(), R.drawable.icon_distance);
        Sensor motion = new Sensor(SensorType.MOTION.toString(), "---",
                SensorState.NONE.toString(), R.drawable.icon_motion);

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
