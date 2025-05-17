package View;

public class CalculadoraEmergia {

    // Classe pública para que CalculationPage consiga acessar
    public static class Insumo {
        private final String nome;
        private final double renovavel;
        private final double naoRenovavel;
        private final double economico;
        private final double uev;
        private final String unidade;

        public Insumo(String nome, double renovavel, double naoRenovavel,
                      double economico, double uev, String unidade) {
            this.nome = nome;
            this.renovavel = renovavel;
            this.naoRenovavel = naoRenovavel;
            this.economico = economico;
            this.uev = uev;
            this.unidade = unidade;
        }

        public String getNome() { return nome; }
        public double getRenovavel() { return renovavel; }
        public double getNaoRenovavel() { return naoRenovavel; }
        public double getEconomico() { return economico; }
        public double getUev() { return uev; }
        public String getUnidade() { return unidade; }
    }

    public static final Insumo[] INSUMOS_PADRAO = {
            new Insumo("Soja", 0.4, 0.5, 0.1, 1.6e12, "kg"),
            new Insumo("Milho", 0.5, 0.4, 0.1, 1.2e12, "kg"),
            new Insumo("Trigo", 0.45, 0.45, 0.1, 1.4e12, "kg"),
            new Insumo("Fertilizante_NPK", 0.1, 0.8, 0.1, 6.2e12, "kg"),
            new Insumo("Herbicida", 0.05, 0.85, 0.1, 1.5e13, "kg"),
            new Insumo("Diesel", 0.1, 0.8, 0.1, 6.8e12, "litro"),
            new Insumo("Mao_de_obra", 0.3, 0.2, 0.5, 1.2e11, "hora")
    };

    // CLASSE PÚBLICA
    public static class ResultadoEmergia {
        private final String nomeInsumo;
        private final double quantidade;
        private final String unidade;
        private final double emergiaTotal;
        private final double emergiaRenovavel;
        private final double emergiaNaoRenovavel;
        private final double emergiaEconomico;
        private final double porcentagemRenovavel;
        private final double porcentagemNaoRenovavel;
        private final double porcentagemEconomico;

        public ResultadoEmergia(String nomeInsumo, double quantidade, String unidade,
                                double emergiaTotal, double emergiaRenovavel,
                                double emergiaNaoRenovavel, double emergiaEconomico,
                                double porcentagemRenovavel, double porcentagemNaoRenovavel,
                                double porcentagemEconomico) {
            this.nomeInsumo = nomeInsumo;
            this.quantidade = quantidade;
            this.unidade = unidade;
            this.emergiaTotal = emergiaTotal;
            this.emergiaRenovavel = emergiaRenovavel;
            this.emergiaNaoRenovavel = emergiaNaoRenovavel;
            this.emergiaEconomico = emergiaEconomico;
            this.porcentagemRenovavel = porcentagemRenovavel;
            this.porcentagemNaoRenovavel = porcentagemNaoRenovavel;
            this.porcentagemEconomico = porcentagemEconomico;
        }

        public String getNomeInsumo() { return nomeInsumo; }
        public double getQuantidade() { return quantidade; }
        public String getUnidade() { return unidade; }
        public double getEmergiaTotal() { return emergiaTotal; }
        public double getEmergiaRenovavel() { return emergiaRenovavel; }
        public double getEmergiaNaoRenovavel() { return emergiaNaoRenovavel; }
        public double getEmergiaEconomico() { return emergiaEconomico; }
        public double getPorcentagemRenovavel() { return porcentagemRenovavel; }
        public double getPorcentagemNaoRenovavel() { return porcentagemNaoRenovavel; }
        public double getPorcentagemEconomico() { return porcentagemEconomico; }

        @Override
        public String toString() {
            return String.format(
                    "Resultado para %s: %.2f %s%n" +
                            " • Total: %.2e sej%n" +
                            "   - Renovável: %.2e sej (%.1f%%)%n" +
                            "   - Não-renovável: %.2e sej (%.1f%%)%n" +
                            "   - Econômico: %.2e sej (%.1f%%)%n",
                    nomeInsumo, quantidade, unidade,
                    emergiaTotal,
                    emergiaRenovavel, porcentagemRenovavel,
                    emergiaNaoRenovavel, porcentagemNaoRenovavel,
                    emergiaEconomico, porcentagemEconomico
            );
        }
    }

    public static ResultadoEmergia calcularEmergia(String nome, double quantidade) {
        Insumo insumo = null;
        for (Insumo i : INSUMOS_PADRAO) {
            if (i.getNome().equalsIgnoreCase(nome)) {
                insumo = i;
                break;
            }
        }
        if (insumo == null) {
            throw new IllegalArgumentException("Insumo não encontrado: " + nome);
        }

        double U = insumo.getUev() * quantidade;
        double eRen = insumo.getRenovavel() * U;
        double eNao = insumo.getNaoRenovavel() * U;
        double eEco = insumo.getEconomico() * U;
        double total = eRen + eNao + eEco;

        double pRen = (eRen / total) * 100;
        double pNao = (eNao / total) * 100;
        double pEco = (eEco / total) * 100;

        return new ResultadoEmergia(
                insumo.getNome(), quantidade, insumo.getUnidade(),
                total, eRen, eNao, eEco,
                pRen, pNao, pEco
        );
    }
}
