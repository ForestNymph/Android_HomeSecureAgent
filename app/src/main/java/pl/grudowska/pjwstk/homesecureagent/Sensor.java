package pl.grudowska.pjwstk.homesecureagent;

/**
 * Created by s.grudowska on 18.07.2015.
 */
public class Sensor {


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
