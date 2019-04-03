public class SingleBuffer<T> {

    private final int CAPACITY = 1;
    private T value = null;
    private boolean hasValue;

    public SingleBuffer() {
        //this.o = o;
        hasValue = false;
    }

    public boolean put(T obj) {
        //condition for put
        if (!hasValue) {
            this.value = obj;
            hasValue = true;
            return hasValue;
        }
        //failed
        else {
            return false;
        }
    }

    public T get() {
        if(hasValue)
            {
                hasValue=false;
                return this.value;
            }
        else
            {
                return null;
            }

    }
}
