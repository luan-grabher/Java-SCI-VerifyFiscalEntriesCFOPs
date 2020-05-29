package robot.checkfiscalcfopspartipant.Control;

import Entity.Executavel;
import java.io.File;
import robot.checkfiscalcfopspartipant.Model.ConfigurationFileModel;
import robot.checkfiscalcfopspartipant.Model.FiscalEntryModel;
import robot.checkfiscalcfopspartipant.View.UserInputsView;
import sql.Database;

public class Controller {
    //Models and Views
    private UserInputsView inputsView;
    private ConfigurationFileModel configurationFileModel;
    private FiscalEntryModel fiscalEntryModel;    
           
    private Integer enterpriseCode;
    private Integer referenceStart;
    private Integer referenceEnd;
    private File saveFodler;
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
            saveFodler = inputsView.getSaveFolder();
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
    
    public class setDatabaseStatic extends Executavel{

        public setDatabaseStatic() {
            name = "Conectando ao banco de dados";
        }

        @Override
        public void run() {
            Database.setStaticObject(new Database("sci.cfg"));
        }
        
    }
}
