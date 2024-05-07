import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.*;

public class GUI extends JFrame {
    private JPanel MainPanel;
    private JTextField AlgorithmField;
    private JTextField StartWordField;
    private JLabel GoalWordLabel;
    private JTable resultTable;
    private JButton searchButton;
    private JLabel startWordLabel;
    private JTextField GoalWordField;
    private JTable InfoTable;

    public GUI() {

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Status;
                if (GoalWordField.getText().length() != StartWordField.getText().length()) {
                    Status = "Panjang kata <StartWord> dan <EndWord> berbeda!";
                    Object ColumnInfo[] = {"Waktu Eksekusi", "Nodes Visited", "Steps", "Status"};
                    Object dataInfo[][] = {{"-", "-", "-", Status}};
                    InfoTable.getTableHeader().setVisible(true);
                    InfoTable.setModel(new DefaultTableModel(dataInfo, ColumnInfo));
                } else {
                    String startWord = StartWordField.getText().toLowerCase();
                    String goalWord = GoalWordField.getText().toLowerCase();
                    HashSet<String> hs = WordReader.readDictionary("dictionary/dictionary.txt");
                    long start, end;
                    List<Word> solution;
                    Algorithm a;

                    // Pilihan Algoritma
                    if (AlgorithmField.getText().equals("Astar")) { // A*
                        a = new Astar(startWord, goalWord);
                    } else if (AlgorithmField.getText().equals("UCS")) { // UCS
                        a = new UCS(startWord, goalWord);
                    } else if (AlgorithmField.getText().equals("GBFS")) { // GBFS
                        a = new Greedy(startWord, goalWord);
                    } else { // Algoritma tidak valid
                        Status = "Algoritma Tidak Valid! (UCS/Astar/GBFS)";
                        Object ColumnInfo[] = {"Waktu Eksekusi", "Nodes Visited", "Steps", "Status"};
                        Object dataInfo[][] = {{"-", "-", "-", Status}};
                        InfoTable.getTableHeader().setVisible(true);
                        InfoTable.setModel(new DefaultTableModel(dataInfo, ColumnInfo));
                        return;
                    }

                    // Search
                    start = System.nanoTime();
                    solution = a.evaluate(hs);
                    end = System.nanoTime();
                    List<char[]> tempList = new ArrayList<>();
                    String exec = "" + (end-start)/1000000.0;

                    // Print solution
                    if (solution != null) {
                        for (Word word : solution) {
                            tempList.add(word.getWord().toCharArray());
                        }

                        Object ColumnName[] = new Object[startWord.length()];
                        for (int i = 0; i < startWord.length(); i++) {
                            ColumnName[i] = 'a';
                        }
                        Object data[][] = new Object[tempList.size()][startWord.length()];
                        for (int i = 0; i < tempList.size(); i++) {
                            for (int j = 0; j < startWord.length(); j++) {
                                data[i][j] = Character.toUpperCase(tempList.get(i)[j]);
                            }
                        }
                        resultTable.getTableHeader().setVisible(false);
                        resultTable.setRowHeight(30);
                        resultTable.setModel(new DefaultTableModel(data, ColumnName));
                        Object ColumnInfo[] = {"Waktu Eksekusi", "Nodes Visited", "Steps", "Status"};
                        Object dataInfo[][] = {{"Waktu Eksekusi: " + exec, "Nodes Visited: " + a.nodesVisited, "Steps: " + (solution.size()-1), "DONE"}};
                        InfoTable.getTableHeader().setVisible(true);
                        InfoTable.setModel(new DefaultTableModel(dataInfo, ColumnInfo));
                    } else {
                        Status = "Tidak ditemukan solusi";
                        Object ColumnInfo[] = {"Waktu Eksekusi", "Nodes Visited", "Steps", "Status"};
                        Object dataInfo[][] = {{"Waktu Eksekusi: " + exec, "Nodes Visited: " + a.nodesVisited, "Steps: -", Status}};
                        InfoTable.getTableHeader().setVisible(true);
                        InfoTable.setModel(new DefaultTableModel(dataInfo, ColumnInfo));
                    }

                }
            }
        });
    }

    public static void main(String[] args) {
        GUI h = new GUI();
        h.setContentPane(h.MainPanel);
        h.setTitle("Word Ladder Solver");
        h.setSize(1000, 600);
        h.setVisible(true);
        h. setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
