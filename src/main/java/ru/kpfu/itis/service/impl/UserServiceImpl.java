package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.change.UserChangeForm;
import ru.kpfu.itis.form.UserRegistrationForm;
import ru.kpfu.itis.message.Mail;
import ru.kpfu.itis.model.Token;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.TokenRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.transform.UserChangeFormTransform;
import ru.kpfu.itis.util.transform.UserRegistrationFormToUserTransformer;

import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private Mail mail;

    @Override
    public User getOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(UserRegistrationForm form) {
        User user = UserRegistrationFormToUserTransformer.transform(form);
        Token tk = new Token();
        tk.setUuid(java.util.UUID.randomUUID().toString());
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date());
        c.add(Calendar.DATE, 3);
        java.util.Date now_plus_5_days = c.getTime();
        tk.setDeleteDate(now_plus_5_days);
        tk.setUser(user);
        userRepository.save(user);
        tokenRepository.save(tk);
        mail.sendMail( "from@no-spam.com", form.getEmail(),
                "Shop",
                "http://localhost:8880/activate?tokenUuid=" + tk.getUuid());
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void changeUser(UserChangeForm form) {
        User user = UserChangeFormTransform.transform(form);
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public boolean activatedUser(String _token) {
        Token token = tokenRepository.findOneByUuid(_token);
        if(token == null){
            return false;
        }
        User user = userRepository.findOne(token.getUser().getId());
        tokenRepository.delete(token);
        user.setis_confirm(true);
        userRepository.save(user);
        return true;
    }
}
