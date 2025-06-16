package Subject;

import Observer.Observer;

public interface WeatherDataSubject {
    void registerObserver(Observer o);
    void notifyObservers();
}