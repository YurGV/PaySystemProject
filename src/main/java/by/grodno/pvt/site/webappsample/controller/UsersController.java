package by.grodno.pvt.site.webappsample.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import by.grodno.pvt.site.webappsample.domain.User;
import by.grodno.pvt.site.webappsample.dto.Avatar;
import by.grodno.pvt.site.webappsample.dto.UserDTO;
import by.grodno.pvt.site.webappsample.exception.UserNotFoundException;
import by.grodno.pvt.site.webappsample.service.StorageService;
import by.grodno.pvt.site.webappsample.service.UserService;

@Controller
public class UsersController {

	static public final Integer SIZE = 5;

	@Autowired
	private UserService userService;
	@Autowired
	private StorageService imgService;
	@Autowired
	private ConversionService convertionService;

	@GetMapping("/users")
	public String getAllUsers(@RequestParam(required = false, name = "pn") Integer pageNum,
			@RequestParam(required = false, name = "sort") Sort.Direction sortDirection,
			@RequestParam(required = false, name = "fieldName") String sortField, Model model) {
		if (pageNum == null) {
			pageNum = Integer.valueOf(0);
		} else {
			pageNum -= 1;
		}

		Page<User> usersPage = userService.getUsersPage(pageNum, SIZE, sortField, sortDirection);

		List<UserDTO> users = usersPage.get().map(u -> convertionService.convert(u, UserDTO.class))
				.collect(Collectors.toList());

		model.addAttribute("users", users);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", usersPage.getTotalPages());
		model.addAttribute("fieldName", sortField);
		model.addAttribute("sort", sortDirection);

		return "userList";
	}

	@PostMapping("/users/{id}/img")
	public String handleFileUpload(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file)
			throws IOException {
		imgService.store(id, file);
		return "redirect:/users";
	}

	@GetMapping("/users/{id}/img")
	public void getImmage(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
		Avatar file = imgService.getFile(id);

		if (file != null) {
			try (InputStream is = file.getData()) {
				IOUtils.copy(is, response.getOutputStream());
			}
		}
	}

	@GetMapping("/apis/v1/users")
	@ResponseBody
	public List<UserDTO> getAllUsers() {

		UserNotFoundException userNotFoundException = new UserNotFoundException();
		userNotFoundException.setUserId("someUserId");
		throw userNotFoundException;
	}

	@GetMapping("/users/edit/{id}")
	public String editUserForm(@PathVariable Integer id, Model model) {

		model.addAttribute("user", userService.getUser(id));

		return "editUserView";
	}

	@PostMapping("/users/edit/{id}")
	public String editUser(@PathVariable Integer id, @Valid UserDTO userDTO, BindingResult br, Model model) {

		if (br.hasErrors()) {
			model.addAttribute("userDTO", userDTO);
			return "editUserView";
		}

		User user = new User();
		user.setId(id);
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());

		userService.edit(userDTO);

		return "redirect:/users";
	}

	@ExceptionHandler(UserNotFoundException.class)
	public String handleUserNotFoundException(UserNotFoundException ex, Model model) {
		model.addAttribute("userId", ex.getUserId());
		return "errors/userNotFound";
	}

	@GetMapping("/w")
	public String getIndexPage1() {
		return "index1";
	}

	@GetMapping("/q")
	public String getIndexPage2() {
		return "index2";
	}

	@GetMapping("/home")
	public String getIndexPage3() {
		return "home";
	}

	@GetMapping("/contact")
	public String getContactPage() {
		return "ContactUs";
	}


}
