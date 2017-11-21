package powermock.session5;

import powermock.session4.Employee;
import powermock.session4.EmployeeDao;

public class EmployeeService {
    private static EmployeeDao employeeDao = new EmployeeDao();
    public int getTotalEmployee() {
        return employeeDao.getTotal();
    }

    public void createEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }
}
