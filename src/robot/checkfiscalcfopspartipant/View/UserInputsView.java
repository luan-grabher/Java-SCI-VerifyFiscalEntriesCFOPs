package robot.checkfiscalcfopspartipant.View;

import java.io.File;
import javax.swing.JOptionPane;

public class UserInputsView {

    public File getConfigurationFile() {
        JOptionPane.showMessageDialog(null, "Escolha o arquivo de configuração com os participantes e seus CFOPs");
        File file = Selector.Arquivo.selecionar("C:\\", "CSV (;)", "csv");

        if (file == null || !file.exists()) {
            throw new Error("Arquivo de configuração selecionado inválido!");
        }

        return file;
    }

    public File getSaveFolder() {
        JOptionPane.showMessageDialog(null, "Escolha a pasta onde o programa irá salvar o arquivo com os erros encontrados");
        File file = Selector.Pasta.selecionar();

        if (file == null || !file.exists()) {
            throw new Error("Pasta para salvar selecionada inválida!");
        }

        return file;
    }

    private Integer getIntegerValue(String message, String errorMessage) {
        String input = JOptionPane.showInputDialog(message);

        try {
            return Integer.valueOf(input);
        } catch (Exception e) {
            throw new Error(errorMessage);
        }
    }

    public Integer getEnterpriseCode() {
        return getIntegerValue("Insira o número da empresa no sistema:", "Número de empresa inválido!");
    }

    public Integer getFirstReference() {
        return getIntegerValue("Insira a referência de inicio(está inclusa na pesquisa):\nExemplo: 201906 para junho de 2019", "Número de referência inválido!");
    }

    public Integer getLastReference() {
        return getIntegerValue("Insira a referência final(está inclusa na pesquisa):\nExemplo: 201906 para junho de 2019", "Número de referência inválido!");
    }
    
    public String getEntriesType(){
        String[] types = new String[]{"Entrada","Saida","Serviço"};
        
        int inputType = JOptionPane.showOptionDialog(null, "Escolha o tipo de lançamento:", "Escolha o tipo de lançamento", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
        
        return types[inputType];
    }
}
