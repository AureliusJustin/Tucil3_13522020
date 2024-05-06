import java.util.*;

class Greedy extends Algorithm{
    Greedy(String startWord, String goalWord) {
        this.pq = new PriorityQueue<>(new WordComparator());
        pq.add(new Word(startWord, 0, null));
        this.visited = new HashSet<>();
        this.startWord = startWord;
        this.goalWord = goalWord;
        this.nodesVisited = 0;
    }
    
    public void addNextMoves(Word currentWord, HashSet<String> hs) {
        List<String> nextWords = currentWord.getNextWords(hs);
        for (String word : nextWords) {
            if (!visited.contains(word)) {
                Word newWord = new Word(word, 0, currentWord);
                newWord.setValue(newWord.distanceToGoal(goalWord));
                pq.add(newWord);
            }
        }
    }
}