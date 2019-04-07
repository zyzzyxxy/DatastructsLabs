public class SimpleListTest {
    private static void print(String prompt,ListNode l) {
        System.out.println(prompt + ": " + Lists.toString(l));
    }
    
    public static void main(String[] arg) {
        ListNode l1,l2,l3,l4,l5;
        
        // Test toList
        l1 = Lists.toList("XabIdRXA7pX");
        print("l1",l1);
        
        // Test copy
        l2 = Lists.copy(l1);
        print("l2",l2);
        
        // Test remove, addFirst and addLast
        Lists.removeAll(l2,'X');
        Lists.addFirst(Lists.addLast(l2,'P'),'S');
        print("l2",l2);
        print("l1",l1);
        
        // Test contains
        System.out.println(Lists.contains(l1,'X'));
        System.out.println(Lists.contains(l2,'X'));
        
        // Test copyUpperCase
        l3 = Lists.copyUpperCase(l2);
        print("13",l3);
        
        // Test reverse
        l4 = Lists.reverse(l3);
        print("l4",l4);
        
        // Test concat
        Lists.concat(l3,Lists.toList("-i-"));
        Lists.concat(l3,l4);
        print("l3",l3);
        print("l4",l4);
       
        // Test addAll
        l5 = Lists.toList("-palindrom");
        Lists.addAll(l3,l5);
        print("l3",l3);
        print("l5",l5);
    }
}
