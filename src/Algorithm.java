import java.util.*;

abstract class Algorithm {
    PriorityQueue<Word> pq;
    HashSet<String> visited;
    String startWord;
    String goalWord;
    Integer nodesVisited;
    
    public abstract void addNextMoves(Word currentWord, HashSet<String> hs);

    public List<Word> evaluate(HashSet<String> hs) {
        while (!pq.isEmpty()) {
            nodesVisited++;
            Word currentWord = pq.remove();
            if (currentWord.getWord().equals(goalWord)) {
                return currentWord.getPath();
            } else {
                visited.add(currentWord.getWord());
                this.addNextMoves(currentWord, hs);
            }
        }
        return null;
    }
}