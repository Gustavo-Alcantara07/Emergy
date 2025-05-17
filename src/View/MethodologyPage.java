package View;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MethodologyPage extends JPanel {

    public MethodologyPage() {
        setLayout(new BorderLayout());
        setBackground(new Color(209, 169, 84)); // fundo dourado

        // === Título ===
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        JLabel titulo = new JLabel("Metodologia", SwingConstants.CENTER);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        titulo.setForeground(new Color(33, 33, 33));

        JSeparator linha = new JSeparator();
        linha.setForeground(new Color(194, 145, 58));
        linha.setMaximumSize(new Dimension(50, 5));
        linha.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(titulo);
        header.add(Box.createVerticalStrut(10));
        header.add(linha);
        header.add(Box.createVerticalStrut(10));

        // === Painel de imagem responsiva ===
        JPanel imagemFaixa = new JPanel() {
            private final Image imagem;

            {
                URL url = getClass().getResource("/View/Images/Farm_Image.jpg");
                if (url != null) {
                    imagem = new ImageIcon(url).getImage();
                } else {
                    System.err.println("⚠ Imagem não encontrada: /View/Images/Farm_Image.jpg");
                    imagem = null;
                }
                setPreferredSize(new Dimension(0, 200));
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagem != null) {
                    g.drawImage(imagem, 0, 0, getWidth(), 200, this);
                }
            }
        };
        imagemFaixa.setOpaque(false);

        // === Texto explicativo ===
        JTextArea metodologiaTexto = new JTextArea(
                "Esta seção apresenta a metodologia adotada para o cálculo de emergia.\n\n" +
                        "Etapas:\n" +
                        "1. Identificação dos insumos utilizados na produção agrícola.\n" +
                        "2. Classificação em renováveis, não renováveis e econômicos.\n" +
                        "3. Aplicação das transformidades (UEV) para obtenção da emergia.\n" +
                        "4. Cálculo da emergia total e proporções por tipo de recurso.\n\n" +
                        "A abordagem considera os princípios da Ecologia de Sistemas e as contribuições de H.T. Odum."
        );
        metodologiaTexto.setFont(new Font("SansSerif", Font.PLAIN, 16));
        metodologiaTexto.setForeground(new Color(33, 33, 33));
        metodologiaTexto.setOpaque(false);
        metodologiaTexto.setEditable(false);
        metodologiaTexto.setLineWrap(true);
        metodologiaTexto.setWrapStyleWord(true);
        metodologiaTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
        metodologiaTexto.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // === Painel principal (centraliza tudo) ===
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setOpaque(false);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));

        painelCentral.add(header);
        painelCentral.add(imagemFaixa);
        painelCentral.add(Box.createVerticalStrut(20));
        painelCentral.add(metodologiaTexto);

        add(painelCentral, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Visualização – Página Metodologia");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setContentPane(new MethodologyPage());
            frame.setVisible(true);
        });
    }
}
