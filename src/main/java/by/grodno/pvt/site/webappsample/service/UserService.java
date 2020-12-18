package by.grodno.pvt.site.webappsample.service;

import java.util.List;
import java.util.Optional;

import by.grodno.pvt.site.webappsample.domain.User;

public interface UserService {

	List<User> getUsers();

	User getUser(Integer id);

	void addUser(List<User> user);

	void saveUser(User user);

	void deleteUser(Integer number);

	Optional<User> findByEmail(String email);

	void activateUser(Integer id);
}
