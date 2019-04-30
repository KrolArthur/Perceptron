import java.util.List;

public class Unit {

    public List<Double> coordinates;
    public List<Double> weights;
    public String expectedName;
    public String actualName;

    public Unit(List<Double> coords,List<Double> weights, String expectedName){
        this.coordinates = coords;
        this.expectedName = expectedName;
        this.weights = weights;
    }
    public Unit(){};

    public String getExpectedName() {
        return expectedName;
    }

    public void setExpectedName(String expectedName) {
        this.expectedName = expectedName;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

}
