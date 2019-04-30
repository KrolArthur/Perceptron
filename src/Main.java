import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Podaj stala uczenia");
		DataUnits data = new DataUnits("train.txt");
		Perceptron perceptron = new Perceptron(data);
		perceptron.setLearningRate(in.nextDouble());
		perceptron.setPosNegVals();
		for (int i = 0; i < 5; i++)
			perceptron.train();
		System.out.println((perceptron.getConfusionMatrix().toString() 
				+ "\nAccu = " + perceptron.getConfusionMatrix().getRealAcc()
				+ "\nReca = " + perceptron.getConfusionMatrix().getRecall()
				+ "\nPrec = " + perceptron.getConfusionMatrix().getPrecision()
				+ "\nFMeas = " + perceptron.getConfusionMatrix().getFMeasure())
				+ "\n");
		////////////////////////////////////////////////////////////////////////////
		List<Double> weights = perceptron.getDataUnits().getUnits().get(0).getWeights();
		
		checkTrainData("test.txt",perceptron, weights);
		
		int input = 0;
		
		
		System.out.println("1 - podaj wektor, 4 -zaoncz dzialanie");
		input = in.nextInt();
		while(input != 4) {
			switch(input) {
			case 1:
				System.out.println("Podaj wektor");
				String newWektor = new String();
				newWektor = in.next();
				String[] split = newWektor.split(",");
				List<Double> testWektor = new ArrayList<>();
				for(int i=0; i<split.length; i++) {
					testWektor.add(Double.parseDouble(split[i]));
				}
				double netVal = 0;
				for(int i=0; i<testWektor.size(); i++) {
					netVal+=testWektor.get(i) * weights.get(i);
				}
				if(netVal > weights.get(weights.size()-1))
					System.out.println(perceptron.getPositiveVal().getName());
				else
					System.out.println(perceptron.getNegativeVal().getName());
				break;
			case 4:
				System.out.println("Koncze dzialanie programu");
				System.exit(0);
				break;
				
			}
			input = in.nextInt();
		}
	}
	
	public static void checkTrainData(String s, Perceptron p, List<Double> weights) throws IOException{
		DataUnits dataUnits = new DataUnits(s);
		ConfusionMatrix confusionMatrix = new ConfusionMatrix();
        for(Unit u : dataUnits.getUnits()){
            double net = 0;
            for(int i=0; i<u.getCoordinates().size() ;i++){
                net += (u.getCoordinates().get(i) * weights.get(i));
            }

            if(net > 0) u.setActualName(p.getPositiveVal().getName());
            else    u.setActualName(p.getNegativeVal().getName());

            if (net > 0 && u.getActualName().equals(u.getExpectedName())) {
                confusionMatrix.setTP(confusionMatrix.getTP() + 1);
                //System.out.println("True Positive");
            }
            else if ( net > 0 && !(u.getActualName().equals(u.getExpectedName())) ){
                confusionMatrix.setFP(confusionMatrix.getFP()+1);
                //System.out.println("False Positive")
            }
            else if ( net < 0 && u.getActualName().equals(u.getExpectedName())) {
                confusionMatrix.setTN(confusionMatrix.getTN() + 1);
                //System.out.println("True Negative");
            }
            else if ( net < 0 && !(u.getActualName().equals(u.getExpectedName()))) {
                confusionMatrix.setFN(confusionMatrix.getFN()+1);
                //System.out.println("False Negative");
            }

        }
        
        System.out.println((confusionMatrix.toString() 
				+ "\nAccu = " + confusionMatrix.getRealAcc()
				+ "\nReca = " + confusionMatrix.getRecall() + "\nPrec = "
				+ confusionMatrix.getPrecision() + "\nFMeas = "
				+ confusionMatrix.getFMeasure()));
        
	}
}
