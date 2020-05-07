# kata-birthday-greetings
TDD Kata in Java inspired by [Matteo Vaccari](http://matteo.vaccari.name/blog/archives/154)


1. I implemented it following [baby steps](http://codingdojo.org/BabySteps/) 
2. Of course using the TDD cycle: Red ![#f03c15](https://placehold.it/15/f03c15/000000?text=+) --> Green ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) --> Refactor
3. My approach to write functional tests for TDD can be found here is a [diagram](https://drive.google.com/file/d/1ZWTPnbqD1Yd9sAabjmCP2TM_KA5Xla7j/view?usp=sharing) showing how tests interacts only with Domain Objects and never with external dependencies.

## Object Oriented Design and other heuristics
TDD does not mean you will end up with a good design. A good designer will likely come up with a good design. That said, I've listed couple of heurists:

1. Avoid usage of null, instead use Null Object pattern (you can see this on class ```NullEmployee ```).

2. Always refactor with all tests in Green ![#c5f015](https://placehold.it/15/c5f015/000000?text=+).

3. Always ***run all tests*** when you're refatoring.

4. Test should have full control of everything (time, date, test data, etc). Please check class ```BirthdayGreetingsTestObjects``` and how it is used on Tests.

5. Never hardcode today() or any time/date inside your classes. It is better to pass it as parameter. Please check method ```greetOn(LocalDate date)``` in class ```BirthdayGreeting``` . Even test objects you can make them flexible on time/date like
method ```employeeSystemOfRecordsWithTwoEmployeeBornOn(LocalDate date) ```

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
10. Avoid naming classes like ```YYYManager```, ```YYYController```, ```EmployeeValidator```, 

11. You will get the most of TDD if you create **functional tests** (not unit tests nor integration tests).
 - Unit tests are very fine grained (e.g: EmployeeTest where you will test only Employee class isoleted). 
 - Integration tests depend on I/O and usually are quite slow and unpredictable. 
 
 Both (unit and integration tests) could be important for your project, but they are not part of pure hardcore TDD :-)

12. Rember a good developer is also a good tester. Be proffesional and responsible, always develop robust software following best practices. It will save you time, headaches, pager duties, issues in production, etc.

## Be in the zone
- Music to keep you in the zone [radio de x-team](https://radio.x-team.com/)

## References 
### In English
- [Adrian Bolboaca](https://blog.adrianbolboaca.ro/) blog with tons of good quality videos doing TDD.

### In Spanish
- [Diseño ágil con TDD](https://uniwebsidad.com/libros/tdd)
- [10 Pines University](https://university.10pines.com/webinars_and_videos)
- [Implementando Exception en Ruby](https://www.youtube.com/watch?v=nlvCYJodigM&list=PLMkq_h36PcLA4yY58tQgj5FAXRzMaZAaY)
- [Aprendiendo Smalltak](https://www.youtube.com/watch?v=blj7itWxk2Y&list=PLMkq_h36PcLCtLKrrdOKKFV2r267VFH_t)
## Authors

* **Matias Tripode** - [My profile on Linkedin](https://www.linkedin.com/in/matiastripode/) and [on twitter](https://twitter.com/TripodeMatias)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* [Hernan Wilkinson](https://www.linkedin.com/in/hernanwilkinson/) and [10pines](https://university.10pines.com/webinars_and_videos) for sharing such amazing knowledge

