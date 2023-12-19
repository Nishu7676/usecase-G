package com.example.EStockMarketApplication.Controllers;

import com.example.EStockMarketApplication.DTOs.CompanyResponseDTO;
import com.example.EStockMarketApplication.Exceptions.CompanyNotFound;
import com.example.EStockMarketApplication.Feign.MyfeignClient;
import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1.0/market/company")
public class CompanyController {
    private CompanyService companyService;
   
    @Autowired
   private MyfeignClient fiegnClient;

    @Autowired
    public CompanyController(CompanyService companyService, MyfeignClient fiegnClient) {
        this.companyService = companyService;
        this.fiegnClient=fiegnClient;
    }

    @GetMapping("/info/{companyCode}")
    public ResponseEntity<?> getCompanyInfo(@PathVariable Long companyCode,@RequestHeader ("Authorization") String token) {
        if(fiegnClient.authorize(token)) {
            Optional<CompanyResponseDTO> company = companyService.getCompanyByID(companyCode);
            return ResponseEntity.ok(company);
        }
        else
        {
            return new ResponseEntity<>("User is not Authenticated",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCompanies(@RequestHeader ("Authorization") String token) {
        if(fiegnClient.authorize(token)) {
            List<CompanyResponseDTO> companies = companyService.getAllCompanies();
            return ResponseEntity.ok(companies);
        }
        else
        {
            return new ResponseEntity<>("User is not Authenticated",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> RegisterCompany(@RequestBody Company company,@RequestHeader ("Authorization") String token) {
        if((fiegnClient.authorize(token)) && (fiegnClient.getrole(token).equalsIgnoreCase("admin")))
        {
            Company c = companyService.RegisterCompany(company);
            return ResponseEntity.ok(c);
        }
        else
        {
        return new ResponseEntity<>("Sorry,only Admin can Access",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put/{companyCode}")
    public ResponseEntity<String> updateCompany(@PathVariable long companyCode,@RequestBody Company company,@RequestHeader ("Authorization") String token)
    {
        try
        {
            if((fiegnClient.authorize(token)) && (fiegnClient.getrole(token).equalsIgnoreCase("admin"))) {
                companyService.UpdateCompany(companyCode, company);
                return ResponseEntity.ok("Company Details is Updated Successfully");
            }
            else
            {
                return new ResponseEntity<>("Sorry,only Admin can Access",HttpStatus.BAD_REQUEST);
            }
        }
        catch (CompanyNotFound e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company Not Found with ID:"+companyCode);
        }
    }

    @DeleteMapping("/delete/{companyCode}")
    public ResponseEntity<?> DeleteCompany(@PathVariable long companyCode,@RequestHeader ("Authorization") String token)
    {
        if((fiegnClient.authorize(token)) && (fiegnClient.getrole(token).equalsIgnoreCase("admin"))) {
            return ResponseEntity.ok(companyService.deleteCompany(companyCode));
        }
        else
        {
            return new ResponseEntity<>("Sorry,only Admin can Access",HttpStatus.BAD_REQUEST);
        }
    }
}
