package my.business.data.logic;

import com.google.gson.Gson;
import my.business.data.entities.Deal;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class StringReader {
    @Autowired
    private Deal deal;

    public ArrayList<Deal> readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

        ArrayList<Deal> arrayListWithDeals = getArrayListWithDeals(reader);

        reader.close();

       return arrayListWithDeals;
    }

    private ArrayList<Deal> readJsonFromFile(String s) {
        return null;
    }

    public void writeJsonToFile(String resultFile, ArrayList<Deal> arrayListWithDeals) throws IOException {
        String json = new Gson().toJson(arrayListWithDeals);

        BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile));
        writer.write(json);

        writer.close();
    }

    private ArrayList<Deal> getArrayListWithDeals(BufferedReader reader) throws IOException {
        ArrayList<Deal> deals = new ArrayList<>();

        while (reader.ready()) {
            deals.add(addStringsToDeal(reader.readLine()));
        }

        return deals;
    }

    private Deal addStringsToDeal(String s) {
        String[] split = s.split(",");

        try {
            if (Long.valueOf(deal.getNumber()) < 26109106377365l) {
                deal = new Deal(
                        split[0],
                        LocalDate.now().getDayOfMonth() - 1 + " " +
                                LocalDate.now().getMonth() + " " +
                                LocalDate.now().getYear(),
                        split[1],
                        split[5],
                        split[7],
                        split[15]
                );
            } else {
                deal = new Deal(
                        split[0],
                        LocalDate.now().getDayOfMonth() + " " +
                                LocalDate.now().getMonth() + " " +
                                LocalDate.now().getYear(),
                        split[1],
                        split[5],
                        split[7],
                        split[15]
                );
            }
        } catch (NullPointerException e) {
            deal = new Deal(
                    split[0],
                    LocalDate.now().getDayOfMonth() - 1 + " " +
                            LocalDate.now().getMonth() + " " +
                            LocalDate.now().getYear(),
                    split[1],
                    split[5],
                    split[7],
                    split[15]
            );
        }

        switch (deal.getDirection()) {
            case "B": deal.setDirection("BUY");break;
            case "S": deal.setDirection("SELL");break;
        }

        return deal;
    }

    private void printDeal(Deal deal) {
        System.out.println("\n=====================================\n");

        System.out.println("number : " + deal.getNumber());
        System.out.println("date : " + deal.getDate());
        System.out.println("time : " + deal.getTime());
        System.out.println("price : " + deal.getPrice());
        System.out.println("quantity : " + deal.getQuantity());
        System.out.println("direction : " + deal.getDirection());

        System.out.println("\n=====================================\n");
    }
}
