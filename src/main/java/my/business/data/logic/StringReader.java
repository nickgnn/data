package my.business.data.logic;

import my.business.data.entities.Deal;
import my.business.data.repo.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;

@Component
public class StringReader {
    @Autowired
    DealRepository dealRepository;

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

    private String[] readString(String s) throws IOException {
        String[] split = s.split(",");
        String[] deal = new String[5];

        deal[0] = "number : " + split[0];
        deal[1] = "time : " + split[1];
        deal[2] = "price : " + split[5];
        deal[3] = "quantity : " + split[7];

        switch (split[15]) {
            case "B": deal[4] = "direction : " + split[15] + "UY";break;
            case "S": deal[4] = "direction : " + split[15] + "ELL";break;
        }

        return deal;
    }

    private Deal addStringsToDeal(String s) {
        String[] split = s.split(",");
        Deal deal = new Deal(
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

    private void printDeal(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }

        System.out.println("\n=====================================\n");
    }
}
