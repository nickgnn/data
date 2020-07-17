package my.business.data.controller;

import com.google.gson.internal.LinkedTreeMap;
import my.business.data.logic.StringReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class MainController {
    private StringReader stringReader;

    @Autowired
    public MainController(StringReader stringReader) {
        this.stringReader = stringReader;
    }

    @RequestMapping("/")
    public String index() throws IOException {
        ArrayList<LinkedTreeMap> deals = stringReader.readFile("C:\\Users\\nick\\Desktop\\res_15_JULY_2020.txt");

        stringReader.writeDealsToDB(deals);

        return "All Done!";
    }
}
