import java.util.*;

class Main {
    public static void main(String[] args) {
        
        if (args.length != 3) {
            System.out.println("Format salah!");
            System.out.println("Format: java -jar WordLadder.jar <StartWord> <EndWord> <Astar / UCS / GBFS>");
            System.out.println("Contoh: java -jar WordLadder.jar Trap Hope Astar");
            return;
        }
        
        if (args[0].length() != args[1].length()) {
            System.out.println("Panjang kata <StartWord> dan <EndWord> berbeda!");
            return;
        }
        
        String startWord = args[0].toLowerCase();
        String goalWord = args[1].toLowerCase();
        HashSet<String> hs = WordReader.readDictionary("dictionary/dictionary.txt");
        long start, end;
        List<Word> solution;
        Algorithm a;

        // Pilihan Algoritma
        if (args[2].equals("Astar")) { // A*
            System.out.println("\nA* Algorithm\n");
            a = new Astar(startWord, goalWord);
        } else if (args[2].equals("UCS")) { // UCS
            System.out.println("\nUCS Algorithm\n");
            a = new UCS(startWord, goalWord);
        } else if (args[2].equals("GBFS")) { // GBFS
            System.out.println("\nGreedy Best First Search Algorithm\n");
            a = new Greedy(startWord, goalWord);
        } else { // Algoritma tidak valid
            System.out.println("Algoritma tidak valid!");
            System.out.println("Format: java -jar WordLadder.jar <StartWord> <EndWord> <Astar / UCS / GBFS>");
            System.out.println("Contoh: java -jar WordLadder.jar Trap Hope Astar");
            return;
        }

        // Search
        start = System.nanoTime();
        solution = a.evaluate(hs);
        end = System.nanoTime();
        // Print solution
        if (solution != null) {
            for (Word word : solution) {
                word.printWord(goalWord);
            }
            System.out.println("Steps: " + (solution.size()-1));
        } else {
            System.out.println("Tidak ditemukan solusi.");
        }
        System.out.println("Execution Time: " + (end-start)/1000000.0 + " ms");
        System.out.println("Nodes Visited: " + (a.nodesVisited) + "\n");
    }
}