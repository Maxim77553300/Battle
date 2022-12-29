package by.battle.battleservice.util;

import by.battle.battleservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class FakeSecurityHolder {

    private UserRepository userRepository;

    public String getTestUserOneId() {
        return userRepository.findByName("Valera1").getId();
    }

    public String getTestUserTwoId() {
        return userRepository.findByName("Valera2").getId();
    }

    public String getUserOneName() {
        return "Valera1";
    }

    public String getUserTwoName() {
        return "Valera2";
    }
}
