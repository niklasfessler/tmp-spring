package at.ccp19.api;

import at.ccp19.model.Student;
import at.ccp19.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class StudentsController {
    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("/students")
    public Iterable<Student> getAll(@RequestParam(value = "name", defaultValue = "World") String name) {

        return this.studentRepository.findAll();
    }


    @RequestMapping("/students/{name}")
    public Optional<Student> getStudentByName(@PathVariable String name) {
       // return Optional.of(this.studentRepository.findByUsername("Mario"));
        return null;
    }

    @PostMapping("/students")
    public void createUser(@RequestBody Student student) {

        this.studentRepository.save(student);
    }

    @GetMapping("/students/hashit")
    public String hash() {
        String passwordToHash = "password";
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("Sha-256");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
