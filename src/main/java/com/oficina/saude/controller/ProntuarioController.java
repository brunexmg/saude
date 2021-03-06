package com.oficina.saude.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oficina.saude.model.Prontuario;
import com.oficina.saude.model.Status;
import com.oficina.saude.repository.Prontuarios;
import com.oficina.saude.service.CadastroProntuarioService;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

	@Autowired
	private CadastroProntuarioService cadastroProntuarioService;

	@Autowired
	private Prontuarios prontuarios;

	@RequestMapping("/novo")
	public ModelAndView novo(Prontuario prontuario) {
		ModelAndView mv = new ModelAndView("prontuario/CadastroProntuario");
		mv.addObject("agendado", Status.AGENDADO);
		mv.addObject("prontuarios", prontuarios.findByStatus(Status.AGENDADO));
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView agendar(@Valid Prontuario prontuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(prontuario);
		}
		try {
			if (prontuario.getData() == null) {
				java.util.Date udata = new java.util.Date();
				Date data = new Date(udata.getTime());
				prontuario.setData(data);
				prontuario.setStatus(Status.AGENDADO);
			}
			cadastroProntuarioService.salvar(prontuario);
			attributes.addFlashAttribute("mensagem", "Agendamento salvo com sucesso");
			return new ModelAndView("redirect:/prontuarios/novo");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/prontuarios/novo");
		}
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		cadastroProntuarioService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Consulta excluída");
		return new ModelAndView("redirect:/prontuarios/novo");
	}

	@RequestMapping("/incluir/{id}")
	public ModelAndView retornaProntuario(@PathVariable("id") Prontuario prontuario) {
		ModelAndView mv = new ModelAndView("prontuario/EditaProntuario");
		prontuario.setStatus(Status.ATENDIDO);
		mv.addObject("prontuario", prontuario);
		mv.addObject(prontuario);
		return mv;
	}
	
	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	public ModelAndView incluirAtributosProntuario(Prontuario prontuario) {
		Prontuario pront = prontuarios.findOne(prontuario.getId());
		pront.setAltura(prontuario.getAltura());
		pront.setPressao(prontuario.getPressao());
		pront.setPeso(prontuario.getPeso());
		pront.setStatus(Status.AGUARDANDO);
		cadastroProntuarioService.salvar(pront);
		return new ModelAndView("redirect:/prontuarios/lista");
	}

	@RequestMapping("/lista")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("prontuario/ListaProntuarios");
		mv.addObject("statuss", Status.values());
		return mv;
	}

	@RequestMapping(value = "/lista", method = RequestMethod.POST)
	public List<Prontuario> listar(@RequestParam Status status) {
		List<Prontuario> prontuarios = this.prontuarios.findByStatus(status);
		return prontuarios;
	}

}
