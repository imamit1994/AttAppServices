package attapp.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import attapp.model.Employee;
@Service
public class EmployeeServices {
	ArrayList<Employee> employee=new ArrayList<Employee>();
	public EmployeeServices(){
		Employee emp=new Employee();
		emp.setName("Amit");
		emp.setId("573010");
		emp.setForm_A_compliance("Yes");
		emp.setForm_B_compliance("No");
		emp.setManager("Dixon");
		emp.setProject("AT&T");
		employee.add(emp);
	}
	public ArrayList<Employee> getEmployeeInfo()
	{
		return employee;
	}
}
