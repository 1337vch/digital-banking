package com.enset.ebank.web;

import com.enset.ebank.DTO.CustomerDTO;
import com.enset.ebank.exception.CustomerNotExist;
import com.enset.ebank.services.IBankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerRestController {
    final IBankAccountService bankAccountService;

    @GetMapping()
    public List<CustomerDTO> customers (){
        return  bankAccountService.customersList();
    }
    @GetMapping("/{customerId}")
    public CustomerDTO findCustomer (@PathVariable Long customerId) throws CustomerNotExist {
        return  bankAccountService.getCustomer(customerId);
    }

    @PostMapping()
    public CustomerDTO addCustomer (@RequestBody CustomerDTO customerDTO) {
        return  bankAccountService.saveCustomer(customerDTO);
    }
    @PutMapping("/{customerId}")
    public CustomerDTO updateCustomer (@RequestBody CustomerDTO customerDTO,@PathVariable Long customerId) {

        customerDTO.setId(customerId);
        return  bankAccountService.updateCustomer(customerDTO);
    }


    @DeleteMapping("/{customerId}")
    public void deleteCustomer (@PathVariable Long customerId) throws CustomerNotExist {
        bankAccountService.deleteCustomer(customerId);
    }
}
