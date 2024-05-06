import java.util.*;

class Astar extends Algorithm{
    Astar(String startWord, String goalWord) {
        this.pq = new PriorityQueue<>(new WordComparator());
        pq.add(new Word(startWord, 0, null));
        this.visited = new HashSet<>();
        this.startWord = startWord;
        this.goalWord = goalWord;
        this.nodesVisited = 0;
    }
    
    public void addNextMoves(Word currentWord, HashSet<String> hs) {
        List<String> nextWords = currentWord.getNextWords(hs);
        int nextValue = currentWord.distanceFromRoot() + 1;
        for (String word : nextWords) {
            if (!visited.contains(word)) {
                Word newWord = new Word(word, 0, currentWord);
                newWord.setValue(newWord.distanceToGoal(goalWord) + nextValue);
                pq.add(newWord);
            }
        }
    }
}