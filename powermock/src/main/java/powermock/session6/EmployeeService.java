package powermock.session6;

import powermock.session4.Employee;

public class EmployeeService {
    public int getEmployeeService() {
        return EmployeeUtils.getEmployeeCount();
    }

    public void createEmployee(Employee employee) {
        EmployeeUtils.persistenceEmployee(employee);
    }
}
