package com.example.supportPortal.service.imp;

import com.example.supportPortal.model.User;
import com.example.supportPortal.model.UserPricipal;
import com.example.supportPortal.repository.UserRepository;
import com.example.supportPortal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@Qualifier("userDetailService")
public class UserServiceİmpl implements UserService, UserDetailsService {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository;

    @Autowired
    public UserServiceİmpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);
        if(user == null){
            LOGGER.error("User not found by username: " + username);
            throw new UsernameNotFoundException("User not found by username: " + username);
        }else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPricipal userPricipal = new UserPricipal(user);
            LOGGER.info("Returning found user by username: " + username);
            return userPricipal;
        }
    }
}
