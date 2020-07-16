package my.business.data;

import my.business.data.entities.Deal;
import my.business.data.logic.StringReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class DataApplication {
	public static void main(String[] args) throws IOException {
//		SpringApplication.run(DataApplication.class, args);


		File file = new File("C:\\Users\\nick\\Desktop\\res_" + LocalDate.now().getDayOfMonth() + "_" +
				LocalDate.now().getMonth() + "_" +
				LocalDate.now().getYear() + ".txt");
		String absolutePath = file.getAbsolutePath();

		StringReader stringReader = new StringReader();
		ArrayList<Deal> deals = stringReader.readFile(args[0]);

		Deal deal = deals.get(37623);
		Deal deal1 = deals.get(37624);

		stringReader.writeJsonToFile(absolutePath, deals);
	}
}