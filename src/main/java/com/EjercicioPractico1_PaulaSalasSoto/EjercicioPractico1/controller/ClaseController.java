package com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.controller;

import com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.domain.Categoria;
import com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.domain.Clase;
import com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.service.CategoriaService;
import com.EjercicioPractico1_PaulaSalasSoto.EjercicioPractico1.service.ClaseService;
import jakarta.validation.Valid;
import java.util.Locale;
import java.util.List;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clase")
public class ClaseController {

    // El servicio es final para asegurar la inmutabilidad
    private final ClaseService claseService;
    private final MessageSource messageSource;
    private final CategoriaService categoriaService;

    // Inyección por constructor (No requiere @Autowired en Spring moderno)
    public ClaseController(ClaseService claseService, MessageSource messageSource, CategoriaService categoriaService) {
        this.claseService = claseService;
        this.messageSource = messageSource;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String inicio(@RequestParam(name="id", required=false) Integer id, Model model) { 
        List<Clase> clases = claseService.getClasesLista(id);
        model.addAttribute("clases", clases);
        model.addAttribute("totalClases", clases.size());
        List<Categoria> categorias = categoriaService.getCategoriasLista(null);
        model.addAttribute("categorias", categorias);
        model.addAttribute("clase", new Clase());
        return "/clase/listado";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Clase clase, RedirectAttributes redirectAttributes) {

        claseService.save(clase);
        redirectAttributes.addFlashAttribute("todoOk", messageSource.getMessage("mensaje.actualizado", null, Locale.getDefault()));

        return "redirect:/clase/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        String titulo = "todoOk";
        String detalle = "mensaje.eliminado";
        try {
            claseService.delete(id);
        } catch (IllegalArgumentException e) {
            titulo = "error"; // Captura la excepción de argumento inválido para el mensaje de "no existe"
            detalle = "clase.error01";
        } catch (IllegalStateException e) {
            titulo = "error"; // Captura la excepción de estado ilegal para el mensaje de "datos asociados"
            detalle = "clase.error02";
        } catch (Exception e) {
            titulo = "error";  // Captura cualquier otra excepción inesperada
            detalle = "clase.error03";
        }
        redirectAttributes.addFlashAttribute(titulo, messageSource.getMessage(detalle, null, Locale.getDefault()));
        return "redirect:/clase/listado";
    }

   @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Clase> claseOpt = claseService.getClase(id);
        if (claseOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage("clase.error01", null, Locale.getDefault()));
            return "redirect:/clase/listado";
        }
        model.addAttribute("clase", claseOpt.get());
        model.addAttribute("categorias", categoriaService.getCategoriasLista(null));
        return "/clase/modifica";
    }
    
    @GetMapping("/contacto")
    public String mostrarContacto() {
        return "contacto/listado"; 
    }
}