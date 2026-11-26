package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dto.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserIO {
    public UserIO() {}

    // Đọc danh sách User từ file
    public List<User> getUsers(String filepath) {
        File file = new File(filepath);
        if (!file.exists() || file.length() == 0) {
            return Collections.emptyList();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (Exception error) {
            error.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Ghi User mới vào file
    public void add(User user, String filepath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        File file = new File(filepath);
        List<User> users = new ArrayList<>(getUsers(filepath));

        try {
            users.add(user);

            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            mapper.writeValue(file, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Tìm kiếm user theo email
    public User getUserByEmail(String email, String filepath) {
        for (User user : getUsers(filepath)) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }
}