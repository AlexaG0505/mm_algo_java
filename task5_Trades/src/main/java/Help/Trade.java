package Help;

public class Trade {
    public Integer trade_time;
    public String sec_board, sec_code;
    public Double price, value;

    public Trade(String line) {
        String[] tokens = line.split("\t");
        trade_time = Integer.parseInt(tokens[1]);
        sec_board = tokens[2];
        sec_code = tokens[3];
        price = Double.parseDouble(tokens[4]);
        value = Double.parseDouble(tokens[8]);
    }
    public static Trade fromString(String line) {
        return new Trade(line);
    }
    public String getSecCode () {
        return sec_code;
    }
    public Double getValue () {
        return value;
    }
}