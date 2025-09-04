package testing.mockito;

public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public String getUserGreeting(int id) {
        String name = repository.findNameById(id);
        return "Hello, " + name;
    }
}