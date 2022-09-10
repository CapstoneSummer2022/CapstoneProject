package shop.rest.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/admin/reports")
public class ReportController {
    @GetMapping("")
    public String view(){
        return "administrator/report";
    }
}