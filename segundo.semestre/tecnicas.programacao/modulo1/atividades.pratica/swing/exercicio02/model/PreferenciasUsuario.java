package model;

import javax.swing.*;
import java.awt.*;

public class PreferenciasUsuario extends JFrame {

    private JLabel lblTema, lblNotificacao, lblVolume;
    private JComboBox<String> cboxTema;
    private JCheckBox chkNotificacao;
    private JSlider sldVolume;
    private JButton btnSalvar;
    private JTextArea txtAreaPreferencias;

    private Usuario usuario;

    public PreferenciasUsuario() {
        setTitle("Preferências do Usuário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lblTema = new JLabel("Tema: ");
        cboxTema = new JComboBox<>(new String[]{"Claro", "Escuro"});
        gbc.gridx = 0; gbc.gridy = 0; add(lblTema, gbc);
        gbc.gridx = 1; add(cboxTema, gbc);

        lblNotificacao = new JLabel("Notificações: ");
        chkNotificacao = new JCheckBox("Habilitar notificações");
        gbc.gridx = 0; gbc.gridy = 1; add(lblNotificacao, gbc);
        gbc.gridx = 1; add(chkNotificacao, gbc);

        lblVolume = new JLabel("Volume: ");
        sldVolume = new JSlider(0, 100, 50);
        sldVolume.setMajorTickSpacing(20);
        sldVolume.setPaintTicks(true);
        sldVolume.setPaintLabels(true);
        gbc.gridx = 0; gbc.gridy = 2; add(lblVolume, gbc);
        gbc.gridx = 1; add(sldVolume, gbc);

        btnSalvar = new JButton("Salvar");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnSalvar, gbc);

        txtAreaPreferencias = new JTextArea(5, 20);
        txtAreaPreferencias.setEditable(false);
        gbc.gridy = 4;
        add(new JScrollPane(txtAreaPreferencias), gbc);


        btnSalvar.addActionListener(e -> salvarPreferencias());

        cboxTema.addActionListener(e -> atualizarTema());

        atualizarTema();

        setVisible(true);
    }

    private void atualizarTema() {
    String tema = (String) cboxTema.getSelectedItem();

    if (tema.equals("Claro")) {
        getContentPane().setBackground(Color.WHITE);

        lblTema.setForeground(Color.BLACK);
        lblNotificacao.setForeground(Color.BLACK);
        lblVolume.setForeground(Color.BLACK);
        chkNotificacao.setForeground(Color.BLACK);
        chkNotificacao.setBackground(Color.WHITE);
        txtAreaPreferencias.setBackground(Color.LIGHT_GRAY);
        txtAreaPreferencias.setForeground(Color.BLACK);
        sldVolume.setBackground(Color.WHITE);
        sldVolume.setForeground(Color.BLACK);
        cboxTema.setBackground(Color.WHITE);
        cboxTema.setForeground(Color.BLACK);
        btnSalvar.setBackground(UIManager.getColor("Button.background"));
        btnSalvar.setForeground(Color.BLACK);
    } else {
        Color fundoEscuro = new Color(45, 45, 45);
        Color textoClaro = Color.WHITE;

        getContentPane().setBackground(fundoEscuro);

        lblTema.setForeground(textoClaro);
        lblNotificacao.setForeground(textoClaro);
        lblVolume.setForeground(textoClaro);
        chkNotificacao.setForeground(textoClaro);
        chkNotificacao.setBackground(fundoEscuro); 
        txtAreaPreferencias.setBackground(new Color(70, 70, 70));
        txtAreaPreferencias.setForeground(textoClaro);
        sldVolume.setBackground(fundoEscuro);
        sldVolume.setForeground(textoClaro);
        cboxTema.setBackground(new Color(70, 70, 70));
        cboxTema.setForeground(textoClaro);
        btnSalvar.setBackground(new Color(100, 100, 100));
        btnSalvar.setForeground(textoClaro);
    }
}

    private void salvarPreferencias() {
        String tema = (String) cboxTema.getSelectedItem();
        boolean notificacoesHabilitadas = chkNotificacao.isSelected();
        int volume = sldVolume.getValue();

        if (usuario == null) {
            usuario = new Usuario(tema, notificacoesHabilitadas, volume);
        } else {
            usuario.setTema(tema);
            usuario.setNotificacoesHabilitadas(notificacoesHabilitadas);
            usuario.setVolume(volume);
        }

        txtAreaPreferencias.setText(usuario.getResumo());
    }
}