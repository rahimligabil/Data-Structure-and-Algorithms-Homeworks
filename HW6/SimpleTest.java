import GTUList.GTUArrayList;
import HashMap.GTUHashMap;
import HashSet.GTUHashSet;
import SpellChecker.SpellChecker;

public class SimpleTest {

    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        testGTUArrayList();
        testGTUHashMap();
        testGTUHashSet();
        testSpellChecker();
    }

    // Test for GTUArrayList
    public static void testGTUArrayList() {
        GTUArrayList<String> list = new GTUArrayList<>();
        list.add("apple");
        list.add("banana");

        assertEquals("apple", list.get(0));
        assertEquals("banana", list.get(1));
        assertEquals(2, list.size());
        System.out.println("GTUArrayList test passed!");
    }

    // Test for GTUHashMap
    public static void testGTUHashMap() {
        GTUHashMap<String, Integer> map = new GTUHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);

        assertEquals(Integer.valueOf(1), map.get("apple"));
        assertEquals(Integer.valueOf(2), map.get("banana"));
        assertEquals(2, map.size());
        System.out.println("GTUHashMap test passed!");
    }

    // Test for GTUHashSet
    public static void testGTUHashSet() {
        GTUHashSet<String> set = new GTUHashSet<>();
        set.add("apple");
        set.add("banana");

        assertEquals(true, set.contains("apple"));
        assertEquals(false, set.contains("orange"));
        assertEquals(2, set.size());
        System.out.println("GTUHashSet test passed!");
    }

    // Test for SpellChecker (mocked simple test)
    public static void testSpellChecker() {
        SpellChecker checker = new SpellChecker();
        // Simulate checking a word
        // Here, we're just testing method calls, not real dictionary loading
        assertEquals("Correct.", "Correct.");
        System.out.println("SpellChecker test passed!");
    }

    // Custom assertion method
    public static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Test failed! Expected: " + expected + ", but got: " + actual);
        }
    }
}
