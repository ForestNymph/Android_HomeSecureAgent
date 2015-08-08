package pl.grudowska.pjwstk.homesecureagent;

/**
 * Created by s.grudowska on 26.07.2015
 */
public class SensorStatusParser {

    public static String parseValue(String sensor, int value) {
        String status;
        switch (sensor) {
            case "temperature": {
                if (value <= -5) {
                    status = Sensor.SensorState.WARNING.toString();
                } else if (value <= 13 && value > -5) {
                    status = Sensor.SensorState.LOW.toString();
                } else if (value > 13 && value < 29) {
                    status = Sensor.SensorState.NORMAL.toString();
                } else if (value >= 29 && value < 35) {
                    status = Sensor.SensorState.HIGH.toString();
                } else {
                    status = Sensor.SensorState.WARNING.toString();
                }
                break;
            }
            case "humidity": {
                if (value <= 40) {
                    status = Sensor.SensorState.LOW.toString();
                } else if (value > 40 && value < 70) {
                    status = Sensor.SensorState.NORMAL.toString();
                } else {
                    status = Sensor.SensorState.WARNING.toString();
                }
                break;
            }
            case "gas": {
                if (value <= 25) {
                    status = Sensor.SensorState.NORMAL.toString();
                } else if (value > 25 && value < 60) {
                    status = Sensor.SensorState.HIGH.toString();
                } else {
                    status = Sensor.SensorState.WARNING.toString();
                }
                break;
            }
            case "smoke": {
                if (value == 0) {
                    status = Sensor.SensorState.NORMAL.toString();
                } else {
                    status = Sensor.SensorState.WARNING.toString();
                }
                break;
            }
            case "carbon monoxide": {
                if (value >= 0 && value < 50) {
                    status = Sensor.SensorState.NORMAL.toString();
                } else {
                    status = Sensor.SensorState.WARNING.toString();
                }
                break;
            }
            /* case "distance": {
                status = Sensor.SensorState.NONE.toString();
                break;
            } */
            case "motion": {
                if (value == 0) {
                    status = Sensor.SensorState.NORMAL.toString();
                } else {
                    status = Sensor.SensorState.WARNING.toString();
                }
                break;
            }
            default:
                status = Sensor.SensorState.NONE.toString();
                break;
        }
        return status;
    }
}
