package robot.checkfiscalcfopspartipant.Model;

import java.io.File;
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
    
    
}
