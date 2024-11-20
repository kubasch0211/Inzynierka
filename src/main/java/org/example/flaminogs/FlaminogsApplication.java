package org.example.flaminogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.flaminogs.service.KomentarzService;

@SpringBootApplication
public class FlaminogsApplication implements CommandLineRunner {

    private final KomentarzService komentarzService;


    @Autowired
    public FlaminogsApplication(KomentarzService komentarzService) {
        this.komentarzService = komentarzService;
    }


    public static void main(String[] args) {
        SpringApplication.run(FlaminogsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        komentarzService.printAllKomentarze();
    }

}
