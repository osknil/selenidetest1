package ltuselenide;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class LoginCred {
    private String email;
    private String password;

    // Constructor that checks if a file exists, creates a new file if it doesn't,
// and retrieves email and password credentials from a JSON file.
    public LoginCred() {
        // Check if the file exists. If not, create a new file
        File file = new File("C:\\Users\\User\\JetBrainsProjects\\ltu.json");
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists at:" + file.getAbsolutePath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // Read email and password credentials from JSON file
            File jsonFile = new File("C:\\Users\\User\\JetBrainsProjects\\ltu.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            email = jsonNode.get("ltuCredentials").get("email").asText();
            password = jsonNode.get("ltuCredentials").get("password").asText();
            System.out.println("Email: " + email);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
