package my.business.data.logic;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import my.business.data.entities.Deal;
import my.business.data.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class StringReader {
    @Autowired
    DealService dealService;

    public void writeDealsToDB(ArrayList<LinkedTreeMap> maps) {
        ArrayList<Deal> deals = new ArrayList<>();

        for (int i = 0; i < maps.size(); i++) {
            deals.add(new Deal(
                    String.valueOf(maps.get(i).get("number")),
                    String.valueOf(maps.get(i).get("date")),
                    String.valueOf(maps.get(i).get("time")),
                    String.valueOf(maps.get(i).get("price")),
                    String.valueOf(maps.get(i).get("quantity")),
                    String.valueOf(maps.get(i).get("direction"))
            ));
        }


        for (int i = 0; i < deals.size(); i++) {
            dealService.addUser(deals.get(i));
        }
    }

    public ArrayList<LinkedTreeMap> readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

        ArrayList<LinkedTreeMap> deals = readJsonFromFile(reader.readLine());

        reader.close();

        return deals;
    }

    private ArrayList<LinkedTreeMap> readJsonFromFile(String s) {
        ArrayList<LinkedTreeMap> deals = new Gson().fromJson(s, ArrayList.class);

        return deals;
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
