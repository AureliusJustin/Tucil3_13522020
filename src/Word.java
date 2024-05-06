import java.util.*;

class Word {
    private String word;
    private Integer value;
    private Word pred;

    // Constructor
    Word(String word, int value, Word pred) {
        this.word = new String(word);
        this.value = value;
        this.pred = pred;
    }

    public String getWord() {
        return word;
    }

    public int getValue() {
        return value;
    }

    public Word getPred() {
        return pred;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int distanceFromRoot() {
        if (pred == null) {
            return 0;
        } else {
            return pred.distanceFromRoot() + 1;
        }
    }

    public int distanceToGoal(String goal) {
        int len = word.length();
        int distance = 0;
        for (int i = 0; i < len; i++) {
            if (this.word.charAt(i) != goal.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    public List<Word> getPath() {
        List<Word> temp;
        if (pred == null) {
            temp = new ArrayList<>();
        } else {
            temp = pred.getPath();
        }
        temp.add(this);
        return temp;
    }

    public List<String> getNextWords(HashSet<String> hs) {
        List<String> stringList = new ArrayList<>();
        int len = this.word.length();
        for (int i = 0; i < len; i++){
            StringBuilder sb = new StringBuilder(this.word);
            for (Character iterator = 'a'; iterator <= 'z'; iterator++) {
                sb.replace(i, i+1, iterator.toString());
                String currentWord = sb.toString();
                if (hs.contains(currentWord) && !iterator.equals(word.charAt(i))) {
                    stringList.add(currentWord);
                }
            }
        }
        return stringList;
    }

    public void printWord(String goal) {
        int len = word.length();
        for (int i = 0; i < len; i++) {
            Character c = this.word.charAt(i);
            if ( c == goal.charAt(i)) {
                System.out.print("\u001B[32m" + Character.toUpperCase(c) + "\u001B[0m");
            } else {
                System.out.print(Character.toUpperCase(c));
            }
        }
        System.out.println();
    }
}

class WordComparator implements Comparator<Word> {
    public int compare(Word w1, Word w2) {
        if (w1.getValue() < w2.getValue()) {
            return -1;
        } else if (w1.getValue() > w2.getValue()){
            return 1;
        } else {
            return w1.getWord().compareTo(w2.getWord());
        }
    }
}