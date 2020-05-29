package robot.checkfiscalcfopspartipant.Model;

import fileManager.FileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationFileModel {
    private File file;
    private Map<Integer,List<Integer>> participantCFOPs = new HashMap<>();

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public void setParticipantCFOpsFromFile(){
        //Get text of file
        String fileText = FileManager.getText(file);
        
        //Divide by lines
        String[] fileTextLines = fileText.split("\n");
        
        //Look all lines
        for (String fileTextLine : fileTextLines) {
            //split collumns with numbers and ;
            String[] collumns = fileTextLine.replaceAll("[^0-9;]", "").split(";");
            
            //If exists minimun of two collumns
            if(collumns.length > 2){
                //Define collumns values
                Integer participant = Integer.valueOf(collumns[0]);
                Integer cfop = Integer.valueOf(collumns[1]);
                                
                if(participantCFOPs.containsKey(participant)){
                    //If exists other cfops for this participant
                    participantCFOPs.get(participant).add(cfop);
                }else{
                    //If no exists other cfops for this participant
                    List<Integer> cfops = new ArrayList<>();
                    cfops.add(cfop);
                    participantCFOPs.put(participant, cfops);
                }
            }
        }
    }

    public Map<Integer, List<Integer>> getParticipantCFOPs() {
        return participantCFOPs;
    }        
}
