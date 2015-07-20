package pl.grudowska.pjwstk.homesecureagent;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by s.grudowska on 11.07.2015.
 */

public class SensorDataParser {

    private String rawSensorData = null;

    public SensorDataParser() {
        rawSensorData = getLatestSensorsRawData();
    }

    private String getLatestSensorsRawData() {

        String data = null;
        String sensorData = null;
        try {
            // TODO Parse latest element
            data = new DataDownloader().getData(new URL("http://192.168.0.10:8080/info"));
            int index = data.lastIndexOf("last_timestamp") + 3;
            String lasttimestamp = data.substring(index, index + 10);
            sensorData = "http://192.168.0.10:8080/getjson" + lasttimestamp;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return sensorData;
    }

    public String resulSensorData() {

        // TODO Parse, check and set sensor data
        // range checking sensors
        RangeOfSensor range = new RangeOfSensor();

        String tempValue = parseSensorData(SensorEnum.TEMPERATURE, 0);
        RangeOfSensor.isCorrectRange(SensorEnum.TEMPERATURE, tempValue);

        String humadityValue = parseSensorData(SensorEnum.HUMIDITY, 0);
        RangeOfSensor.isCorrectRange(SensorEnum.HUMIDITY, humadityValue);

        //TODO Add all sensors

        return null;
    }

    private String parseSensorData(Enum sensor, int c) {
        rawSensorData.lastIndexOf(sensor.toString(), c);
        String sensor_value = "";
        return sensor_value;
    }
}
