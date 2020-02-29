package org.wcci.books;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class CampusController {
	@Resource
	private CampusRepository campusRepo;
	@GetMapping("/campuses")
	public String showAllCampuses(Model model) {
		model.addAttribute("campuses", campusRepo.findAll());
		return "campusesView";
	}

}
