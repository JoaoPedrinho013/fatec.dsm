package model;

public class Usuario {
    private String tema;
    private boolean notificacoesHabilitadas;
    private int volume;

    public Usuario(String tema, boolean notificacoesHabilitadas, int volume) {
        this.tema = tema;
        this.notificacoesHabilitadas = notificacoesHabilitadas;
        this.volume = volume;
    }

    public String getTema() {
        return tema;
    }
    public boolean notificacoesHabilitadas() {
        return notificacoesHabilitadas;
    }
    public int getVolume() {
        return volume;
    }
    public void setTema(String tema) {
        this.tema = tema;
    }
    public void setNotificacoesHabilitadas(boolean notificacoesHabilitadas) {
        this.notificacoesHabilitadas = notificacoesHabilitadas;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public String getResumo() {
        return "Tema: " + tema + " | Notificações Habilitadas: " + notificacoesHabilitadas + " | Volume: " + volume;
    }
}
