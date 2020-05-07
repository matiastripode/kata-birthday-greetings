package functionaltests;

import business.Employee;
import business.GreetablePerson;
import business.NullEmployee;
import infrastructure.DataBaseSystem;
import infrastructure.EmployeeSystemOfRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * 1.Loads a set of employee records from a flat file
 * The flat file is a sequence of records, separated by newlines; this are the first few lines:
 last_name, first_name, date_of_birth, email
 Doe, John, 1982/10/08, john.doe@foobar.com
 Ann, Mary, 1975/09/11, mary.ann@foobar.com
 * */

public class EmployeeSystemOfRecordTest {

    private BirthdayGreetingsTestObjects testObjects;

    @Before
    public void  setUp() {
        this.testObjects = new BirthdayGreetingsTestObjects();
    }

    @Test
    public void test01_whenSORHasAnEmployeeItCanReturnHerByEmail() {
        EmployeeSystemOfRecord employeeSOR = new EmployeeSystemOfRecord(this.testObjects.dbSystemReturnTwoEmployees());
        Employee maryAnn = new Employee("Mary", "Ann",
                LocalDate.of(1975, Month.SEPTEMBER, 10),
                "mary.ann@foobar.com");
        try {
            Assert.assertEquals(employeeSOR.getEmployeeByEmail("mary.ann@foobar.com"), maryAnn);
        }catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void test02_whenANonExistingEmployeeIsQueriedThenSORReturnANullEmployee() {
        EmployeeSystemOfRecord employeeSOR = new EmployeeSystemOfRecord(this.testObjects.dbSystemReturnTwoEmployees());

        try {
            GreetablePerson nonExistingEmployee = employeeSOR.getEmployeeByEmail("anothermary.ann@foobar.com");
            Assert.assertTrue(nonExistingEmployee.getClass() == NullEmployee.class);
        } catch (Exception e) {
            Assert.fail();
        }

    }

    @Test
    public void test03_whenSORHasNoEmployeesThenItReturnsANullEmployeeInAllQueriesByEmail() {
        EmployeeSystemOfRecord employeeSOR = new EmployeeSystemOfRecord(this.testObjects.dbSystemReturnEmpty());
        try {
            GreetablePerson nonExistingEmployee = employeeSOR.getEmployeeByEmail("anothermary.ann@foobar.com");
            Assert.assertTrue(nonExistingEmployee.getClass() == NullEmployee.class);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void test04_whenDBThrowsANetworkFailureThenSORThrowsItBack() {
        EmployeeSystemOfRecord employeeSOR = new EmployeeSystemOfRecord(this.testObjects.dbSystemFailedNetworkConnection());

        try {
            GreetablePerson nonExistingEmployee = employeeSOR.getEmployeeByEmail("mary.ann@foobar.com");
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals(DataBaseSystem.ERROR_NETWORK_FAILED, e.getMessage());
        }
    }
}
