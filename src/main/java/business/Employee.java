package business;

import java.time.LocalDate;

public class Employee implements GreetablePerson {
    private final String name;
    private final LocalDate dateOfBirth;
    private final String email;
    private final String lastName;

    public Employee(String name, LocalDate dateOfBirth, String email) {
        this.name = name.split("\\,")[0];
        this.lastName = name.split("\\,")[1].trim();
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;

        return (this.name.equalsIgnoreCase(other.name)  &&
                this.lastName.equalsIgnoreCase(other.lastName) &&
                (this.dateOfBirth.compareTo(other.dateOfBirth) == 0) &&
                this.email.equalsIgnoreCase(other.email));
    }
    public String getName() {
        return this.name;
    }
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    @Override
    public String getEmail() {
        return this.email;
    }
}
