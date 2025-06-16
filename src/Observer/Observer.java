package Observer;

public interface Observer {
    void update(float temperatura, float humedad, float presion, int aqi);
    String display();
}
