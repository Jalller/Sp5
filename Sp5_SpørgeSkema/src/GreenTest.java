import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GreenTest {
    public ArrayList<Question> questions = new ArrayList<>();
    private int score;

    public GreenTest() {
        questions = readQuestionData();
    }

    // loops thought the amount of questions in the text file questions.txt
    public void createQuestionnaire() {
        String input = "";
        score = 0;
        // the auto answers for certain products with best of un acceptable score
        int indefinite = 0;
        int accepted = 1;
        int denied = 2;
        int decision;

        String inputName = UI.getUserInput("Indtast venligst navnet på produktet du vil teste: \n");

        for (Question q : questions) {
            input = UI.getUserInput(q.getQuestion() + "\nY/N: ");

            // if yes add 1 to score and change boolean to true
            if (input.equalsIgnoreCase("y")) {
                q.setGreen(true);
                score++;
                // set boolean to false which makes it print GreenAdvice
            } else if (input.equalsIgnoreCase("n")){
                q.setGreen(false);
            }

        }



        // inform of product score  and test which questions should be adviced
        if (score > 1) {
            System.out.println("\nDit produkt opfylder: " + score + " krav ud af 10 og derfor godkendt\n");
            decision = indefinite;
            if (score == 10) decision = accepted;
        } else {
            System.out.println("Dit produkt opfylder " + score +" krav, hvilket er mindre end 2 krav\n");
            decision = denied;
        }

        ProductManager.products.add(new Product(inputName, decision,score));

        System.out.println("Disse ting opfylder dit produkt ikke: \n");

        for (Question q : questions) {
            if (!q.isGreen()) {
                System.out.println(q.getGreenAdvice() + "\n");
            }
        }

    }


    public static ArrayList<Question> readQuestionData() {

        ArrayList<Question> readQuestions = new ArrayList<>();

        File file = new File("questions.txt");
        String[] questionLine;
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // once for each group of words
        if (scan != null) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                // check for blank line
                if (line.isEmpty()) continue;
                questionLine = line.split(":");

                readQuestions.add(new Question(questionLine[0], questionLine[1], false));
            }
        }
        return readQuestions;
    }
}
