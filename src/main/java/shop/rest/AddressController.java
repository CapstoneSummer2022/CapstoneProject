package shop.rest;

import shop.db.dto.DistrictDTO;
import shop.db.dto.WardDTO;
import shop.services.DistrictService;
import shop.services.WardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {
    final DistrictService districtService;
    final WardService wardService;
    @GetMapping("/district")
    @ResponseBody
    public List getDistrict(@RequestParam(name ="province")String provinceName){
        List list = new ArrayList<>();
        List<DistrictDTO> li = districtService.findByProvinceName(provinceName);
        List<WardDTO> wardDTOList = wardService.findByDistrictName(li.get(0).getName());
        list.add(li);
        list.add(wardDTOList);
        return list;
    }
    @GetMapping("/ward")
    @ResponseBody
    public List<WardDTO> getWard(@RequestParam(name ="district")String districtName){
        return wardService.findByDistrictName(districtName);
    }
}
