/**
 * A collection of utility functions for C style primitive list handling.
 *
 * @version 2019-04-xx
 * @author(s) NN and NN
 */
public class Lists {

    // Create an empty list (a null terminated list head).
    public static ListNode mkEmpty() {
        return toList("");
    }

    // Returns true if l refers to a null terminated list head, false ow.
    public static boolean isEmpty(ListNode l) {
        if (l == null)
            throw new ListsException("Lists: null passed to isEmpty");
        return l.next == null;
    }

    // Two lists are equal if both are empty, or if they have equal lengths
    // and contain pairwise equal elements at the same positions.
    public static boolean equals(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            throw new ListsException("null passed to equals");
        if (isEmpty(l1) && isEmpty(l2))
            return true;
        else if (isEmpty(l1) || isEmpty(l2))
            return false;
        else { // both lists are non-empty
            ListNode p1 = l1.next, p2 = l2.next;
            while (p1 != null && p2 != null) {
                char c1 = p1.element, c2 = p2.element;
                if (p1.element != p2.element)
                    return false;
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1 == null && p2 == null;
        }
    }

    // Se f�rel. OH
    public static ListNode toList(String chars) {
        ListNode head, ptr1;     // head pekar alltid p� listans huvud
        head = new ListNode();   // Listans huvud (inneh�ller ej data)
        head.next = null;
        ptr1 = head;             // ptr pekar p� sista noden

        // Bygg en lista av tecken
        for (int i = 0; i < chars.length(); i++) {
            ptr1.next = new ListNode();          // Addera en ny nod sist
            ptr1 = ptr1.next;                    // Flytta fram till den nya noden
            ptr1.element = chars.charAt(i);      // S�tt in tecknet
            ptr1.next = null;                    // Avsluta listan
        }
        return head;
    }

    // Se f�rel. OH
    public static ListNode copy(ListNode l) {
        if (l == null)
            throw new ListsException("Lists: null passed to copy");
        ListNode head, ptr1, ptr2;
        head = new ListNode();             // Kopian
        head.next = null;
        ptr1 = head;

        ptr2 = l.next;  // f�rsta listelementet i originallistan
        while (ptr2 != null) {
            ptr1.next = new ListNode();    // Ny nod i kopian
            ptr1 = ptr1.next;              // Flytta fram
            ptr1.element = ptr2.element;   // Kopiera tecknet
            ptr1.next = null;              // Avsluta
            ptr2 = ptr2.next;              // Flytta fram i originallistan
        }
        return head;
    }

    // Se f�rel. OH
    public static ListNode removeAll(ListNode l, char c) {
        if (l == null)
            throw new ListsException("Lists: null passed to removeAll");
        ListNode p = l;
        while (p.next != null) {
            ListNode temp = p.next;      // Handtag p� n�sta nod
            if (temp.element == c)     // Skall den tas bort?
                p.next = temp.next;      // L�nka f�rbi
            else
                p = p.next;              // Nej, g� vidare *
        }
        // * p f�r ej flyttas om den efterf�ljande noden togs bort!
        return l;
    }

    // ---------------- Uppgifter ----------------- 

    // Testmetod: JunitListTest.testToString()
    public static String toString(ListNode l) {
        if (l == null)
            throw new ListsException("Lists: null passed to toString");
        String result = "";
        if (l != null) {
            while (l.next != null) {
                l = l.next;
                result += l.element;
            }
        }
        return result;
    }

    // Testmetod: JunitListTest.testContains()
    public static boolean contains(ListNode head, char c) {
        if (head == null)
            throw new ListsException("Lists: null passed to contains");
        if (head == null)
            return false;
        else {
            while (head.next != null) {
                head = head.next;
                if (head.element == c)
                    return true;
            }
        }
        return false;
    }

    // Testmetod: JunitListTest.testCopyUpperCase()
    public static ListNode copyUpperCase(ListNode head) {
        if (head == null)
            throw new ListsException("Lists: null passed to copyupperCase");
        ListNode resultHead = new ListNode();
        ListNode lastAdded = new ListNode();
        resultHead.next = lastAdded;
        while (head.next != null) {
            head = head.next;
            if (Character.isUpperCase(head.element)) {
                ListNode temp = new ListNode();
                temp.element = head.element;
                lastAdded.next = temp;
                lastAdded = temp;

            }
        }
        //First listnode is used as the head since we add in "next"
        return resultHead.next;
    }

    // Testmetod: JunitListTest.testAddFirst()
    public static ListNode addFirst(ListNode l, char c) {
        if (l == null)
            throw new ListsException("Lists: null passed to addFirst");
        ListNode temp = new ListNode();
        if (l != null) {
            temp.next = l.next;
            temp.element = c;
            l.next = temp;
        }
        return l;
    }

    // This is a private utility method.
    private static ListNode getLastNode(ListNode head) {
        if (head == null)
            throw new ListsException("Lists: null passed to getLastNode");
        while (head.next != null)
            head = head.next;
        return head;
    }

    // Testmetod: JunitListTest.testAddLast()
    public static ListNode addLast(ListNode l, char c) {
        if (l == null)
            throw new ListsException("Lists: null passed to addLast");
        ListNode last = getLastNode(l);
        last.next = new ListNode();
        last.next.element = c;
        return l;
    }

    // Testmetod: JunitListTest.testConcat()
    public static ListNode concat(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            throw new ListsException("Lists: null passed to concat");
        ListNode lastl1 = getLastNode(l1);
        if (l2 != null)
            lastl1.next = l2.next;
        l2.next = null;
        return l1;
    }

    // Testmetod: JunitListTest.testAddAll()
    public static ListNode addAll(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            throw new ListsException("Lists: null passed to addAll");
        ListNode ptr = l2;
        while (hasNext(ptr)) {
            ptr = ptr.next;
            addLast(l1, ptr.element);
        }
        return l1;
    }

    // Testmetod: JunitListTest.testReverse()
    public static ListNode reverse(ListNode head) {
        if (head == null)
            throw new ListsException("Lists: null passed to reverse");
        ListNode headCopy = copy(head);      //so we can modify it
        ListNode newHead = new ListNode();
        while (headCopy.next != null) {
            headCopy = headCopy.next;
            addFirst(newHead, headCopy.element);
        }
        return newHead;
    }

    public static boolean hasNext(ListNode l) {
        return l.next != null;
    }
}
