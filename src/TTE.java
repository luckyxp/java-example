import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;

public class TTE {
    private String usernamePattern = "^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{5,20}$";
    private String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#%+=])[A-Za-z0-9@#%+=]{8,50}$";
    private String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private String phonePattern = "^1[0-9]{10}$";
    private Set<String> registed = new CopyOnWriteArraySet<>();

    public String registerUser(String username, String password, String email, String phone) {

        if(username==null||!Pattern.compile(usernamePattern).matcher(username).matches()){
            return "Invalid username!";
        }
        if(password==null||!Pattern.compile(passwordPattern).matcher(password).matches()){
            return "Invalid password!";
        }
        if(email==null||registed.contains(email)
                ||!Pattern.compile(emailPattern).matcher(email).matches()){
            return "Invalid email!";
        }
        if(phone==null
                ||!Pattern.compile(phonePattern).matcher(phone).matches()){
            return "Invalid phone!";
        }
        registed.add(phone);
        registed.add(email);
        return "Registration Successful";
    }

    public static void main(String[] args) {
        String[] strings = new String[]{};
        System.out.println(new TTE().registerUser(
                "User123",
                "Password@123",
                "user123@example.com",
                "2571234567"
        ));
    }
}