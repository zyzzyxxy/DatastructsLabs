import java.util.*;
import java.util.function.*;

import collection.*;
import static collection.CollectionOps.*; // Use static methods without the "Collection." prefix

public class Main {

    public static void main(String[] args) {    
         ArrayList<String> names = new ArrayList<String>();

         // Test print for an empty list
         print(names); System.out.println();

         // Test print for a list containing one element
         names.add("a");
         print(names); System.out.println();

         // Test print for a list containing more than one element
         names.add("b");
         names.add("c");
         print(names); System.out.println();

         // Test the return value from reverse
         print(reverse(names));
         System.out.println();
         // Test that reverse mutates its argument
         print(names);
         System.out.println();

         //Test some other lists
         List<String> campusLindholmen = new ArrayList<>();
         campusLindholmen.add("Saga");
         campusLindholmen.add("Svea");
         campusLindholmen.add("Jupiter");
         print(campusLindholmen);
         System.out.println("");
         reverse(campusLindholmen);
         print(campusLindholmen);

         // Assignment 4: Write code to test less here
         class IntegerComperator implements Comparator{
              @Override
              public int compare(Object o1, Object o2) {
                   if((int)o2<(int)o1)
                        return 1;
                   else
                        return -1;
              }
         }
         class StringComperator implements Comparator{
              @Override
              public int compare(Object o1, Object o2) {
                   if(((String)o1).compareTo((String)o2)>0)
                        return 1;
                   else
                        return 0;
              }
         }
         IntegerComperator intcomp = new IntegerComperator();
         StringComperator stringcomp = new StringComperator();
         Integer[] iArr1= {4,2,5,1,3};
         Integer[] iArr2= {8,6,7,9};
         Integer[] iArr3= {97,5,123,18};
         List<Integer> li1 = Arrays.asList(iArr1);
         List<Integer> li2 = Arrays.asList(iArr2);
         List<Integer> li3 = Arrays.asList(iArr3);
         String[] str1 = {"HC2", "ED", "HC3"};
         String[] str2 = {"Saga", "Svea", "Jupiter"};
         List<String> johanneberg = Arrays.asList(str1);
         List<String> lindholmen = Arrays.asList(str2);

         System.out.println(less(li1,li2,intcomp));
         System.out.println(less(li1,li3,intcomp));
         System.out.println(less(johanneberg,lindholmen,stringcomp));


         // Assignment 5: Write code to test map here
         double[] d = {23.4, -19.0, 377.62, 0.0, 18.9, -32.12};
         List<Double> d1 = new ArrayList<>();
         for (double x:d) {d1.add(x); }

         Collection<Integer> d2 = map(new Sign(),d1);
         print(d2);

         // Assignment 5: Write code to test filter here

         List<Integer> l1 = new ArrayList<Integer>();
         l1.add(3);l1.add(-42);l1.add(88);l1.add(19);l1.add(-37);l1.add(0);l1.add(18);


         System.out.println("----- IsEven -----");
         Collection<Integer> l2 = filter(new IsEven(), l1);
         // Lamdbda testing
         l2 = filter(x -> x%2==0, l1);
         //List<Integer>l3 = filter2(new IsEven(), l1);

         print(l2); // [-42,88,0,18]





         ArrayList<Person> pl = new ArrayList<>();
         pl.add(new Person("Nisse","nisse@hipnet.moc","male",23));
         pl.add(new Person("Lisa","lisa@shipnet.sea","female",67));
         pl.add(new Person("Ada","ada@jahuu.vanu","female",49));
         pl.add(new Person("Kal","karl@gotnet.vg","male",78));
         pl.add(new Person("Beda","beda@fishnet.cod","female",102));

         // Assignment 6: Write code using lambdas here
         BiFunction<Integer,Integer,Integer> plus = (x,y) -> x+y;
         System.out.println(plus.apply(1,2));

         Collection<Person> pResult = filter(x -> ((Person)x).getAge()>65&&((Person)x).getGender().equals("female"),pl);
         Collection<String> eMails = map(x -> ((Person)x).getEmail(), pResult);
         print(eMails);



    }




}














