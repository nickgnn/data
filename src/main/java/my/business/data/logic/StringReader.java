package my.business.data.logic;

import my.business.data.entities.Deal;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class StringReader {
    @Autowired
    private Deal deal;

    public void readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

        while (reader.ready()) {
            printDeal(addStringsToDeal(reader.readLine()));

            try {
                Thread.sleep(997);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Deal addStringsToDeal(String s) {
        String[] split = s.split(",");
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
