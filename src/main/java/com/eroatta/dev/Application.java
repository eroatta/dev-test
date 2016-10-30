package com.eroatta.dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eroatta.dev.models.Route;
import com.eroatta.dev.services.RoutesService;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private RoutesService routesService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException("Missing data source file...");
        }

        loadRoutes(args[0]);
    }

    /**
     * Reads the input file and process the list of routes.
     * 
     * @param fileName
     *            The name of the input file.
     * @throws IOException
     */
    private void loadRoutes(String fileName) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));

            // get the number of routes on the file
            String line = reader.readLine();
            int numberOfRoutes = Integer.valueOf(line.trim()).intValue();
            logger.info("Loading " + numberOfRoutes
                    + " routes from the bus routes data file...");

            while (numberOfRoutes > 0) {
                line = reader.readLine().trim();

                if (!line.equals("")) {
                    int[] input = Arrays.stream(line.split(" "))
                            .mapToInt(Integer::parseInt).toArray();

                    Route route = new Route(input[0], Arrays.copyOfRange(input,
                            1, input.length));
                    routesService.addRoute(route);
                }

                numberOfRoutes--;
            }
        } catch (IOException ex) {
            logger.error("Unable to access the provided file...");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
