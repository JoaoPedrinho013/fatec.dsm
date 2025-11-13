package model;

import javax.swing.*;
import java.awt.*;

public class CadastroForm extends JFrame {

    private JLabel lblNome, lblIdade, lblSexo, lblResultado;
    private JTextField txtNome;
    private JSpinner spnIdade;
    private JRadioButton rbMasculino, rbFeminino;
    private JButton btnEnviar;
    private ButtonGroup grupoSexo;

    public CadastroForm() {
        setTitle("Formulário de Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 3, 3));

        lblNome = new JLabel("Nome: ");
        txtNome = new JTextField();

        lblIdade = new JLabel("Idade: ");
        spnIdade = new JSpinner(new SpinnerNumberModel(0, 0, 120, 1));

        lblSexo = new JLabel("Sexo: ");
        rbMasculino = new JRadioButton("Masculino");
        rbFeminino = new JRadioButton("Feminino");

        grupoSexo = new ButtonGroup();
        grupoSexo.add(rbMasculino);
        grupoSexo.add(rbFeminino);

        btnEnviar = new JButton("Enviar");
        lblResultado = new JLabel(" ");
        btnEnviar.setSize(20, 20);

        add(lblNome); add(txtNome);
        add(lblIdade); add(spnIdade);
        add(lblSexo);

        JPanel painelSexo = new JPanel();
        painelSexo.add(rbMasculino);
        painelSexo.add(rbFeminino);
        add(painelSexo);

        add(new JLabel()); add(btnEnviar);
        add(new JLabel("Resultado: "));
        add(lblResultado);

        btnEnviar.addActionListener(e -> enviarDados());

        setVisible(true);
    }

    private void enviarDados() {
        String nome = txtNome.getText().trim();
        int idade = (int) spnIdade.getValue();
        String sexo = rbMasculino.isSelected() ? "Masculino" :
                      rbFeminino.isSelected() ? "Feminino" : "";

        if (nome.isEmpty() && sexo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(nome, idade, sexo);
        lblResultado.setText(cliente.getResumo());
    }
}