package robot.checkfiscalcfopspartipant.View;

import Entity.Warning;
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

    public void createFileWithWarnings(File saveFolder, String referencesToName, Integer enterpriseCode) {
        StringBuilder fileText = new StringBuilder();
        //percorre entradas irregulares
        for (FiscalEntry irregularFiscalEntry : irregularFiscalEntries) {
            if (!fileText.toString().equals("")) {
                fileText.append("\n");
            }

            fileText.append("O CFOP ").append(irregularFiscalEntry.getCfop());
            fileText.append(" do lançamento de ").append(irregularFiscalEntry.getType());
            fileText.append(" com chave ").append(irregularFiscalEntry.getKey());
            fileText.append(" do participante ").append(irregularFiscalEntry.getParticipant());
            fileText.append(" não é nenhum dos cadastrados: ").append(getInIntegerList(participantsCFOPs.get(irregularFiscalEntry.getParticipant())));
        }
        
        String fileName = enterpriseCode + " - CFOPs Errados - " + referencesToName + ".txt";
        
        if(fileManager.FileManager.save(saveFolder, fileName, fileText.toString())){
            throw new Warning("Arquivo '" + fileName +"' salvo em:\n" + saveFolder.getAbsolutePath());
        }else{
            throw new Error("Erro ao salvar arquivo '" + fileName +"' em:\n" + saveFolder.getAbsolutePath());
        }
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

        return listString.toString();
    }
}
