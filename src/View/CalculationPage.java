package View;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class CalculationPage extends JPanel {
    private JComboBox<String> comboInsumos;
    private JTextField tfQuantidade;
    private JButton btnAdd, btnCalc;
    private JTextArea log;
    private DefaultPieDataset dataset;

    public CalculationPage() {
        setLayout(new BorderLayout());
        setBackground(new Color(209, 169, 84));

        // título
        JLabel lblTitle = new JLabel("Cálculo de Emergia", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        // conteúdo central
        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);

        // formulário de entrada
        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0;
        form.add(new JLabel("Insumo:"), c);

        comboInsumos = new JComboBox<>();
        for (CalculadoraEmergia.Insumo i : CalculadoraEmergia.INSUMOS_PADRAO) {
            comboInsumos.addItem(i.getNome());
        }
        c.gridx = 1; c.weightx = 0.5;
        form.add(comboInsumos, c);

        c.gridx = 2; c.weightx = 0;
        form.add(new JLabel("Quantidade:"), c);

        tfQuantidade = new JTextField();
        c.gridx = 3; c.weightx = 0.5;
        form.add(tfQuantidade, c);

        c.gridy = 1; c.gridx = 1; c.weightx = 0;
        btnAdd = new JButton("Adicionar Insumo");
        form.add(btnAdd, c);
        c.gridx = 2;
        btnCalc = new JButton("Calcular");
        form.add(btnCalc, c);

        center.add(form, BorderLayout.NORTH);

        // painel gráfico + log
        JPanel bottom = new JPanel(new GridLayout(1, 2, 10, 0));
        bottom.setOpaque(false);

        dataset = new DefaultPieDataset();
        JFreeChart pie = ChartFactory.createPieChart(
                "", dataset, true, true, false);
        ChartPanel cp = new ChartPanel(pie);
        cp.setPreferredSize(new Dimension(300, 300));
        cp.setOpaque(false);
        bottom.add(cp);

        log = new JTextArea();
        log.setEditable(false);
        JScrollPane sp = new JScrollPane(log);
        bottom.add(sp);

        center.add(bottom, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        // ações dos botões
        btnAdd.addActionListener(e -> {
            String nome = (String) comboInsumos.getSelectedItem();
            double qtd;
            try {
                qtd = Double.parseDouble(tfQuantidade.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida!");
                return;
            }
            var res = CalculadoraEmergia.calcularEmergia(nome, qtd);
            log.append(res.toString() + "\n");
            dataset.setValue("Renovável", res.getPorcentagemRenovavel());
            dataset.setValue("Não-renovável", res.getPorcentagemNaoRenovavel());
            dataset.setValue("Econômico", res.getPorcentagemEconomico());
        });

        btnCalc.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Gráfico atualizado!");
        });
    }

    // === MAIN PARA EXECUÇÃO INDEPENDENTE ===
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cálculo de Emergia");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // tela cheia
            frame.setContentPane(new CalculationPage());
            frame.setVisible(true);
        });
    }
}