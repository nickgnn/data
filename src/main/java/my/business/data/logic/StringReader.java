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
            if (Integer.valueOf(String.valueOf(maps.get(i).get("quantity"))) < 100) {
                deals.add(new Deal(
                        String.valueOf(maps.get(i).get("number")),
                        String.valueOf(maps.get(i).get("date")),
                        String.valueOf(maps.get(i).get("time")),
                        String.valueOf(maps.get(i).get("price")),
                        String.valueOf(maps.get(i).get("quantity")),
                        String.valueOf(maps.get(i).get("direction"))
                ));
            }
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
}
