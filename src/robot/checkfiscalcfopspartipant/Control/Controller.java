package robot.checkfiscalcfopspartipant.Control;

import Entity.Executavel;
import java.io.File;
import robot.checkfiscalcfopspartipant.Model.ConfigurationFileModel;
import robot.checkfiscalcfopspartipant.View.UserInputsView;

public class Controller {
    //Models and Views
    private UserInputsView inputsView;
    private ConfigurationFileModel configurationFileModel;
           
    private Integer enterpriseCode;
    private Integer referenceStart;
    private Integer referenceEnd;
    private File saveFodler;
    
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
}
