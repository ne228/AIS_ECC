package com.example.ais_ecc.controllers;


import com.example.ais_ecc.entity.UD;
import com.example.ais_ecc.entity.User;
import com.example.ais_ecc.entity.actions.CreateUD;
import com.example.ais_ecc.models.CreateUDModel;
import com.example.ais_ecc.repositories.CreateUDRepository;
import com.example.ais_ecc.repositories.UDRepository;
import com.example.ais_ecc.repositories.UserRepository;
import com.example.ais_ecc.service.MapperService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/UD")
public class UDController {

    final CreateUDRepository createUDRepository;
    final UDRepository udRepository;
    final MapperService mapperService;

    final UserRepository userRepository;

    public UDController(CreateUDRepository createUDRepository, UDRepository udRepository, MapperService mapperService, UserRepository userRepository) {
        this.createUDRepository = createUDRepository;
        this.udRepository = udRepository;
        this.mapperService = mapperService;
        this.userRepository = userRepository;
    }

    @GetMapping("/create")
    public String createPage(Model model) {

        model.addAttribute("createUD", new CreateUDModel());
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/UD/create";
    }

    @PostMapping("/create")
    public String create(@Valid CreateUDModel createUDmodel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            List<User> users = userRepository.findAll();
            model.addAttribute("users", users);
            return "/UD/createUD";
        }
        var investigator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var Date = LocalDate.now();
        var createUD = mapperService.convertToEntity(createUDmodel);
        createUD.setInvestigator(investigator);
        createUD.setDate(Date);
        //Add applicants and suspects
        createUD.setApplicant(userRepository.findAllById(createUDmodel.ApplicantIds));
        createUD.setSuspect(userRepository.findAllById(createUDmodel.SuspectIds));


        // Created UD
        UD ud = new UD();
        ud.setActions(new ArrayList<>());
        ud.getActions().add(createUD);


        var entity = udRepository.save(ud);

        var savedEntity = createUDRepository.save(createUD);
        var actionId = entity.getActions().get(0).getId();
        return "redirect:/UD/action/" + actionId;
    }

    @GetMapping("/action/{id}")
    public String details(@PathVariable("id") String id, Model model) {

        var action = createUDRepository.findById(id);
        if (action.get() == null)
            return "notFound";

        var createUd = (CreateUD) action.get();
        model.addAttribute("createUD", createUd);

        //TODO сделать чтот-то с applicants and suspects
        return "/UD/details";

    }
}
