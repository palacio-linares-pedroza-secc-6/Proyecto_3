import java.util.Comparator;
import java.io.Serializable;

public class Comparador implements Comparator<Pair>, Serializable {
    @Override
    public int compare(Pair jug1, Pair jug2) {

        if (jug1.getKey() < jug2.getKey()) {
            return 1;
        }

        else if (jug1.getKey() > jug2.getKey()) {
            return -1;
        } else {
            return 0;
        }
    }

}