package com.payroll.leave;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Payroll Leave Service REST API",
				description = "REST CRUD Operation for Payroll",
				contact = @Contact(
						name = "Vishal Saini",
						email = "vishal.saini@ukg.com",
						url = "http://www.payroll.com"
				)
		)
)
public class LeaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveApplication.class, args);
	}

}
