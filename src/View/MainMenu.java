package View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private JPanel menuPanel;
    private JPanel contentPanel;
    private JLabel textoLabel;
    private JLabel imagemLabel;
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

    public MainMenu() {
        setTitle("Menu Principal");
        setSize(1920, 1080);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        configurarMenuLateral();
        configurarPainelDeConteudo();
        iniciarAnimacaoMenu();
    }

    private void configurarMenuLateral() {
        menuPanel = new JPanel(null);
        menuPanel.setBackground(Color.decode("#2C2F33"));
        menuPanel.setBounds(0, 0, 250, 1080);

        int y = 40;
        int espacamento = 50;

        adicionarBotaoMenu("Início", y += espacamento, null);
        adicionarBotaoMenu("Conceito", y += espacamento, e -> mostrarConteudo(new ConceptPage()));
        adicionarBotaoMenu("Relatórios", y += espacamento, null);
        adicionarBotaoMenu("Metodologia", y += espacamento, null);
        adicionarBotaoMenu("Sobre Nós", y += espacamento, null);

        JButton calcular = new JButton("Calcular");
        calcular.setBounds(50, y += espacamento + 10, 150, 40);
        calcular.setFont(new Font("Dialog", Font.BOLD, 16));
        calcular.setForeground(Color.decode("#D1A954"));
        calcular.setFocusPainted(false);
        calcular.setContentAreaFilled(false);
        calcular.setBorderPainted(false);
        calcular.setHorizontalAlignment(SwingConstants.CENTER);
        menuPanel.add(calcular);

        imagemLabel = new JLabel();
        imagemLabel.setBounds(25, y += 80, 200, 240);
        imagemLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagemLabel.setVerticalAlignment(SwingConstants.CENTER);
        imagemLabel.setIcon(new ImageIcon(getClass().getResource(imagens[0])));
        menuPanel.add(imagemLabel);

        textoLabel = new JLabel(textos[0], SwingConstants.CENTER);
        textoLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        textoLabel.setForeground(Color.WHITE);
        textoLabel.setBounds(10, y + 250, 230, 100);
        menuPanel.add(textoLabel);

        JButton sair = new JButton("Sair");
        sair.setBounds(50, 920, 150, 40); // Centralizado dentro dos 250px de largura
        sair.setFont(new Font("Dialog", Font.BOLD, 18));
        sair.setForeground(Color.decode("#D1A954"));
        sair.setBackground(Color.decode("#2C2F33"));
        sair.setFocusPainted(false);
        sair.setBorderPainted(false);
        sair.setContentAreaFilled(false);
        sair.setHorizontalAlignment(SwingConstants.CENTER);
        sair.addActionListener(e -> System.exit(0));
        menuPanel.add(sair);

        add(menuPanel);
    }

    private void adicionarBotaoMenu(String texto, int y, ActionListener action) {
        JButton botao = new JButton(texto);
        botao.setBounds(25, y, 200, 40);
        botao.setFont(new Font("Dialog", Font.BOLD, 18));
        estilizarBotao(botao);
        if (action != null) botao.addActionListener(action);
        menuPanel.add(botao);
    }

    private void estilizarBotao(AbstractButton b) {
        b.setForeground(Color.WHITE);
        b.setBackground(Color.decode("#2C2F33"));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setHorizontalAlignment(SwingConstants.LEFT);
    }

    private void configurarPainelDeConteudo() {
        contentPanel = new JPanel(null);
        contentPanel.setBounds(250, 0, 1670, 1080);
        add(contentPanel);
    }

    private void mostrarConteudo(JPanel novoPainel) {
        contentPanel.removeAll();
        novoPainel.setBounds(0, 0, 1670, 1080);
        contentPanel.add(novoPainel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void iniciarAnimacaoMenu() {
        Timer timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = (index + 1) % textos.length;
                textoLabel.setText(textos[index]);
                imagemLabel.setIcon(new ImageIcon(getClass().getResource(imagens[index])));
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}
