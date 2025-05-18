package View;

import javax.swing.*;
import java.awt.*;

public class EsqueceuSenha extends JFrame {
    private JTextField emailField;

    public EsqueceuSenha() {
        super("Recuperar Senha - Emergia");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Fundo com imagem igual ao LoginPage
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/View/Images/Login_Page.jpg"));
        JLabel background = new JLabel(bgIcon);
        background.setLayout(new BorderLayout());
        setContentPane(background);

        // Painel de recuperação translúcido
        JPanel recoveryPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        recoveryPanel.setOpaque(false);
        recoveryPanel.setPreferredSize(new Dimension(450, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Título
        gbc.gridy = 0;
        JLabel title = new JLabel("RECUPERAR SENHA", SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        recoveryPanel.add(title, gbc);

        // E-mail label
        gbc.gridy = 1;
        JLabel emailLabel = new JLabel("DIGITE SEU E-MAIL", SwingConstants.CENTER);
        emailLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        emailLabel.setForeground(Color.WHITE);
        recoveryPanel.add(emailLabel, gbc);

        // Campo de e-mail
        gbc.gridy = 2;
        emailField = new JTextField(25);
        emailField.setFont(new Font("Dialog", Font.PLAIN, 16));
        emailField.setHorizontalAlignment(JTextField.CENTER);
        recoveryPanel.add(emailField, gbc);

        // Botão Enviar
        gbc.gridy = 3;
        JButton enviar = new JButton("RECUPERAR SENHA");
        enviar.setFont(new Font("Dialog", Font.BOLD, 14));
        enviar.setFocusPainted(false);
        enviar.addActionListener(e -> {
            String email = emailField.getText().trim();
            if (!email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Se o e-mail estiver cadastrado, você receberá instruções para redefinir sua senha.",
                        "Recuperação enviada", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LoginPage().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, insira um e-mail válido.");
            }
        });
        recoveryPanel.add(enviar, gbc);

        // Botão Voltar
        gbc.gridy = 4;
        JButton voltar = new JButton("VOLTAR");
        voltar.setFont(new Font("Dialog", Font.BOLD, 14));
        voltar.setFocusPainted(false);
        voltar.addActionListener(e -> {
            dispose();
            new LoginPage().setVisible(true);
        });
        recoveryPanel.add(voltar, gbc);

        // Wrapper à esquerda com margem
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 150, 320));
        wrapper.setOpaque(false);
        wrapper.add(recoveryPanel);
        background.add(wrapper, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EsqueceuSenha().setVisible(true));
    }
}
