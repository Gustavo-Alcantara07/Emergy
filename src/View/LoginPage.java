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
        // Configurações da janela
        super("Login - Emergia");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setLayout(null);

        // 1) Imagem de fundo como painel de conteúdo
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/View/Images/Login_Page.jpg"));
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1920, 1080);
        setContentPane(background);
        background.setLayout(null);

        // 2) Painel de login translúcido
        JPanel loginPanel = new JPanel(null) {
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
        loginPanel.setBounds(100, 200, 500, 500);
        background.add(loginPanel);

        // 3) Título "LOGIN"
        JLabel loginLabel = new JLabel("LOGIN", SwingConstants.CENTER);
        loginLabel.setBounds(0, 30, 500, 40);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 28));
        loginLabel.setForeground(Color.WHITE);
        loginPanel.add(loginLabel);

        // 4) Label e campo "USUÁRIO"
        JLabel usuarioLabel = new JLabel("USUÁRIO", SwingConstants.CENTER);
        usuarioLabel.setBounds(70, 100, 360, 25);
        usuarioLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        usuarioLabel.setForeground(Color.WHITE);
        loginPanel.add(usuarioLabel);

        usuarioField = new JTextField();
        usuarioField.setBounds(70, 130, 360, 40);
        usuarioField.setFont(new Font("Dialog", Font.PLAIN, 16));
        loginPanel.add(usuarioField);

        // 5) Label e campo "SENHA"
        JLabel senhaLabel = new JLabel("SENHA", SwingConstants.CENTER);
        senhaLabel.setBounds(70, 190, 360, 25);
        senhaLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        senhaLabel.setForeground(Color.WHITE);
        loginPanel.add(senhaLabel);

        senhaField = new JPasswordField();
        senhaField.setBounds(70, 220, 360, 40);
        senhaField.setFont(new Font("Dialog", Font.PLAIN, 16));
        loginPanel.add(senhaField);

        // 6) Botão "ENTRAR"
        JButton entrarButton = new JButton("ENTRAR");
        entrarButton.setBounds(180, 290, 140, 40);
        entrarButton.setFont(new Font("Dialog", Font.BOLD, 18));
        entrarButton.setFocusPainted(false);
        entrarButton.addActionListener(this::realizarLogin);
        loginPanel.add(entrarButton);

        // 7) Botões de link sem borda
        JButton criarContaButton = new JButton("CRIAR CONTA");
        criarContaButton.setBounds(180, 380, 140, 25);
        estilizarLink(criarContaButton, Color.GREEN);
        loginPanel.add(criarContaButton);

        JButton esqueceuSenhaButton = new JButton("ESQUECEU A SENHA");
        esqueceuSenhaButton.setBounds(150, 420, 200, 25);
        estilizarLink(esqueceuSenhaButton, Color.RED);
        loginPanel.add(esqueceuSenhaButton);
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
        if (usuario.equals("admin") && senha.equals("1234")) {
            dispose();
            new MainMenu().setVisible(true);
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