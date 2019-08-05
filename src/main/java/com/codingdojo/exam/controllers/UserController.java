package com.codingdojo.exam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.exam.models.Task;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.services.TaskService;
import com.codingdojo.exam.services.UserService;



@Controller
@RequestMapping("/users")
public class UserController {
	public UserService uS;
	public TaskService tS;
	
	public UserController(UserService uS, TaskService tS) {
		this.uS = uS;
		this.tS= tS;
	}
	
	@GetMapping("")
	public String showIndex(@ModelAttribute("user") User user) {
		return "index";
	}
	
	@PostMapping("")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult res, Model model) {
		if(res.hasErrors()) {
			return "login";
		} else {
			if(!user.getPassword().equals(user.getConfirm())) {
				model.addAttribute("userError", "Passwords dont match");
				return "index";
			} else {
				String pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
				user.setPassword(pw);
				
				User exists= uS.findByEmail(user.getEmail());
				if(exists != null) {
					model.addAttribute("userError", "A user with this email already exists");
					return "index";
				} else {
					uS.create(user);
					return "redirect:/index";
				}
			}
		}
	}
		
	
	@PostMapping("/login")
	public String login( @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(email.length() < 1) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "index";
		}
		if(password.length() < 8) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "index";
		}
		
		User u = uS.findByEmail(email);
		
		if(u == null) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "index";
		} else {
			boolean matches = BCrypt.checkpw(password, u.getPassword());
			
			if(matches) {
				session.setAttribute("user", u.getId());
				return "redirect:/users/dashboard";
				} else {
					model.addAttribute("loginError", "Invalid Credentials");
					model.addAttribute("user", new User());
					return "index";
				}
			}
		}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("user");
		
		if(id == null) {
			return "redirect:/users";
		} else {
			User u = uS.findById(id);
			model.addAttribute("user", u);
			
			model.addAttribute("tasks", tS.findAll());
			
			return "dashboard";
		}
	}
	
	@GetMapping("/add")
    public String addRoute(@ModelAttribute("task") Task task, HttpSession session, Model model) {
		model.addAttribute("users", uS.findAll());
    	return "new";
    }
	
    
	@GetMapping("/tasks/{task_id}")
	public String displayTask(@PathVariable("task_id") Long task_id, @ModelAttribute("task") Task task, Model model, HttpSession session) {
		
		
		Task oneTask = tS.findById(task_id);	
		
		model.addAttribute("oneTask", oneTask);
//		System.out.println(oneTask.id);
		
		
		return "view";
	}
	
	@GetMapping("/edit/{task_id}")
	public String editPage(@PathVariable("task_id") Long task_id, Model model){
		Task task = tS.findById(task_id);
		model.addAttribute("task", task );
		model.addAttribute("users", uS.findAll());
		return "edit";
	}
	
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model, HttpSession session) {

    	
//		System.out.println(task.getAssignee());
//		System.out.println(task.getName());
//		System.out.println(task.getPriority());

    	if(result.hasErrors() )return "new";

    		task.setUser(uS.findById((Long)session.getAttribute("user"))); 
    		
//    		System.out.println(result.getAllErrors());
    		
    		tS.create(task);
    		return "redirect:/users/dashboard";
    	
    }
	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model, HttpSession session) {
		
		if (result.hasErrors()) return "edit";
		
			task.setUser(uS.findById((Long)session.getAttribute("user")));
			tS.update(task);
			return "redirect:/users/dashboard";
		
	}

	@PostMapping ("{id}/destroy")
	public String delete(@PathVariable("id")Long id) {
		tS.destroy(id);
		return "redirect:/users/dashboard";
	}

	
	
	
}