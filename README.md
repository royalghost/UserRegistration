# Purpose
A simple web app to demonstrate the use of Spring MVC, Thymeleaf, Spring Boot, Bean Validation, Unit Test and persistence in HSQLDB.

## User Registration Validation
The following rules are applied for validation and app behavior:

 * The username field accepts alpha-numeric values only 
 * The username length is no less than 5 characters 
 * The username is not already registered 
 * The password has a minimum length of 8 characters and contains at least 1 number, 1 uppercase, and 1 lowercase character 
 * The user gets feedback when the username or the password doesn't meet the criteria 
 * Upon submission of a valid username and password, they are persisted to a database 
 * The user gets feedback that he/she has been registered

## Validation Rules
	@Entity
	@Table(name = "person", uniqueConstraints=@UniqueConstraint(columnNames= {"userName"}))
	public class PersonForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Username is required")
	@Size(min=5, message="Username length is no less than 5 characters")
	@Pattern(message="Username accepts alpha-numeric values only" , regexp="^[a-zA-z0-9]*$")
	private String userName;
	
	@NotNull(message = "Password is required")
	@Size(min=8, message="The password length is no less than 8 characters")
	@Pattern(message="Password should contains at least 1 number, 1 uppercase, and 1 lowercase character ", regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
	private String password;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Username is " + this.userName + " and password is " + this.password;
	}

    }

# Controllers

	@Controller
	public class PersonRegistration implements WebMvcConfigurer {
	
	@Autowired
	private PersonService personService;
	
	private static final String USER_REGISTRATION_FORM = "personForm";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }
	
	@GetMapping("/")
	public String showForm(PersonForm personForm) {
		return USER_REGISTRATION_FORM;
	}
	
	@PostMapping("/")
	public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
		System.out.println(personForm.toString());
		if (bindingResult.hasErrors()) {
			return USER_REGISTRATION_FORM;
		}
		//Save the Username and Password in HSQLDB
		personService.add(personForm);
		
		return "redirect:/results";
	}

	}
  
  # Service
  
  @Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public PersonServiceImpl() {
	}

	@Override
	public void add(PersonForm person) {
		personRepository.save(person);
	}

}
