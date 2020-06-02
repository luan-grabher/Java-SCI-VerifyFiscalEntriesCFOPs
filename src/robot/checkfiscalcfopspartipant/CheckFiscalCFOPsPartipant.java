package robot.checkfiscalcfopspartipant;

import Entity.Executavel;
import Executor.Execution;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import robot.checkfiscalcfopspartipant.Control.Controller;

public class CheckFiscalCFOPsPartipant {

    private static final Controller controller = new Controller();

    public static void main(String[] args) {
        execute(false);
    }

    public static void execute(boolean isTest) {
        try {
            List<Executavel> execs = new ArrayList<>();
            if (!isTest) {
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
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado:\n" + e.getMessage());
        }

    }

}
