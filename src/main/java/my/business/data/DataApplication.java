package my.business.data;

import my.business.data.logic.StringReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@SpringBootApplication
public class DataApplication {
	public static void main(String[] args) throws IOException {
//		SpringApplication.run(DataApplication.class, args);


		File file = new File("C:\\Users\\nick\\Desktop\\res_" + LocalDate.now().getDayOfMonth() + "_" +
				LocalDate.now().getMonth() + "_" +
				LocalDate.now().getYear() + ".txt");

		StringReader stringReader = new StringReader();

		stringReader.writeJsonToFile(file.getAbsolutePath(), stringReader.readFile(args[0]));
	}
}