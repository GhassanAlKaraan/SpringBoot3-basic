package com.ghassan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    //injection of the interface
    private final CustomerRepository customerRepository;
    //use Main constructor
    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); //always
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){};
    //the client sends the below
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());

        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

//    @PutMapping("{customerId}")
//    public void updateCustomer(@PathVariable("CustomerId") Integer id, @RequestBody NewCustomerRequest request)
//    {
//        Customer customer = customerRepository.findById(id);
//        customer.setName(request.name());
//        customer.setEmail(request.email());
//        customer.setAge(request.age());
//
//        customerRepository.save(customer);
//    }


//    @GetMapping("/greet")
//    //The greet method creates a new GreetResponse object and initializes its fields with the provided values. The List.of method creates an immutable list of the given elements.
//    public GreetResponse greet() {
//        return new GreetResponse(
//                "Hello",
//                List.of("java", "Javascript"),
//                new Person("GasGas", 28, 30000)
//        );
//    }
//    record Person(String name, int age, double savings) {}
//    record GreetResponse(String greet, List<String> progLang, Person person) {}

}
