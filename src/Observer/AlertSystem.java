package Observer;

public class AlertSystem implements Observer {
    private String alertas = "";

    @Override
    public void update(float temperatura, float humedad, float presion, int aqi) {
        StringBuilder sb = new StringBuilder();
        if (temperatura > 35) sb.append("¡Alerta! Temperatura alta: " + temperatura + "°C\n");
        if (humedad > 90) sb.append("¡Alerta! Humedad alta: " + humedad + "%\n");
        if (aqi > 150) sb.append("¡Alerta! AQI elevado: " + aqi + "\n");
        alertas = sb.toString();

        if (!alertas.isEmpty()) {
            // ENVÍA CORREO
            EmailSender.enviarCorreo(
                    "bruno.figueroa@usil.pe", // cambia esto a tu correo destino real
                    "🚨 Alerta de Estación Meteorológica",
                    alertas
            );
        }
    }

    @Override
    public String display() {
        return alertas.isEmpty() ? "Sin alertas." : alertas;
    }
}
