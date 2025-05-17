package View;

import javax.swing.*;
import java.awt.*;

public class ConceptPage extends JPanel {

    public ConceptPage() {
        setLayout(null);
        setBackground(new Color(209, 169, 84)); // Cor de fundo semelhante à da imagem

        JLabel titulo = new JLabel("Conceito", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        titulo.setForeground(new Color(33, 33, 33));
        titulo.setBounds(0, 30, 1670, 50); // Largura total disponível após menu lateral
        add(titulo);

        // Linha abaixo do título
        JSeparator linha = new JSeparator();
        linha.setBounds(830 - 25, 80, 50, 5); // Centralizado sob o título
        linha.setForeground(new Color(194, 145, 58)); // Tom dourado/marrom claro
        linha.setBackground(new Color(194, 145, 58));
        add(linha);

        // Imagem ilustrativa
        ImageIcon imagem = new ImageIcon(getClass().getResource("/View/Images/Farm_Image.jpg"));
        JLabel imagemLabel = new JLabel(imagem);
        imagemLabel.setBounds(0, 100, 1670, 200); // imagem full width
        add(imagemLabel);
    }
}