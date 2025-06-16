package Subject;

import Observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class WeatherData implements WeatherDataSubject {
    private List<Observer> observers = new ArrayList<>();
    private float temperature, humidity, pressure;
    private int aqi;

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature, humidity, pressure, aqi);
        }
    }

    public void setMeasurements(float t, float h, float p, int a) {
        this.temperature = t;
        this.humidity = h;
        this.pressure = p;
        this.aqi = a;
        notifyObservers();
    }

    public List<Observer> getObservers() {
        return observers;
    }
}