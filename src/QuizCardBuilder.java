import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class QuizCardBuilder {

    JFrame frame;
    private JTextArea question;     //hold the question
    private JTextArea answer;       //hold answer
    private List<QuizCard> cardList;

    private static final File dir;

    static {
        dir = new File("C:\\Users\\micur\\Documents\\QuizCards");
        System.out.println(dir.mkdir());
    }

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
        question = textAreaSupplier.get();
        JScrollPane questionScroller = scrollPaneFunction.apply(question);

        JLabel answerLabel = new JLabel("Answer");
        answer = textAreaSupplier.get();
        JScrollPane answerScroller = scrollPaneFunction.apply(answer);

        JButton nextButton = new JButton("Next Card");
        nextButton.addActionListener(action -> {
            System.out.println("next pressed");
            cardList.add(new QuizCard(question.getText(), answer.getText()));
            clearCard();
        });

        cardList = new ArrayList<>();

        panel.add(questionLabel);
        panel.add(questionScroller);
        panel.add(answerLabel);
        panel.add(answerScroller);
        panel.add(nextButton);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(action -> {
            System.out.println("New pressed");
            cardList.clear();
            clearCard();
        });

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(action -> {
            System.out.println("Pressed");
            cardList.add(new QuizCard(question.getText(), answer.getText()));

            JFileChooser fileSave = new JFileChooser();
            fileSave.setDialogTitle("File to save");
            fileSave.setCurrentDirectory(dir);
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
            clearCard();
        });

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

    private void saveFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (QuizCard card : cardList) {
                writer.write(card.getQuestion() + "," + card.getAnswer() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }
}