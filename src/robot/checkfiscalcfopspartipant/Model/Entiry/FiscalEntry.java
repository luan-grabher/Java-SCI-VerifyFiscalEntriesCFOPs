package robot.checkfiscalcfopspartipant.Model.Entiry;

public class FiscalEntry {

    private Integer key;
    private Integer cfop;
    private Integer participant;

    public Integer getParticipant() {
        return participant;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getCfop() {
        return cfop;
    }

    public void setCfop(Integer cfop) {
        this.cfop = cfop;
    }

}
