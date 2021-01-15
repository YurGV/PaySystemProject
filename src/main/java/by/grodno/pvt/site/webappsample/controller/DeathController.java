package by.grodno.pvt.site.webappsample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import by.grodno.pvt.site.webappsample.exception.UserDiedException;

@Controller
public class DeathController {

	@GetMapping("/kill/user")
	public String killUser() {
		UserDiedException userDiedException = new UserDiedException();

		userDiedException.setUserId("blue-fingered");

		throw userDiedException;
	}
	@GetMapping("/somethingWith401")
	public String killUser2() {
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
	}
	@GetMapping("/somethingWith500")
	public String killUser3() {
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
