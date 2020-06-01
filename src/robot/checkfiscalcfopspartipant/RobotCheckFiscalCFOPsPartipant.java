package robot.checkfiscalcfopspartipant;

import Entity.Executavel;
import Executor.Execution;
import java.util.ArrayList;
import java.util.List;
import robot.checkfiscalcfopspartipant.Control.Controller;

public class RobotCheckFiscalCFOPsPartipant {
    private static Controller controller = new Controller();
    
    public static void main(String[] args) {
        execute(false);
    }
    
    public static void execute(boolean isTest){
        List<Executavel> execs = new ArrayList<>();
        if(!isTest){
            execs.add(controller.new setUserInputs());
        }
        execs.add(controller.new setDatabaseStatic());
        execs.add(controller.new setParticipantsCFOPs());
        execs.add(controller.new setIrregularCFOPs());
        execs.add(controller.new createWarningFile());
        
        Execution exec = new Execution("Encontrar lançamentos com CFOPs incorretos");
        exec.setExecutables(execs);
        exec.runExecutables();
        exec.endExecution(false);        
    }
    
}
