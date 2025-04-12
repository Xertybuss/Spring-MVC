package ma.enset.hospital.web;

import lombok.AllArgsConstructor;
import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model){
        List<Patient> patientList = patientRepository.findAll();
        model.addAttribute("patientList", patientList);
        return "patients";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "keyword", defaultValue = "") String keyword, Model model) {
        List<Patient> patientList = patientRepository.findByNomContainsIgnoreCase(keyword);
        model.addAttribute("patientList", patientList);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/delete")
    public String delete(Long id){
        patientRepository.deleteById(id);
        return "redirect:/index";
    }
}
