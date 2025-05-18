package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import java.awt.geom.RoundRectangle2D;

public class LoginPage extends JFrame {
    private JTextField usuarioField;
    private JPasswordField senhaField;

    public LoginPage() {
        super("Login - Emergia");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/View/Images/Login_Page.jpg"));
        JLabel background = new JLabel(bgIcon);
        background.setLayout(new BorderLayout());
        setContentPane(background);

        JPanel loginPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        loginPanel.setOpaque(false);
        loginPanel.setPreferredSize(new Dimension(450, 550));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Título
        gbc.gridy = 0;
        JLabel loginLabel = new JLabel("LOGIN", SwingConstants.CENTER);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 28));
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(loginLabel, gbc);

        // Usuário
        gbc.gridy = 1;
        JLabel usuarioLabel = new JLabel("USUÁRIO", SwingConstants.CENTER);
        usuarioLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        usuarioLabel.setForeground(Color.WHITE);
        usuarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(usuarioLabel, gbc);

        gbc.gridy = 2;
        usuarioField = new JTextField(20);
        usuarioField.setFont(new Font("Dialog", Font.PLAIN, 16));
        usuarioField.setHorizontalAlignment(JTextField.CENTER);
        loginPanel.add(usuarioField, gbc);

        // Senha
        gbc.gridy = 3;
        JLabel senhaLabel = new JLabel("SENHA", SwingConstants.CENTER);
        senhaLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        senhaLabel.setForeground(Color.WHITE);
        senhaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(senhaLabel, gbc);

        gbc.gridy = 4;
        senhaField = new JPasswordField(20);
        senhaField.setFont(new Font("Dialog", Font.PLAIN, 16));
        senhaField.setHorizontalAlignment(JTextField.CENTER);
        senhaField.setDocument(new javax.swing.text.PlainDocument() {{
            putProperty("filterNewlines", Boolean.TRUE);
        }});
        loginPanel.add(senhaField, gbc);

        // Botão Entrar
        gbc.gridy = 5;
        JButton entrarButton = new JButton("ENTRAR");
        entrarButton.setFont(new Font("Dialog", Font.BOLD, 18));
        entrarButton.setFocusPainted(false);
        entrarButton.addActionListener(this::realizarLogin);
        loginPanel.add(entrarButton, gbc);

        // Link Criar Conta
        gbc.gridy = 6;
        JButton criarContaButton = new JButton("CRIAR CONTA");
        estilizarLink(criarContaButton, Color.GREEN);
        loginPanel.add(criarContaButton, gbc);

        // Link Esqueceu Senha
        gbc.gridy = 7;
        JButton esqueceuSenhaButton = new JButton("ESQUECEU A SENHA");
        estilizarLink(esqueceuSenhaButton, Color.RED);
        esqueceuSenhaButton.addActionListener(e -> {
            dispose();
            new EsqueceuSenha().setVisible(true);
        });
        loginPanel.add(esqueceuSenhaButton, gbc);

        // Wrapper alinhado à esquerda com margem vertical ajustada
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 100, 200));
        wrapper.setOpaque(false);
        wrapper.add(loginPanel);
        background.add(wrapper, BorderLayout.CENTER);
    }

    private void estilizarLink(AbstractButton b, Color cor) {
        b.setFont(new Font("Dialog", Font.BOLD, 14));
        b.setForeground(cor);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void realizarLogin(ActionEvent e) {
        String usuario = usuarioField.getText();
        String senha = new String(senhaField.getPassword());

        if (usuario.equalsIgnoreCase("admin") && senha.equals("1234")) {
            dispose();
            new MainMenu(usuario).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}

class RoundedBorder implements Border {
    private final int radius;
    public RoundedBorder(int radius) { this.radius = radius; }
    public Insets getBorderInsets(Component c) { return new Insets(radius, radius, radius, radius); }
    public boolean isBorderOpaque() { return false; }
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
    }
}
