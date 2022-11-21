package recipe.foodbar.usecase.user_example;

import recipe.foodbar.entities.User;
import recipe.foodbar.usecase.user_example.exception.NotAllowedException;
import recipe.foodbar.usecase.user_example.port.PasswordEncoder;
import recipe.foodbar.usecase.user_example.port.UserRepository;

public final class LoginUser {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginUser(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(final String email, final String password) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new NotAllowedException("Not allowed"));
        var hashedPassword = passwordEncoder.encode(email + password);
        if (!user.getPassword().equals(hashedPassword)) throw new NotAllowedException("Not allowed");
        return user;
    }
}
