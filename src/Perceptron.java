import java.util.ArrayList;
import java.util.List;

public class Perceptron {

    public DataUnits dataUnits;
    public ConfusionMatrix confusionMatrix;
    public double learningRate = 0.025;
    public PositiveVal positiveVal = new PositiveVal();
    public NegativeVal negativeVal = new NegativeVal();

    public Perceptron(DataUnits dataUnits) {

        this.dataUnits=dataUnits;


    }
    public void setPosNegVals(){
        this.negativeVal = new NegativeVal();
        this.positiveVal.setName(this.dataUnits.getUnits().get(0).getExpectedName());
        int i=0;
        while(negativeVal.getName() == null){
            if(!(positiveVal.getName().equals(dataUnits.getUnits().get(i).getExpectedName()))) {
                negativeVal.setName(dataUnits.getUnits().get(i).getExpectedName());
            }
            i++;
        }
    }
    public void train(){
        this.confusionMatrix = new ConfusionMatrix();
        for(Unit u : dataUnits.getUnits()){
            double net = 0;
            for(int i=0; i<u.getCoordinates().size() ;i++){
                net += (u.getCoordinates().get(i) * u.getWeights().get(i));
            }

            if(net > 0) u.setActualName(positiveVal.getName());
            else    u.setActualName(negativeVal.getName());

            if (net > 0 && u.getActualName().equals(u.getExpectedName())) {
                confusionMatrix.setTP(confusionMatrix.getTP() + 1);
                //System.out.println("True Positive");
            }
            else if ( net > 0 && !(u.getActualName().equals(u.getExpectedName())) ){
                confusionMatrix.setFP(confusionMatrix.getFP()+1);
                //System.out.println("False Positive");
                List<Double> newWeights = new ArrayList<>();
                for(int i = 0; i < u.getWeights().size(); i++){
                    newWeights.add(u.getWeights().get(i)+((0-1)*learningRate*u.getCoordinates().get(i)));
                }
                for(Unit x : dataUnits.getUnits())
                    x.setWeights(newWeights);

            }
            else if ( net < 0 && u.getActualName().equals(u.getExpectedName())) {
                confusionMatrix.setTN(confusionMatrix.getTN() + 1);
                //System.out.println("True Negative");
            }
            else if ( net < 0 && !(u.getActualName().equals(u.getExpectedName()))) {
                confusionMatrix.setFN(confusionMatrix.getFN()+1);
                //System.out.println("False Negative");
                List<Double> newWeights = new ArrayList<>();
                for(int i = 0; i < u.getWeights().size(); i++){
                    newWeights.add(u.getWeights().get(i)+((1-0)*learningRate*u.getCoordinates().get(i)));
                }
                for(Unit x : dataUnits.getUnits())
                    x.setWeights(newWeights);
            }

        }
    }

    public ConfusionMatrix getConfusionMatrix() {
        return confusionMatrix;
    }

    public void setConfusionMatrix(ConfusionMatrix confusionMatrix) {
        this.confusionMatrix = confusionMatrix;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public DataUnits getDataUnits() {
        return dataUnits;
    }

    public void setDataUnits(DataUnits dataUnits) {
        this.dataUnits = dataUnits;
    }

    public PositiveVal getPositiveVal() {
        return positiveVal;
    }

    public NegativeVal getNegativeVal() {
        return negativeVal;
    }
}
