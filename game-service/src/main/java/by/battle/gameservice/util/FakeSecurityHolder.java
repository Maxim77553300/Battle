package by.battle.gameservice.util;

import by.battle.gameservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class FakeSecurityHolder {

    private UserRepository userRepository;

    public String getTestUserOneId() {
        return userRepository.findByName("Valera2").get().getId();
    }

    public String getTestUserTwoId() {
        return userRepository.findByName("Valera3").get().getId();
    }

    public String getUserOneName() {
        return "Valera1";
    }

    public String getUserTwoName() {
        return "Valera2";
    }
}
