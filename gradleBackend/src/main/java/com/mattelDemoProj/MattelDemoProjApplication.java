package com.mattelDemoProj;

import com.mattelDemoProj.models.Admin;
import com.mattelDemoProj.models.Items;
import com.mattelDemoProj.models.User;
import com.mattelDemoProj.repo.AdminRepository;
import com.mattelDemoProj.repo.ItemsRepository;
import com.mattelDemoProj.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableJpaRepositories
@SpringBootApplication
public class MattelDemoProjApplication {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final AdminRepository adminRepository;
	private final ItemsRepository itemsRepository;

	@Autowired
	public MattelDemoProjApplication(PasswordEncoder passwordEncoder, UserRepository userRepository, AdminRepository adminRepository, ItemsRepository itemsRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.adminRepository = adminRepository;
		this.itemsRepository = itemsRepository;
	}

	@PostConstruct
	public void initUsers(){
		userRepository.saveAll(Stream.of(
				new User(null, "user1@email.com", passwordEncoder.encode("user1"), "user", "one", "1111111", "11 User Lane", "useing"),
				new User(null, "user2@email.com", passwordEncoder.encode("user2"), "user", "two", "2222222", "22 User Lane", "useing")
		).collect(Collectors.toList()));

		adminRepository.saveAll(Stream.of(
				new Admin(null, "admin1@email.com", passwordEncoder.encode("admin1")),
				new Admin(null, "admin2@email.com", passwordEncoder.encode("admin2"))
		).collect(Collectors.toList()));

		itemsRepository.save(new Items(null, "https://i.imgur.com/xyPtn4m.jpeg"));
	}

	public static void main(String[] args) {
		SpringApplication.run(MattelDemoProjApplication.class, args);
	}

}
