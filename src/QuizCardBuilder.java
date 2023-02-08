import com.sun.deploy.panel.WinUpdatePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class QuizCardBuilder {

    JFrame frame;
    private JTextArea scrollPane;     //hold the question
    private JTextArea answer;       //hold answer
    private List<QuizCard> cardList;

    public static void main(String[] args) {
        QuizCardBuilder quizCardBuilder = new QuizCardBuilder();
        quizCardBuilder.go();
    }

    public void go() {
        frame = new JFrame("Quiz Card Builder");
        JPanel panel = new JPanel();
        Font bigFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);

        Supplier<JTextArea> textAreaSupplier = () -> {
            JTextArea textArea = new JTextArea(6, 20);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setFont(bigFont);
            return textArea;
        };

        Function<JTextArea,JScrollPane> scrollPaneFunction = (textArea) -> {
            JScrollPane scrollerPane = new JScrollPane(textArea);
            scrollerPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollerPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            return scrollerPane;
        };

        JLabel questionLabel = new JLabel("Question");
        JTextArea question = textAreaSupplier.get();
        JScrollPane questionScroller = scrollPaneFunction.apply(question);

        JLabel answerLabel = new JLabel("Answer");
        JTextArea answer = textAreaSupplier.get();
        JScrollPane answerScroller = scrollPaneFunction.apply(answer);

        JButton nextButton = new JButton("Next Card");
        cardList = new ArrayList<>();

        panel.add(questionLabel);
        panel.add(questionScroller);
        panel.add(answerLabel);
        panel.add(answerScroller);
        panel.add(nextButton);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenu("New");
        JMenuItem saveMenuItem = new JMenu("Save");

        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(500, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Build and display GUI
        //Need a listener for NextCard button
        //Need a listener for Save button
        //A listener for New button
    }

    private void saveFile() {
        //Handel the saving
    }
}