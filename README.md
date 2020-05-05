# kata-birthday-greetings
TDD Kata in Java inspired by [Matteo Vaccari](http://matteo.vaccari.name/blog/archives/154)


1. I implemented it following [baby steps](http://codingdojo.org/BabySteps/) 
2. Of course using the TDD cycle: Red ![#f03c15](https://placehold.it/15/f03c15/000000?text=+) --> Green ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) --> Refactor

## On Object Oriented Design
TDD does not mean you will end up with a good design. A good designer will likely come up with a good design. That said, I've listed couple of heurists:

1. Avoid usage of null, instead use Null Object pattern (you can this on class ```NullEmployee ```).

2. Always refactor with all tests in Green ![#c5f015](https://placehold.it/15/c5f015/000000?text=+).

3. Always run all tests when you're refatoring.

4. Test should have fully control of everything (time, date, test data, etc). Please check class ```BirthdayGreetingsTestObjects```.

5. Never hardcode today() or any time/date inside your classes. It is better to pass it as parameter. Please check method ```greetOn(LocalDate date)``` in class ```BirthdayGreeting``` 

6. Pass dependencies in constructors.  Please check constructor ```BirthdayGreeting(EmailSystem emailSystem, EmployeeSystem employeeSystemOfRecords)```

7. Use names which represents better its meaning e.g: ```EmployeeSystemOfRecord```  is prefered over ```EmployeeRepository``` and ```BirthdayGreeting``` is prefered over ```BirthdayService```

8. Make your classes to depend on Interfaces 
```Java
 public BirthdayGreeting(EmailSystem emailSystem, EmployeeSystem employeeSystemOfRecords){
        this.emailSystem = emailSystem;
        this.employeeSystemOfRecords = employeeSystemOfRecords;
  }
 ```
9. Avoid Mocking frameworks as much as you can. Instead use a technique shown in class ```BirthdayGreetingsTestObjects```

```Java
public EmailSystem successEmailSystemWithTrue() { return (email) -> {return true;}; }
public EmailSystem successEmailSystemWithFalse() { return (email) -> {return false;}; }
```

## Be in the zone
- Music to keep you in the zone [radio de x-team](https://radio.x-team.com/)

## References 
### In English
- [Adrian Bolboaca](https://blog.adrianbolboaca.ro/) blog with tons of good quality videos doing TDD.

### In Spanish
- [Diseño ágil con TDD](https://uniwebsidad.com/libros/tdd)
- [10 Pines University](https://university.10pines.com/webinars_and_videos)

## Authors

* **Matias Tripode** - [My profile on Linkedin](https://www.linkedin.com/in/matiastripode/) and [on twitter](https://twitter.com/TripodeMatias)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* [Hernan Wilkinson](https://www.linkedin.com/in/hernanwilkinson/) and [10pines](https://university.10pines.com/webinars_and_videos) for sharing such amazing knowledge

