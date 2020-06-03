package robot.checkfiscalcfopspartipant.Control;

import Entity.Executavel;
import java.io.File;
import robot.checkfiscalcfopspartipant.Model.ConfigurationFileModel;
import robot.checkfiscalcfopspartipant.Model.FiscalEntryModel;
import robot.checkfiscalcfopspartipant.View.FiscalEntryView;
import robot.checkfiscalcfopspartipant.View.UserInputsView;
import sql.Database;

public class Controller {
    //Models and Views
    private final UserInputsView inputsView = new UserInputsView();
    private final ConfigurationFileModel configurationFileModel = new ConfigurationFileModel();
    private final FiscalEntryModel fiscalEntryModel = new FiscalEntryModel();
    private final FiscalEntryView fiscalEntryView = new FiscalEntryView();
           
    private Integer enterpriseCode;
    private Integer referenceStart;
    private Integer referenceEnd;
    private File saveFolder;
    private String entriesType;
    
    public class setUserInputs extends Executavel{

        public setUserInputs() {
            name = "Pegando informações do usuário";
        }

        @Override
        public void run() {
            enterpriseCode = inputsView.getEnterpriseCode();
            referenceStart = inputsView.getFirstReference();
            referenceEnd = inputsView.getLastReference();
            configurationFileModel.setFile(inputsView.getConfigurationFile());
            saveFolder = inputsView.getSaveFolder();
            entriesType = inputsView.getEntriesType();
        }        
    }
    
    public class setParticipantsCFOPs extends Executavel{

        public setParticipantsCFOPs() {
            name = "Buscando informações do arquivo de configuração.";
        }

        @Override
        public void run() {
            configurationFileModel.setParticipantCFOpsFromFile();
       }
        
    }
    
    public class setIrregularCFOPs extends Executavel{

        public setIrregularCFOPs() {
            name = "Procurando cfops irregulares";
        }

        @Override
        public void run() {
            fiscalEntryModel.setIrregularCFOPs(configurationFileModel.getParticipantCFOPs(), enterpriseCode, referenceStart, referenceEnd, entriesType);
        }    
    }
    
    public class createWarningFile extends Executavel{

        public createWarningFile() {
            name = "Criando arquivo de erros";
        }

        @Override
        public void run() {
            fiscalEntryView.setIrregularFiscalEntries(fiscalEntryModel.getIrregularFiscalEntries());
            fiscalEntryView.setParticipantsCFOPs(configurationFileModel.getParticipantCFOPs());            
            
            fiscalEntryView.createFileWithWarnings(saveFolder, referenceStart + "_" +  referenceEnd, entriesType, enterpriseCode);            
        }
        
    }
    
    public class setDatabaseStatic extends Executavel{

        public setDatabaseStatic() {
            name = "Conectando ao banco de dados";
        }

        @Override
        public void run() {
            Database.setStaticObject(new Database("sci.cfg"));
            if(!Database.getDatabase().testConnection()){
                throw new Error("Erro ao conectar ao banco de dados!");
            }
        }
        
    }
}
