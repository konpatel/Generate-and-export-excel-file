package kon.patel.export;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ExportApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExportApplication.class, args);
  }

}
