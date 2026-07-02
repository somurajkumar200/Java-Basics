package Service;
import Model.User;
import Util.IdGenerator;
import Util.ValidationUtil;
import Exception.UserAlreadyExistsException;
import java.util.*;

public class UserService {
    private HashMap<String, User> users;
    public UserService(HashMap<String, User> users) {
        this.users = users;
    }

    public User registerUser(String name, String mobile) throws Exception {

        if (ValidationUtil.isEmpty(name))
            throw new Exception("Name cannot be empty");

        if (!ValidationUtil.isValidMobile(mobile))
            throw new Exception("Invalid mobile number");

        if (users.containsKey(mobile))
            throw new UserAlreadyExistsException();

        String userId = IdGenerator.userId();
        String upi = name.toLowerCase().split(" ")[0] + "@phonepe";
        User user = new User(userId, name, mobile, upi);
        users.put(mobile, user);
        return user;
    }

    public User getUser(String mobile) throws Exception {
        if (!users.containsKey(mobile))
            throw new Exception("User not found");
        return users.get(mobile);
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public void updateName(String mobile, String newName) throws Exception {
        User user = getUser(mobile);
        user.setName(newName);
    }

    public void updateMobile(String oldMobile, String newMobile) throws Exception {
        if (!ValidationUtil.isValidMobile(newMobile))
            throw new Exception("Invalid new mobile");

        if (users.containsKey(newMobile))
            throw new Exception("Mobile already exists");

        User user = users.remove(oldMobile);
        user.setMobile(newMobile);
        users.put(newMobile, user);
    }
}
