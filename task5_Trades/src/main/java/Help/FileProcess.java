package Help;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.IOException;

public class FileProcess {
    public static List<List<Trade>> readFile (String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            Comparator<List<Trade>> priceCompare = (trade1, trade2) -> {
                Double priceDelta1 = trade1.get(trade1.size() - 1).price - trade1.get(0).price;
                Double priceDelta2 = trade2.get(trade2.size() - 1).price - trade2.get(0).price;
                return priceDelta2.compareTo(priceDelta1);
            };
            Comparator<Trade> dateCompare = Comparator.comparing(trade -> trade.trade_time);

            return stream
                    .skip(1)
                    .map(Trade::fromString)
                    .filter((trade) -> trade.sec_board.equals("TQBR") || trade.sec_board.equals("FQBR"))
                    .collect(Collectors.groupingBy(Trade::getSecCode))
                    .values()
                    .stream()
                    .peek((group) -> group.sort(dateCompare))
                    .sorted(priceCompare)
                    .toList();
        }
        catch (IOException exc) {
            System.out.println ("File error " + filename);
        }
        return null;
    }

    public static void printData (List<List<Trade>> trades) {
        System.out.println(" SECCODE    PRICEDIFFPERC    TOTALQTY    TOTALVOLUME   PRICEDIFF");
        trades.forEach((group) -> {
            Trade first = group.get(0);
            Trade last = group.get(group.size() - 1);
            Double diffPrice = (last.price - first.price);
            Double diffPercent = 100. * diffPrice / first.price;
            Double totalVolume = group.stream()
                    .map(Trade::getValue)
                    .reduce(Double::sum)
                    .get();
          System.out.printf("%8s %15.2f%% %11d %14.2f %11.2f\n", first.sec_code, diffPercent, group.size(), totalVolume, diffPrice);
        });
    }
}