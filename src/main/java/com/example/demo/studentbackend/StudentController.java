package com.example.demo.studentbackend;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    @GetMapping
    public List<Student> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Student add(@RequestBody Student s) {
        return repo.save(s);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Integer id, @RequestBody Student s) {

        Student existing = repo.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setName(s.getName());
        existing.setEmail(s.getEmail());
        existing.setCourse(s.getCourse());

        return repo.save(existing);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
