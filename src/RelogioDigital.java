import javax.swing.*;
import java.awt.*;

public class RelogioDigital extends JFrame {

    private JLabel labelRelogio;
    private JLabel labelCronometro;
    private JLabel labelTemporizador;

    private Timer timerRelogio;
    private Timer timerCronometro;
    private Timer timerTemporizador;

    private int segundosCronometro;
    private int segundosTemporizador;

    private JTextField textFieldTemporizador;

    public RelogioDigital() {
        super("Relógio Digital");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 200));

        initComponents();
        initTimers();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JPanel bottomPanel = new JPanel(new FlowLayout());

        labelRelogio = new JLabel();
        labelCronometro = new JLabel();
        labelTemporizador = new JLabel();

        JButton startCronometroButton = new JButton("Iniciar Cronômetro");
        JButton resetCronometroButton = new JButton("Zerar Cronômetro");

        textFieldTemporizador = new JTextField(10);
        JButton startTemporizadorButton = new JButton("Iniciar Temporizador");
        JButton resetTemporizadorButton = new JButton("Zerar Temporizador");

        // Adiciona os componentes ao painel
        topPanel.add(new JLabel("Relógio:"));
        topPanel.add(labelRelogio);

        centerPanel.add(new JLabel("Cronômetro:"));
        centerPanel.add(labelCronometro);
        centerPanel.add(startCronometroButton);
        centerPanel.add(resetCronometroButton);

        centerPanel.add(new JLabel("Temporizador:"));
        centerPanel.add(labelTemporizador);

        JPanel tempoPanel = new JPanel(new FlowLayout());
        tempoPanel.add(new JLabel("Definir tempo (segundos):"));
        tempoPanel.add(textFieldTemporizador);

        bottomPanel.add(tempoPanel);
        bottomPanel.add(startTemporizadorButton);
        bottomPanel.add(resetTemporizadorButton);

        // Eventos dos botões
        startCronometroButton.addActionListener(e -> iniciarCronometro());
        resetCronometroButton.addActionListener(e -> resetarCronometro());

        startTemporizadorButton.addActionListener(e -> iniciarTemporizador());
        resetTemporizadorButton.addActionListener(e -> resetarTemporizador());

        // Adiciona os painéis ao frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void initTimers() {
        // Timer para o relógio
        timerRelogio = new Timer(1000, e -> atualizarRelogio());
        timerRelogio.start();

        // Timer para o cronômetro (inicializado, mas não iniciado)
        timerCronometro = new Timer(1000, e -> atualizarCronometro());
        segundosCronometro = 0;

        // Timer para o temporizador (inicializado, mas não iniciado)
        timerTemporizador = new Timer(1000, e -> atualizarTemporizador());
        segundosTemporizador = 0;
    }

    // Insira aqui o restante do seu código específico, se houver.

    private void atualizarRelogio() {
        labelRelogio.setText(getHoraAtual());
    }

    private void iniciarCronometro() {
        timerCronometro.start();
    }

    private void resetarCronometro() {
        timerCronometro.stop();
        segundosCronometro = 0;
        labelCronometro.setText(formatarTempo(segundosCronometro));
    }

    private void atualizarCronometro() {
        segundosCronometro++;
        labelCronometro.setText(formatarTempo(segundosCronometro));
    }

    private void iniciarTemporizador() {
        try {
            int tempoDigitado = Integer.parseInt(textFieldTemporizador.getText());
            segundosTemporizador = tempoDigitado;
            labelTemporizador.setText(formatarTempo(segundosTemporizador));
            timerTemporizador.start();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número válido para o temporizador.");
        }
    }

    private void resetarTemporizador() {
        timerTemporizador.stop();
        segundosTemporizador = 0;
        labelTemporizador.setText(formatarTempo(segundosTemporizador));
    }

    private void atualizarTemporizador() {
        if (segundosTemporizador > 0) {
            segundosTemporizador--;
            labelTemporizador.setText(formatarTempo(segundosTemporizador));
        } else {
            // O temporizador chegou a zero
            timerTemporizador.stop();
            JOptionPane.showMessageDialog(this, "Tempo zerado!");
        }
    }

    private String getHoraAtual() {
        return String.format("%tT", System.currentTimeMillis());
    }

    private String formatarTempo(int segundos) {
        int minutos = segundos / 60;
        int segundosRestantes = segundos % 60;
        return String.format("%02d:%02d", minutos, segundosRestantes);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RelogioDigital::new);
    }
}
