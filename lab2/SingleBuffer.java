public class SingleBuffer<T> {

    private T value = null;

    public SingleBuffer() {
    }

    public boolean put(T obj) {
        //condition for put
        if (value==null) {
            this.value = obj;
            return true;
        }
        //failed
        else {
            return false;
        }
    }

    public T get() {
        if(value!=null)
            {
                T temp = this.value;
                this.value=null;
                return temp;
            }
        else
            {
                return null;
            }

    }
}
