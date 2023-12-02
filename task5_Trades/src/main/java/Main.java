import Help.FileProcess;
import Help.Trade;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filename = "trades.txt";
        var trades = FileProcess.readFile(filename);
        if (trades != null) {
            var increase = trades.subList(0, 10);
            var decrease = trades.subList(trades.size() - 10, trades.size());

            System.out.println("Maximum increase:");
            FileProcess.printData(increase);
            System.out.println("\n\n");

            System.out.println("Maximum decrease:");
            List<List<Trade>> reversedDecrease = new ArrayList<>(decrease);
            Collections.reverse(reversedDecrease);
            FileProcess.printData(reversedDecrease);
        }
    }
}