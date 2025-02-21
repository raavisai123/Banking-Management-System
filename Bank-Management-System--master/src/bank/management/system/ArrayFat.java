import java.util.ArrayList;

class ArrayFat {
    public static void main(String args[]) {
        ArrayList<String> addData = new ArrayList<>();
        addData.add("ram");
        addData.add("jam");
        addData.add("king");

        // Define the start and end indices for the substring
        int startIndex = 1;
        int endIndex = 3;

        for (String e : addData) {
            if (e.length() >= endIndex) {
                String substring = e.substring(startIndex, endIndex);
                System.out.println(substring);
            } else {
                System.out.println("String is too short for the given indices: " + e);
            }
        }
    }
}
