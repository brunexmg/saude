package com.oficina.saude.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("pacientes")
public class PacienteController {

	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("/paciente/CadastroPasciente");
		
		return mv;
	}
	
}
