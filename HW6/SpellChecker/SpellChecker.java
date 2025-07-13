package SpellChecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import GTUList.GTUArrayList;
import HashSet.GTUHashSet;

public class SpellChecker {

    public static void main(String[] args) {
        GTUHashSet<String> dictionary = new GTUHashSet<>();
        System.out.println("Dictionary loaded..");

        long dictStart = System.nanoTime();
        try (BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("dictionary.txt cannot be read.");
            return;
        }
        long dictEnd = System.nanoTime();
        System.out.printf("Dictionary loaded %.2f ms'.\n", (dictEnd - dictStart) / 1e6);

        Scanner scanner = new Scanner(System.in);

    
        while (true) {
            System.out.print("Enter the word(exit for exitting): ");
            String input = scanner.nextLine().trim().toLowerCase();

            // 'exit' komutu girildiyse döngüden çık
            if (input.equals("exit")) {
                System.out.println("Exitting...");
                break;
            }
             if (input.matches(".*\\d.*")) {
                System.err.println("Error: Input contains digits. Please enter a valid word.");
                continue;
            }

            long start = System.nanoTime();

            if (dictionary.contains(input)) {
                System.out.println("Correct.");
            } else {
                System.out.println("False. Suggestions:");
                GTUHashSet<String> seen = new GTUHashSet<>();

                // Edit Distance 1 
                GTUArrayList<String> edit1Set = generateEditDistance1(input, dictionary, seen);
                
                for (String ed1 : edit1Set) {
                    if (ed1 == null || ed1.length() < 2) continue;
                    generateEditDistance2(ed1, dictionary, seen); 
                }

               
                StringBuilder sb = new StringBuilder();
                int i = 0;
                for (String suggestion : seen) {
                    sb.append(suggestion);
                    if (++i != seen.size()) sb.append(", ");
                }
                System.out.println(sb);
            }

            long end = System.nanoTime();
            System.out.printf("Total time: %.2f ms\n", (end - start) / 1e6);
            System.out.printf("Collision count: %d\n", dictionary.getCollisionCount()); 
            dictionary.printMemoryUsage(); 
        }

        scanner.close(); 
    }

    private static void generateEditDistance2(String word, GTUHashSet<String> dictionary, GTUHashSet<String> seen) {
        int len = word.length();

        for (int i = 0; i < len; i++) {
            String del = word.substring(0, i) + word.substring(i + 1); // Deletion
            if (!seen.contains(del) && dictionary.contains(del)) { 
                seen.add(del); 
            }
        }

        for (int i = 0; i <= len; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String ins = word.substring(0, i) + c + word.substring(i); // Insertion
                if (!seen.contains(ins) && dictionary.contains(ins)) { 
                    seen.add(ins); 
                }
            }
        }

        for (int i = 0; i < len; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (word.charAt(i) != c) { 
                    String sub = word.substring(0, i) + c + word.substring(i + 1); // Substitution
                    if (!seen.contains(sub) && dictionary.contains(sub)) { 
                        seen.add(sub); 
                    }
                }
            }
        }
    }

    private static GTUArrayList<String> generateEditDistance1(String word, GTUHashSet<String> dictionary, GTUHashSet<String> seen) {
        GTUArrayList<String> variants = new GTUArrayList<>();
        int len = word.length();

        // Deletion 
        for (int i = 0; i < len; i++) {
            String del = word.substring(0, i) + word.substring(i + 1); // Deletion
            variants.add(del); 
            if (dictionary.contains(del)) { 
                seen.add(del); 
            }

            for (char c = 'a'; c <= 'z'; c++) {
                String ins = word.substring(0, i) + c + word.substring(i); // Insertion
                variants.add(ins); 
                if (dictionary.contains(ins)) { 
                    seen.add(ins); 
                }
                if (word.charAt(i) != c) { 
                    String sub = word.substring(0, i) + c + word.substring(i + 1); // Substitution
                    variants.add(sub); 
                    if (dictionary.contains(sub)) { 
                        seen.add(sub); 
                    }
                }
            }
        }

        return variants;
    }
}
