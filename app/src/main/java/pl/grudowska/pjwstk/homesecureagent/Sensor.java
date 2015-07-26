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

        TIMESTAMP("Timestamp"),
        TEMPERATURE("Temperature"),
        HUMIDITY("Humidity"),
        GAS("Gas"),
        SMOKE("Smoke"),
        CARBON_MONOXIDE("CarbonMonoxide"),
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

    private String mNameSensor;
    private String mValueSensor;
    private String mStatusSensor;
    private int mImageSensor;

    public Sensor(String name, String value, String status, int image) {
        this.mNameSensor = name;
        this.mValueSensor = value;
        this.mStatusSensor = status;
        this.mImageSensor = image;
    }

    public void setValueSensor(String valueSensor) {
        mValueSensor = valueSensor;
    }

    public void setStatusSensor(String status) {
        mStatusSensor = status;
    }

    public String getNameSensor() {
        return mNameSensor;
    }

    public String getValueSensor() {
        return mValueSensor;
    }

    public String getStatusSensor() {
        return mStatusSensor;
    }

    public int getImageSensor() {
        return mImageSensor;
    }

    // Return list with all existing sensors
    public static ArrayList<Sensor> initializeSensorDataCreator() {
        ArrayList<Sensor> sensorArray = new ArrayList();

        Sensor timestamp = new Sensor(SensorType.TIMESTAMP.toString(),
                "---", "", R.drawable.update);
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

        sensorArray.add(timestamp);
        sensorArray.add(temp);
        sensorArray.add(humidity);
        sensorArray.add(gas);
        sensorArray.add(smoke);
        sensorArray.add(carbon_monoxide);
        sensorArray.add(distance);
        sensorArray.add(motion);

        return sensorArray;
    }

    // Return list with sensors selected by user only
    public static ArrayList<Sensor> selectedSensorDataCreator(ArrayList<Integer> index) {

        ArrayList<Sensor> sensor = Sensor.initializeSensorDataCreator();
        ArrayList<Sensor> newSensorList = new ArrayList<>();
        int position;

        // Always add timestamp at index zero
        newSensorList.add(sensor.get(0));
        for (int i = 0; i < index.size(); ++i) {
            position = index.get(i) + 1;
            newSensorList.add(sensor.get(position));
        }
        return newSensorList;
    }
}
