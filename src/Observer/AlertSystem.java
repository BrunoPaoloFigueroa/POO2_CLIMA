package Observer;

public class AlertSystem implements Observer {
    private String estadoAlerta = "Sin alertas.";

    @Override
    public void update(float temperature, float humidity, float pressure, int aqi) {
        StringBuilder alerta = new StringBuilder();

        if (temperature > 35) {
            alerta.append("¡ALERTA DE TEMPERATURA! Temperatura = ").append(temperature).append("°C\n");
        }
        if (humidity > 90) {
            alerta.append("¡ALERTA DE HUMEDAD! Humedad = ").append(humidity).append("%\n");
        }
        if (aqi > 150) {
            alerta.append("¡ALERTA DE CALIDAD DE AIRE! AQI = ").append(aqi).append("\n");
        }

        if (alerta.length() > 0) {
            estadoAlerta = "¡ALERTA ACTIVADA! Se ha enviado un correo.";
            EmailSender.enviarCorreo(
                    "andre.zapata@usil.pe",
                    "Alerta Meteorológica",
                    alerta.toString()
            );
        } else {
            estadoAlerta = "Sin alertas.";
        }
    }

    @Override
    public String display() {
        return "";
    }

    public String getEstadoAlerta() {
        return estadoAlerta;
    }
}
