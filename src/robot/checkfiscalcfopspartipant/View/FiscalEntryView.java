package robot.checkfiscalcfopspartipant.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import robot.checkfiscalcfopspartipant.Model.Entiry.FiscalEntry;

public class FiscalEntryView {

    private List<FiscalEntry> irregularFiscalEntries;
    private Map<Integer, List<Integer>> participantsCFOPs;

    public void setIrregularFiscalEntries(List<FiscalEntry> irregularFiscalEntries) {
        this.irregularFiscalEntries = irregularFiscalEntries;
    }

    public void setParticipantsCFOPs(Map<Integer, List<Integer>> participantsCFOPs) {
        this.participantsCFOPs = participantsCFOPs;
    }

    public void createFileWithWarnings(File saveFolder, String referencesToName, String entriesType, Integer enterpriseCode) {
        StringBuilder fileText = new StringBuilder();
        //percorre entradas irregulares
        for (FiscalEntry irregularFiscalEntry : irregularFiscalEntries) {
            if (!fileText.toString().equals("")) {
                fileText.append("\n");
            }

            fileText.append("O CFOP ").append(irregularFiscalEntry.getCfop());
            fileText.append("do lançamento de ").append(entriesType);
            fileText.append(" com chave ").append(irregularFiscalEntry.getKey());
            fileText.append(" do participante ").append(irregularFiscalEntry.getParticipant());
            fileText.append(" não é nenhum dos cadastrados: ").append(getInIntegerList(participantsCFOPs.get(irregularFiscalEntry.getParticipant())));
        }
        
        fileManager.FileManager.save(saveFolder, enterpriseCode + " - CFOPs Errados - " + referencesToName, fileText.toString());
    }

    public static String getInIntegerList(List<Integer> list) {
        StringBuilder listString = new StringBuilder();

        for (Integer integer : list) {
            //Se nao estiver vazio, adiciona separador
            if (!listString.toString().equals("")) {
                listString.append(",");
            }

            listString.append(integer.toString());
        }

        return list.toString();
    }
}
