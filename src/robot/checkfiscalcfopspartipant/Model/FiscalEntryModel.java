package robot.checkfiscalcfopspartipant.Model;

import fileManager.FileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import robot.checkfiscalcfopspartipant.Model.Entiry.FiscalEntry;
import robot.checkfiscalcfopspartipant.View.FiscalEntryView;
import sql.Database;

public class FiscalEntryModel {
    
    private File sqlFileGetParticipantEntriesOffTheList = new File("sql/getParticipantEntriesOnReferenceWithoutAnyCFOPsList.sql");
    private String sqlFileGetParticipantEntriesOffTheListText = FileManager.getText(sqlFileGetParticipantEntriesOffTheList);
    
    private List<FiscalEntry> irregularFiscalEntries = new ArrayList<>();

    public void setIrregularCFOPs(Map<Integer, List<Integer>> participantsCFOPs, Integer enterpriseCode, Integer referenceStart, Integer referenceEnd, String entriesType) {
        Map<String, String> sqlChanges = new HashMap<>();
        sqlChanges.put("enterpriseCode", enterpriseCode.toString());
        sqlChanges.put("referenceStart", referenceStart.toString());
        sqlChanges.put("referenceEnd", referenceEnd.toString());
        sqlChanges.put("entriesType", entriesType.substring(0, 3).toLowerCase());
        
        //Percorre todos os participantes
        for (Map.Entry<Integer, List<Integer>> participantCfops : participantsCFOPs.entrySet()) {
            //grava participante
            Integer participant = participantCfops.getKey();
            //grava cfops do participante
            List<Integer> cfops = participantCfops.getValue();
            
            //coloca participante nas trocas do sql
            sqlChanges.put("participant", participant.toString());
            //coloca cfops na lista de in
            sqlChanges.put("participantCfopsInList", FiscalEntryView.getInIntegerList(cfops));
            
            //pega entradas do banco de dados
            ArrayList<String[]> irregularParticipantEntries = Database.getDatabase().select(sqlFileGetParticipantEntriesOffTheListText, sqlChanges);
            
            for (String[] irregularParticipantEntry : irregularParticipantEntries) {
                FiscalEntry fiscalEntry = new FiscalEntry();
                fiscalEntry.setKey(Integer.valueOf(irregularParticipantEntry[0]));
                fiscalEntry.setParticipant(participant);
                fiscalEntry.setCfop(Integer.valueOf(irregularParticipantEntry[1]));
                irregularFiscalEntries.add(fiscalEntry);
            }
        }
    }

    public List<FiscalEntry> getIrregularFiscalEntries() {
        return irregularFiscalEntries;
    }
       
}
