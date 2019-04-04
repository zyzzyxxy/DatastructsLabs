import java.util.function.Predicate;

public class IsEven implements Predicate<Integer> {

    @Override
    public boolean test(Integer x) {
        return ((int)x)%2==0;
    }
}
