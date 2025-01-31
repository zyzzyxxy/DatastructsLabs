package collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.*;

public class CollectionOps {

    public static <T> void print(Collection<T> l)
    {
        System.out.print("[");
        Iterator<T> iterator = l.iterator();
        while (iterator.hasNext())
        {
            System.out.print(iterator.next().toString());
            if(iterator.hasNext())
                System.out.print(", ");
        }
        System.out.print("]\n");
    }
    public static <T> List<T> reverse(List<T> l)
    {
        for(int i = 0; i <= l.size();i++)
        {
            l.add(i,l.get(l.size()-1));
            l.remove(l.size()-1);
        }
        return l;
    }
    // Put your code for less here ...
    public static <T> boolean less(Collection<T> c1, Collection<T> c2, Comparator<T> comp)
    {
        if(comp.compare(Collections.min(c2,comp),Collections.max(c1,comp))!=1)
            return false;
        return true;
    }



    // Example
    public static <T,R> Collection<R> map(Function<T,R> f,Collection<T> c)
    {
        // Determine the dynamic type of the collection
        Class<? extends Collection> cls = c.getClass();
        try {
            // Create an object of the same dynamic type as c
            Collection<R> result = (Collection<R>)cls.newInstance();
            // type.cast(type.newInstance());
            // Copy the elements and apply op to them
            for ( T x : c )
                result.add(f.apply(x));
            return result;   
        }   
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Put your code for filter here ...
    public static <T> Collection<T> filter(Predicate<T> f,Collection<T> c)
    {
        // Determine the dynamic type of the collection
        Class<? extends Collection> cls = c.getClass();
        try {
            // Create an object of the same dynamic type as c
            Collection<T> result = (Collection<T>)cls.newInstance();
            // type.cast(type.newInstance());
            // Copy the elements and apply op to them
            for ( T x : c )
            {
                if(f.test(x))
                    result.add(x);
            }
            return result;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
