package Core;


/**
 * This class encapsulates a timestamp for a row of data (one entry per channel)
 *
 * @author javiergs
 * @version 20190130
 */
public class Data {

    private double time;
    private float agreement;
    private float concentrating;
    private float disagreement;
    private float interested;
    private float thinking;
    private float unsure;


    public Data(double time, float agreement, float concentrating, float disagreement, float interested, float thinking, float unsure) {
        this.time = time;
        this.agreement = agreement;
        this.concentrating = concentrating;
        this.disagreement = disagreement;
        this.interested = interested;
        this.thinking = thinking;
        this.unsure = unsure;

    }

    public void setData(double time1, float agreement1, float concentrating1, float disagreement1, float interested1, float thinking1, float unsure1) {
        time= time1;
        agreement = agreement1;
        concentrating = concentrating1;
        disagreement = disagreement1;
        interested = interested1;
        thinking = thinking1;
        unsure = unsure1;
    }

    @Override
    public String toString()
    {
        return "Data{ " + "time= " + time + ", agreement= " + agreement +
                ", concentrating= " + concentrating + ", disagreement= " + disagreement +
                ", interested= " + interested + ", thinking= " + thinking  + ", unsure= " + unsure  + "}";
    }


    public double getTime() {
        return time;
    }

    public float getAgreement() {
        return agreement;
    }

    public float getConcentrating() {
        return concentrating;
    }

    public float getDisagreement() {
        return disagreement;
    }

    public float getInterested() {
        return interested;
    }

    public float getThinking() {
        return thinking;
    }

    public float getUnsure() {
        return unsure;
    }


}
