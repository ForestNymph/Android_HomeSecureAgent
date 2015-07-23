package pl.grudowska.pjwstk.homesecureagent;

/**
 * Created by s.grudowska on 11.07.2015
 */

public class SensorDataParser {

    private String rawSensorData = null;

    public SensorDataParser() {
        rawSensorData = getLatestSensorsRawData();
    }

    private String getLatestSensorsRawData() {

        String data = null;
        String sensorData = null;

        // TODO Parse latest element
        data = new DataDownloader().getData("http://192.168.0.10:8080/info");
        int index = data.lastIndexOf("last_timestamp") + 3;
        String lasttimestamp = data.substring(index, index + 10);
        sensorData = "http://192.168.0.10:8080/getjson" + lasttimestamp;

        return sensorData;
    }

    public String checkSensorResult() {

        // TODO Parse, check and set sensor data
        // range checking sensors
        RangeOfSensor range = new RangeOfSensor();

        String tempValue = parseSensorData(Sensor.SensorType.TEMPERATURE, 0);
        RangeOfSensor.isCorrectRange(Sensor.SensorType.TEMPERATURE, tempValue);

        String humadityValue = parseSensorData(Sensor.SensorType.HUMIDITY, 0);
        RangeOfSensor.isCorrectRange(Sensor.SensorType.HUMIDITY, humadityValue);

        //TODO Add all sensors

        return null;
    }

    private String parseSensorData(Enum sensor, int c) {
        rawSensorData.lastIndexOf(sensor.toString(), c);
        String sensor_value = "";
        return sensor_value;
    }
}
