package com.example.electriccomponentsshop.controller;

import com.example.electriccomponentsshop.dto.DistrictDTO;
import com.example.electriccomponentsshop.dto.ProvinceDTO;
import com.example.electriccomponentsshop.dto.WardDTO;
import com.example.electriccomponentsshop.services.DistrictService;
import com.example.electriccomponentsshop.services.ProvinceService;
import com.example.electriccomponentsshop.services.WardService;
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
    public List<WardDTO> getWard(@RequestParam(name ="district") String districtName){
        return wardService.findByDistrictName(districtName);
    }

    @GetMapping("/get-district")
    @ResponseBody
    public List<DistrictDTO> getDistrictByProvince(@RequestParam(name ="pCode") String pCode){
        return districtService.findByProvinceId(pCode);
    }

    @GetMapping("/get-ward")
    @ResponseBody
    public List<WardDTO> getWardByDistrict(@RequestParam(name ="dCode") String dCode){
        return wardService.findByDistrictId(dCode);
    }
}
