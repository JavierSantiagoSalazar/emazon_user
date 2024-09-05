package com.pragma.emazon_user.infrastructure.out.jpa.adapter;

import com.pragma.emazon_user.domain.model.User;
import com.pragma.emazon_user.domain.spi.UserPersistencePort;
import com.pragma.emazon_user.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.emazon_user.infrastructure.out.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public void saveUser(User user) {

        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public Boolean checkIfUserExists(String userEmail) {
        return userRepository.findByUserEmail(userEmail).isPresent();
    }

    @Override
    public Boolean checkIfUserExistsByDocument(String userDocument) {
        return userRepository.findByUserDocument(userDocument).isPresent();
    }

    @Override
    public Boolean checkIfUserExistsByPhone(String userPhone) {
        return userRepository.findByUserPhone(userPhone).isPresent();
    }

}
