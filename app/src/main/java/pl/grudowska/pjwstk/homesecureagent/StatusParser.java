package pl.grudowska.pjwstk.homesecureagent;

/**
 * Created by s.grudowska on 26.07.2015
 */
public class StatusParser {

    public static String parseValue(String sensor, int value) {
        String status = null;
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
                status = Sensor.SensorState.NONE.toString();
                break;
            }
            case "smoke": {
                status = Sensor.SensorState.NONE.toString();
                break;
            }
            case "carbon monoxide": {
                status = Sensor.SensorState.NONE.toString();
                break;
            }
            case "distance": {
                status = Sensor.SensorState.NONE.toString();
                break;
            }
            case "motion": {
                status = Sensor.SensorState.NONE.toString();
                break;
            }
            default:
                status = Sensor.SensorState.NONE.toString();
                break;
        }
        return status;
    }
}
