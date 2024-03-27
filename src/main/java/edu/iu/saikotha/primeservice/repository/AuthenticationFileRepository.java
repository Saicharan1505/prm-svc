package edu.iu.saikotha.primeservice.repository;

import edu.iu.saikotha.primeservice.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.nio.file.*;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


import static org.springframework.security.config.http.MatcherType.regex;


@Repository

public class AuthenticationFileRepository implements IAuthenticationRepository {

    private static final Logger LOG =
            (Logger) LoggerFactory.getLogger(AuthenticationFileRepository.class);

    private static final String DATABASE_NAME = "data/customers.txt";
    private static final String NEW_LINE = System.lineSeparator();

    public AuthenticationFileRepository(){
        File file = new File(DATABASE_NAME);
        file.getParentFile().mkdirs();
        try{
            file.createNewFile();

        }
        catch (IOException e){
            LOG.error(e.getMessage());
        }
    }


    public Customer findByUsername(String username) throws IOException {
        // Example implementation
        Path path = Paths.get(DATABASE_NAME);
        List<String> data = Files.readAllLines(path);
        for (String line : data) {
            if (!line.trim().isEmpty()) {
                String[] properties = line.split(",");
                if (properties[0].trim().equalsIgnoreCase(username.trim())) {
                    return new Customer(properties[0].trim(), properties[1].trim());
                }
            }
        }

        return null; // or throw an exception if preferred
    }

    @Override
    public boolean save(Customer customer) throws IOException {
        Customer x = findByUsername(customer.getUsername());
        if(x==null){
        Path path = Paths.get(DATABASE_NAME);
        String data = String.format("%1$s,%2$s",
                customer.getUsername().trim(),
                customer.getPassword().trim());
        data += NEW_LINE;
        Files.write(path,
                data.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
        return true;

        }

        return false;
    }
}

//public AuthenticationFileRepository(){
//    File file = new File(DATABASE_NAME);
//    file.getParentFile().mkdirs();
//    try{
//        file.createNewFile();
//
//    }
//    catch (IOException e){
//        LOG.error(e.getMessage());
//    }
//}
