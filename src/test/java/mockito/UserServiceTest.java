package mockito;

import testing.mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Enables Mockito annotations
public class UserServiceTest {

    @Mock
    private UserRepository userRepository; // This will be a mock

    @InjectMocks
    private UserService userService; // The mock is injected here

    @Test
    void testGetUserGreetingManualInjection() {
        UserRepository mockRepo = Mockito.mock(UserRepository.class);
        Mockito.when(mockRepo.findNameById(1)).thenReturn("Alice");

        UserService service = new UserService(mockRepo);  // manual injection
        String greeting = service.getUserGreeting(1);

        assertEquals("Hello, Alice", greeting);
        Mockito.verify(mockRepo).findNameById(1);
    }

    @Test
    void testGetUserGreeting() {
        when(userRepository.findNameById(1)).thenReturn("Alice");
        String greeting = userService.getUserGreeting(1);

        assertEquals("Hello, Alice", greeting);

        // Verify interaction with mock
        verify(userRepository).findNameById(1);
    }


}