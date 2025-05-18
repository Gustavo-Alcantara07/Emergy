package View;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainMenu extends JFrame {
    private JSplitPane splitPane;
    private JPanel menuPanel;
    private BackgroundPanel contentPanel;
    private JLabel imagemLabel;
    private JLabel textoLabel;
    private Timer timer;
    private int index = 0;

    private final String[] imagens = {
            "/View/Images/Odum_Image.png",
            "/View/Images/Odum_Image.png",
            "/View/Images/Odum_Image.png",
            "/View/Images/Odum_Image.png",
            "/View/Images/Odum_Image.png"
    };

    private final String[] textos = {
            "<html><center><i>\"A emergia é a energia que a biosfera investe para produzir seus bens e serviços.\"</i><br>— H.T. Odum, 1996.</center></html>",
            "<html><center><i>\"A emergia permite avaliar a sustentabilidade ao considerar todos os fluxos de energia e matéria.\"</i><br>— Brown & Ulgiati, 2004.</center></html>",
            "<html><center><i>\"A emergia atribui valor a recursos naturais que não possuem preço de mercado.\"</i><br>— Ulgiati, 2001.</center></html>",
            "<html><center><i>\"A contabilidade emergética integra economia e ecologia, oferecendo uma visão holística.\"</i><br>— Brown & Ulgiati, 2004.</center></html>",
            "<html><center><i>\"A emergia é a energia usada direta e indiretamente para produzir um serviço ou produto.\"</i><br>— Ortega, 2005.</center></html>"
    };

    public MainMenu(String usuario) {
        super("Menu Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        initComponents(usuario);
    }

    private void initComponents(String usuario) {
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.decode("#2C2F33"));

        // === Painel do usuário ===
        JPanel userPanel = new JPanel();
        userPanel.setOpaque(false);
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/View/Images/Logo_User.png"));
        Image userImg = rawIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        JLabel userIcon = new JLabel(new ImageIcon(userImg));
        JLabel userName = new JLabel(usuario);
        userName.setFont(new Font("Dialog", Font.BOLD, 16));
        userName.setForeground(Color.WHITE);
        userName.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        userPanel.add(Box.createRigidArea(new Dimension(20, 0))); // alinhamento esquerdo
        userPanel.add(userIcon);
        userPanel.add(userName);

        // === Painel de botões ===
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        String[] itens = {"Início", "Conceito", "Relatórios", "Metodologia", "Sobre Nós", "Calcular"};
        for (String texto : itens) {
            JButton b = new JButton(texto);
            b.setAlignmentX(Component.LEFT_ALIGNMENT);
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
            b.setFont(new Font("Dialog", Font.BOLD, 16));
            b.setForeground(texto.equals("Calcular") ? Color.decode("#D1A954") : Color.WHITE);
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            b.setBorderPainted(false);
            b.setFocusPainted(false);
            b.setHorizontalAlignment(SwingConstants.LEFT);

            if (texto.equals("Conceito")) {
                b.addActionListener(e -> showConceptPage());
            } else if (texto.equals("Metodologia")) {
                b.addActionListener(e -> showMethodologyPage());
            } else if (texto.equals("Calcular")) {
                b.addActionListener(e -> showCalculationPage());
            }

            buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0))); // alinhamento esquerdo
            buttonsPanel.add(b);
            buttonsPanel.add(Box.createVerticalStrut(10));
        }

        // === Agrupar usuário e botões ===
        JPanel agrupado = new JPanel();
        agrupado.setOpaque(false);
        agrupado.setLayout(new BoxLayout(agrupado, BoxLayout.Y_AXIS));
        agrupado.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        agrupado.add(userPanel);
        agrupado.add(Box.createVerticalStrut(40)); // espaçamento entre logo e botões
        agrupado.add(buttonsPanel);

        menuPanel.add(agrupado);

        // === Carrossel ===
        JPanel carousel = new JPanel();
        carousel.setOpaque(false);
        carousel.setLayout(new BoxLayout(carousel, BoxLayout.Y_AXIS));
        carousel.add(Box.createVerticalStrut(150));

        imagemLabel = new JLabel();
        imagemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagemLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        carousel.add(imagemLabel);
        carousel.add(Box.createVerticalStrut(10));

        textoLabel = new JLabel("", SwingConstants.CENTER);
        textoLabel.setForeground(Color.WHITE);
        textoLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        textoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        carousel.add(textoLabel);

        menuPanel.add(carousel);

        // === Botão Sair ===
        JButton sair = new JButton("Sair");
        sair.setFont(new Font("Dialog", Font.BOLD, 18));
        sair.setForeground(Color.decode("#D1A954"));
        sair.setOpaque(false);
        sair.setContentAreaFilled(false);
        sair.setBorderPainted(false);
        sair.setFocusPainted(false);
        sair.setAlignmentX(Component.CENTER_ALIGNMENT);
        sair.addActionListener(e -> System.exit(0));

        JPanel exitHolder = new JPanel();
        exitHolder.setOpaque(false);
        exitHolder.setLayout(new BoxLayout(exitHolder, BoxLayout.Y_AXIS));
        exitHolder.add(Box.createVerticalGlue());
        exitHolder.add(sair);
        exitHolder.add(Box.createVerticalStrut(20));

        menuPanel.add(exitHolder);

        // === Painel de Conteúdo ===
        contentPanel = new BackgroundPanel("/View/Images/Menu_Page.jpg");
        contentPanel.setLayout(new BorderLayout());

        // === SplitPane ===
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuPanel, contentPanel);
        splitPane.setDividerLocation(250);
        splitPane.setDividerSize(1);
        splitPane.setEnabled(false);
        getContentPane().add(splitPane);

        startCarousel();
    }

    private void startCarousel() {
        updateCarousel();
        timer = new Timer(10_000, e -> updateCarousel());
        timer.start();
    }

    private void updateCarousel() {
        URL u = getClass().getResource(imagens[index]);
        if (u != null) {
            Image img = new ImageIcon(u).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imagemLabel.setIcon(new ImageIcon(img));
        } else {
            System.err.println("⚠️ Não achei: " + imagens[index]);
        }
        textoLabel.setText(textos[index]);
        index = (index + 1) % imagens.length;
    }

    private void showConceptPage() {
        ConceptPage painel = new ConceptPage();
        contentPanel.removeAll();
        contentPanel.add(painel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showCalculationPage() {
        CalculationPage painel = new CalculationPage();
        contentPanel.removeAll();
        contentPanel.add(painel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showMethodologyPage() {
        MethodologyPage painel = new MethodologyPage();
        contentPanel.removeAll();
        contentPanel.add(painel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu("Admin").setVisible(true));
    }

    private class BackgroundPanel extends JPanel {
        private Image bg;
        BackgroundPanel(String resourcePath) {
            URL url = getClass().getResource(resourcePath);
            if (url != null) {
                bg = new ImageIcon(url).getImage();
            }
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bg != null) {
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
            }
        }
    }
}
