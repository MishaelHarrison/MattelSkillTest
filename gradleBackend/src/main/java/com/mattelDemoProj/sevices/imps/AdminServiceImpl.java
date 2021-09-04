package com.mattelDemoProj.sevices.imps;

import com.mattelDemoProj.dto.UserDTO;
import com.mattelDemoProj.exceptions.EntityNotFound;
import com.mattelDemoProj.models.Admin;
import com.mattelDemoProj.models.Grants;
import com.mattelDemoProj.models.Request;
import com.mattelDemoProj.models.User;
import com.mattelDemoProj.repo.AdminRepository;
import com.mattelDemoProj.repo.GrantsRepository;
import com.mattelDemoProj.repo.RequestsRepository;
import com.mattelDemoProj.repo.UserRepository;
import com.mattelDemoProj.sevices.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final GrantsRepository grantsRepository;
    private final RequestsRepository requestsRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, UserRepository userRepository, GrantsRepository grantsRepository, RequestsRepository requestsRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.grantsRepository = grantsRepository;
        this.requestsRepository = requestsRepository;
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

    @Transactional
    @Override
    public void grantAccess(String email, String username) {
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFound::new);
        requestsRepository.deleteByBy(user.getId());
        grantsRepository.save(new Grants(user.getId(), adminRepository.findByEmail(username).orElseThrow(EntityNotFound::new).getId()));
    }

    @Override
    public void requestAccess(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFound::new);
        requestsRepository.save(new Request(user.getId()));
    }

}
