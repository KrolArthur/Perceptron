public class ConfusionMatrix {
    int TP=0, TN =0, FP=0, FN=0;

    public ConfusionMatrix(){};

    public String toString(){
        return "TP = " + this.getTP() + " FP = " + this.getFP() +
                "\nFN = " + this.getFN() + " TN = " + this.getTN();
    }
    public double getRealAcc(){
        return (double)((this.TP + this.TN) / (double)(this.TP + this.TN + this.FP + this.FN));
    }

    public double getRecall(){
        return (double)(this.TP/(double)(this.TP + this.FN));
    }

    public double getPrecision(){
        return (double)(this.TP / (double)(this.TP + this.FP));
    }

    public double getFMeasure(){
        return (2*getRecall()*getPrecision())/(getRecall()+getPrecision());
    }

    public int getTP() {
        return TP;
    }

    public void setTP(int TP) {
        this.TP = TP;
    }

    public int getTN() {
        return TN;
    }

    public void setTN(int TN) {
        this.TN = TN;
    }

    public int getFP() {
        return FP;
    }

    public void setFP(int FP) {
        this.FP = FP;
    }

    public int getFN() {
        return FN;
    }

    public void setFN(int FN) {
        this.FN = FN;
    }
}
