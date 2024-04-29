import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static ArrayList<Problem> decodeJSON(String fileName) throws IOException, ParseException {
        Object jsonParser = new JSONParser().parse(new FileReader(fileName));

        JSONObject jsonObject = (JSONObject) jsonParser;

        JSONArray jsonArray = (JSONArray) jsonObject.get("problems");

        ArrayList<Problem> problems = new ArrayList<>();

        for (Object o : jsonArray) {
            JSONObject problem = (JSONObject) o;
            String hash = (String) problem.get("hash");
            JSONArray dataArray = (JSONArray) problem.get("data");
            HashSet<String> set = new HashSet<>();
            for (Object data : dataArray) {
                set.add((String) data);
            }
            problems.add(new Problem(hash, set));
        }
        return problems;
    }

    public static void encodeJSON(ArrayList<Problem> problems, String fileName) throws IOException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Problem problem : problems) {
            JSONObject problemJSON = new JSONObject();
            problemJSON.put("hash", problem.getHash());

            JSONArray dataArray = new JSONArray();
            for (Object data : problem.getData()) {
                dataArray.add(data);
            }
            problemJSON.put("data", dataArray);
            jsonArray.add(problemJSON);
        }
        jsonObject.put("problems", jsonArray);
        PrintWriter writer = new PrintWriter(fileName);
        writer.write(jsonObject.toJSONString());

        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter path to first Bugban file: ");
        String input1 = scanner.nextLine();
        System.out.println("Enter path to second Bugban file: ");
        String input2 = scanner.nextLine();
        System.out.println("Enter path to first output file: ");
        String output1 = scanner.nextLine();
        System.out.println("Enter path to second output file: ");
        String output2 = scanner.nextLine();
        System.out.println("Enter path to third output file: ");

        String output3 = scanner.nextLine();
        scanner.close();

        ArrayList<Problem> problems1 = decodeJSON(input1);
        ArrayList<Problem> problems2 = decodeJSON(input2);

        ArrayList<Problem> onlyFirst = new ArrayList<>();
        ArrayList<Problem> onlySecond = new ArrayList<>();
        ArrayList<Problem> both = new ArrayList<>();


        for (Problem problem1 : problems1) {
            for (Problem problem2 : problems2) {
//                checking the hash first, for speed, then making sure by checking the whole data
                if (problem1.getHash().equals(problem2.getHash())) {
                    if (problem1.getData().equals(problem2.getData())) {
                        both.add(problem1);
                    }
                }
                else {
                    onlyFirst.add(problem1);
                }
            }
        }
        for (Problem problem2 : problems2) {
            for (Problem problem1 : problems1) {
                if (!problem2.getHash().equals(problem1.getHash())) {
                    onlySecond.add(problem2);
                }
            }
        }

        encodeJSON(onlyFirst, output1);
        encodeJSON(onlySecond, output2);
        encodeJSON(both, output3);
    }
}