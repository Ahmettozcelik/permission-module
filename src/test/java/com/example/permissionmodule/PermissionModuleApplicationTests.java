package com.example.permissionmodule;

import com.example.permissionmodule.entity.Employee;
import com.example.permissionmodule.mapper.EmployeeMapper;
import com.example.permissionmodule.repository.EmployeeRepository;
import com.example.permissionmodule.repository.PermissionRepository;
import com.example.permissionmodule.repository.PermissionRequestRepository;
import com.example.permissionmodule.repository.PermissionResponseRepository;
import com.example.permissionmodule.service.EmployeeService;
import com.example.permissionmodule.service.PermissionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class PermissionModuleApplicationTests {

	private EmployeeService employeeService;
	private PermissionService permissionService;

	private EmployeeRepository employeeRepository;
	private PermissionRepository permissionRepository;
	private PermissionRequestRepository permissionRequestRepository;
	private PermissionResponseRepository permissionResponseRepository;
	private EmployeeMapper employeeMapper;

	@BeforeEach
	public void setUp(){
		employeeRepository = Mockito.mock(EmployeeRepository.class);
		permissionRepository = Mockito.mock(PermissionRepository.class);
		permissionRequestRepository = Mockito.mock(PermissionRequestRepository.class);
		permissionResponseRepository = Mockito.mock(PermissionResponseRepository.class);
		employeeMapper = Mockito.mock(EmployeeMapper.class);

		employeeService = new EmployeeService(employeeRepository,permissionRepository,permissionRequestRepository,permissionResponseRepository,employeeMapper);
		permissionService = new PermissionService(permissionRepository);
	}

	@Test()
	public void whenEmployeeCreated(){
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setFullName("Ahmet Levs");
		employee.setStartOfWork(LocalDate.of(2015,3,28));
		employee.setLanguage("TR");
		employeeRepository.save(employee);

		Mockito.when(employeeService.findEmployeeById(1L)).thenReturn(Optional.of(employee));
		Employee employee2 = employeeService.findEmployeeById(1L).orElse(null);

		Assertions.assertEquals(employee,employee2);
	}

	/*@Test()
	public void whenPermissionCreated(){
		*//*Employee employee = new Employee();
		employee.setId(1L);
		employee.setFullName("Ahmet Levs");
		employee.setStartOfWork(LocalDate.of(2015,3,28));
		employee.setLanguage("TR");
		employeeService.save(employeeMapper.employeeToDto(employee));

		Mockito.when(employeeService.findEmployeeById(1L)).thenReturn(Optional.of(employee));

		Permission permission = new Permission();
		Mockito.when(permissionService.findPermissionByEmployeeId(employee.getId())).thenReturn(permission);
		//permission = permissionService.findPermissionByEmployeeId(employee.getId());

		Assertions.assertEquals(permission.getEmployeeId(),employee.getId());*//*
	}*/

}
