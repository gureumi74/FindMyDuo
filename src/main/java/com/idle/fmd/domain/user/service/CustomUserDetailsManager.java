package com.idle.fmd.domain.user.service;

import com.idle.fmd.domain.user.entity.CustomUserDetails;
import com.idle.fmd.domain.user.entity.UserEntity;
import com.idle.fmd.domain.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser
                = userRepository.findByAccountId(accountId);
        if (optionalUser.isEmpty())
            throw new UsernameNotFoundException(accountId);

        return CustomUserDetails.fromEntity(optionalUser.get());
    }

    // 해당 accountId를 가진 유저가 존재하는 유저인지 아닌지를 반환하는 메서드
    @Override
    public boolean userExists(String accountId) {
        return userRepository.existsByAccountId(accountId);
    }

    // 전달 받은 UserDetails 객체를 이용해서 DB 에 유저 데이터를 저장하는 메서드
    @Override
    public void createUser(UserDetails user) {
        // 이미 해당 아이디를 가진 유저가 존재하면 예외 발생
        if(userExists(user.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 새로운 엔티티를 생성해서 유저 정보를 DB 에 저장
        CustomUserDetails userInfo = (CustomUserDetails) user;
        UserEntity userEntity = new UserEntity();
        userEntity.setAccountId(userInfo.getUsername());
        userEntity.setEmail(userInfo.getEmail());
        userEntity.setNickname(userInfo.getNickname());
        userEntity.setPassword(userInfo.getPassword());
        userEntity.setCreatedAt(LocalDateTime.now());

        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }
}
