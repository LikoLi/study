package powermock.session7;

import powermock.session4.Employee;
import powermock.session4.EmployeeDao;

public class EmployeeService {
    public void saveOrUpdate(Employee employee) {
        final EmployeeDao employeeDao = new EmployeeDao();
        long count = employeeDao.getCount(employee);

        if (count > 0) {
            employeeDao.updateEmployee(employee);
        } else {
            employeeDao.saveEmployee(employee);
        }
    }
}
