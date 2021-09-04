package com.mattelDemoProj.sevices.imps;

import com.mattelDemoProj.dto.UserDTO;
import com.mattelDemoProj.exceptions.AccessNotGranted;
import com.mattelDemoProj.exceptions.EntityNotFound;
import com.mattelDemoProj.models.User;
import com.mattelDemoProj.repo.GrantsRepository;
import com.mattelDemoProj.repo.ItemsRepository;
import com.mattelDemoProj.repo.RequestsRepository;
import com.mattelDemoProj.repo.UserRepository;
import com.mattelDemoProj.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RequestsRepository requestsRepository;
    private final GrantsRepository grantsRepository;
    private final ItemsRepository itemsRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RequestsRepository requestsRepository, GrantsRepository grantsRepository, ItemsRepository itemsRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.requestsRepository = requestsRepository;
        this.grantsRepository = grantsRepository;
        this.itemsRepository = itemsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserDTO dataToDto(User data){
        UserDTO ret = new UserDTO();
        ret.setPassword("password intentionally overwritten");
        ret.setBusinessJustification(data.getBusinessJustification());
        ret.setContactNumber(data.getContactNumber());
        ret.setAddress(data.getAddress());
        ret.setEmail(data.getEmail());
        ret.setFName(data.getFName());
        ret.setLName(data.getLName());
        return ret;
    }

    private User newDtoToData(UserDTO dto){
        User ret = new User();
        ret.setBusinessJustification(dto.getBusinessJustification());
        ret.setContactNumber(dto.getContactNumber());
        ret.setPassword(passwordEncoder.encode(dto.getPassword()));
        ret.setAddress(dto.getAddress());
        ret.setEmail(dto.getEmail());
        ret.setFName(dto.getFName());
        ret.setLName(dto.getLName());
        return ret;
    }

    @Override
    public UserDTO getData(String email) {
        return dataToDto(userRepository.findByEmail(email).orElseThrow(EntityNotFound::new));
    }

    @Override
    public List<UserDTO> getRequests() {
        requestsRepository.deleteByExpirationIsLessThan(Timestamp.from(Instant.now()));
        return requestsRepository.findAll().stream().map(x->dataToDto(userRepository.findById(x.getBy()).orElseThrow(EntityNotFound::new))).collect(Collectors.toList());
    }

    @Override
    public UserDTO register(UserDTO newUser) {
        return dataToDto(userRepository.save(newDtoToData(newUser)));
    }

    @Override
    public String access(String email) {
        int id = userRepository.findByEmail(email).orElseThrow(EntityNotFound::new).getId();
        try{grantsRepository.findByTo(id).orElseThrow(AccessNotGranted::new);}
        catch (Exception e) { throw new AccessNotGranted(); }
        return itemsRepository.findAll().stream().findFirst().orElseThrow(EntityNotFound::new).getData();
    }

}
