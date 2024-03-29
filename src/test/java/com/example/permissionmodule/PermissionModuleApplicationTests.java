package com.example.permissionmodule;

import com.example.permissionmodule.entity.Employee;
import com.example.permissionmodule.entity.Permission;
import com.example.permissionmodule.mapper.EmployeeMapper;
import com.example.permissionmodule.repository.EmployeeRepository;
import com.example.permissionmodule.repository.PermissionRepository;
import com.example.permissionmodule.repository.PermissionRequestRepository;
import com.example.permissionmodule.service.EmployeeService;
import nonapi.io.github.classgraph.utils.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
class PermissionModuleApplicationTests {

	private EmployeeService employeeService;

	private EmployeeRepository employeeRepository;
	private PermissionRepository permissionRepository;
	private PermissionRequestRepository permissionRequestRepository;
	private EmployeeMapper employeeMapper;

	@BeforeEach
	public void setUp(){
		employeeRepository = Mockito.mock(EmployeeRepository.class);
		permissionRepository = Mockito.mock(PermissionRepository.class);
		permissionRequestRepository = Mockito.mock(PermissionRequestRepository.class);
		employeeMapper = Mockito.mock(EmployeeMapper.class);

		employeeService = new EmployeeService(employeeRepository,permissionRepository,permissionRequestRepository,employeeMapper);
	}

	@Test
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

}
