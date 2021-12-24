package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller // đánh dấu appcontroller là 1 Controller - tiếp nnhajanc ác thông tin reques từ người dùng cho @sẻvice xử lý 
public class AppController {
	@Autowired // Injex cho service
	private ProductService service;
	
	@RequestMapping("/") // hiển thị khi bắt đầu chạy
	public String viewHomePage(Model model) {
	    List<Product> listProducts = service.listAll(); // gọi tới xử lý logic bên sẻvice 
	    model.addAttribute("listProducts", listProducts);
	     
	    return "index"; // trả về trang index.html giao diện cho người dùng nhìn thấy
	}
	// trang index 
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
	    Product product = new Product();
	    model.addAttribute("product", product);
	     
	    return "new_product";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST) // hàm chạy khi phương thức gọi tới POST
	public String saveProduct(@ModelAttribute("product") Product product) {
	    service.save(product);
	     
	    return "redirect:/";
	}
	// chuyển hướng sang trang edit -> để thực thi hành đồng theo đổi sản phẩm
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_product");
	    Product product = service.get(id);
	    mav.addObject("product", product);
	     
	    return mav;
	}
	// chuyển hướng sang trang delete -> để xoá sản phẩm thêm vào 
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
	    service.delete(id);
	    return "redirect:/";       
	}
	
}
