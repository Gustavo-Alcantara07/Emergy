package View;

import javax.swing.*;
import java.awt.*;

public class RegisterPage extends JFrame {

    private JTextField nomeField;
    private JTextField responsavelField;
    private JTextField cpfCnpjField;
    private JTextField telefoneField;
    private JTextField emailField;

    public RegisterPage() {
        setTitle("Cadastro - Emergia");
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Fundo com imagem
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/View/Images/Login_Page.jpg"));
        JLabel background = new JLabel(bgIcon);
        background.setBounds(0, 0, 1920, 1080);
        background.setLayout(null);

        // Painel principal translúcido
        JPanel registerPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
        registerPanel.setOpaque(false);
        registerPanel.setBounds(100, 160, 600, 600);

        // Título
        JLabel title = new JLabel("CADASTRO", SwingConstants.CENTER);
        title.setBounds(0, 30, 600, 40);
        title.setFont(new Font("Dialog", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        registerPanel.add(title);

        int y = 100;
        int altura = 40;
        int espacamento = 80;

        nomeField = criarCampo("Nome/ Responsável", y, registerPanel);
        y += espacamento;
        cpfCnpjField = criarCampo("CPF/CNPJ", y, registerPanel);
        y += 70;
        telefoneField = criarCampo("Telefone", y, registerPanel);
        y += 70;
        emailField = criarCampo("E-mail", y, registerPanel);
        y += 70;

        JButton cadastrarButton = new JButton("CADASTRAR");
        cadastrarButton.setBounds(210, y + 30, 180, 40);
        cadastrarButton.setFont(new Font("Dialog", Font.BOLD, 18));
        cadastrarButton.setFocusPainted(false);
        registerPanel.add(cadastrarButton);

        background.add(registerPanel);
        setContentPane(background);
    }

    private JTextField criarCampo(String rotulo, int y, JPanel panel) {
        JLabel label = new JLabel(rotulo, SwingConstants.CENTER);
        label.setBounds(70, y, 460, 25);
        label.setFont(new Font("Dialog", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JTextField campo = new JTextField();
        campo.setBounds(70, y + 30, 460, 40);
        campo.setFont(new Font("Dialog", Font.PLAIN, 16));
        panel.add(campo);
        return campo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterPage().setVisible(true));
    }
}
