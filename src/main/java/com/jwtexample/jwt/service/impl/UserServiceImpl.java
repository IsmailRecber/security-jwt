package com.jwtexample.jwt.service.impl;

import com.jwtexample.jwt.Dto.AuthRequest;
import com.jwtexample.jwt.Dto.DtoUser;
import com.jwtexample.jwt.model.User;
import com.jwtexample.jwt.repository.UserRepository;
import com.jwtexample.jwt.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;  // Şifreleme için PasswordEncoder
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Şifreyi encode etmek için

    @Override
    public DtoUser saveUser(AuthRequest request) {
        // AuthRequest kullanarak yeni bir User nesnesi oluştur
        User newUser = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())  // Parolayı şifrele
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .build();

        User savedUser = userRepository.save(newUser);  // Yeni kullanıcıyı kaydet

        // Kaydedilen kullanıcıyı DtoUser objesine kopyala
        DtoUser dtoUser = new DtoUser();
        BeanUtils.copyProperties(savedUser, dtoUser);

        return dtoUser;  // Kaydedilen kullanıcıyı geri döndür
    }

//    public Optional<User> getByUserName(String username){
//        return userRepository.findByUsername(username);
//    }

    @Override
    public List<DtoUser> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<DtoUser> dtoUserList = new ArrayList<>();
        for (User user : userList) {
            DtoUser dtoUser = new DtoUser();
            BeanUtils.copyProperties(user, dtoUser);  // Kullanıcıları DtoUser'e dönüştür
            dtoUserList.add(dtoUser);
        }
        return dtoUserList;
    }

    @Override
    public DtoUser getUserById(Long userId) {
        // İstenilen kullanıcıyı repository'den bulup geri döndür
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        DtoUser dtoUser = new DtoUser();
        BeanUtils.copyProperties(user, dtoUser);
        return dtoUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);  // Kullanıcıyı sil
    }

    @Override
    public DtoUser updateUser(Long userId, User user) {
        // Güncellenen kullanıcıyı repository'ye kaydet
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        BeanUtils.copyProperties(user, existingUser, "id");
        User updatedUser = userRepository.save(existingUser);

        DtoUser dtoUser = new DtoUser();
        BeanUtils.copyProperties(updatedUser, dtoUser);
        return dtoUser;
    }
}
