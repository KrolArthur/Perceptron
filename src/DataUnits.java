import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataUnits {

    public List<Unit> units = new ArrayList<>();
    public DataUnits(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName)));
        String tmpLine = new String();
        List<Double> tmpWeights = new ArrayList<>();
        while ((tmpLine = reader.readLine()) != null) {
            // System.out.println(tmpLine);
                String[] splitted = tmpLine.split(",");
                List<Double> tmpSplit = new ArrayList<>();

                String tmpName = new String();
                if(tmpWeights.isEmpty()) {
                    for (int i = 0; i < splitted.length - 1; i++) {
                        tmpSplit.add(Double.parseDouble(splitted[i]));
                        tmpWeights.add((double) (Math.random()));
                    }
                    tmpWeights.add(Math.random());
                }
                else{
                    for (int i = 0; i < splitted.length - 1; i++) {
                        tmpSplit.add(Double.parseDouble(splitted[i]));
                    }
                }
                tmpName = splitted[splitted.length-1];
                tmpSplit.add((double)-1);
                units.add(new Unit(tmpSplit, tmpWeights, tmpName));
                //System.out.println(tmpSplit + "name = " + tmpName);
            }


    }
    public List<Unit> getUnits() {
        return units;
    }
}
