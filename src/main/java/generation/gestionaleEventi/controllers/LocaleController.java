package generation.gestionaleEventi.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import generation.gestionaleEventi.services.AppService;
import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.entities.Gestore;
import generation.gestionaleEventi.entities.Locale;
import generation.gestionaleEventi.entities.Persona;
import generation.gestionaleEventi.services.GestoreService;
import generation.gestionaleEventi.services.LocaleService;
import jakarta.servlet.http.HttpSession;
@Controller
public class LocaleController {
    @Autowired
    private GestoreService gestoreService;
    @Autowired
    private LocaleService localeService;
    @Autowired
    private AppService appService;

     @GetMapping("/createLocale-form")
    public String creaLocaleForm( HttpSession session, Model model) {
       
        String userRole = (String) session.getAttribute("role");
        if (!"gestore".equals(userRole)) {
            model.addAttribute("message", "Non hai i permessi per creare un evento.");
            return "errorPage";
        }
        return "creaLocale";   
    }
    @PostMapping("/createLocale")
    public String createLocale(
            @RequestParam String nome,
            @RequestParam String indirizzo,
            @RequestParam String pIva,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            HttpSession session,
            Model model) {
    
        // Verifica se l'utente è un gestore
        Gestore gestore = (Gestore) session.getAttribute("persona"); // Ottieni il gestore dalla sessione
    if (gestore == null) {
        model.addAttribute("message", "Non hai i permessi per creare un locale.");
        return "errorPage"; // O gestisci diversamente
    }
    
      
   
    
        // Percorso dove salvare le immagini
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";
        String fileName = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                fileName = imageFile.getOriginalFilename();
                File file = new File(uploadDir + fileName);
                imageFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "Errore durante il salvataggio dell'immagine.");
                return "errorPage";
            }
        }
    
        // Crea il nuovo Locale e associa il Gestore
        Locale locale = new Locale();
        locale.setNome(nome);
        locale.setIndirizzo(indirizzo);
        locale.setPIva(pIva);
        locale.setImmagineUrl(fileName != null ? "/images/" + fileName : "");
        locale.setGestore(gestore);  // Associa il gestore al locale
    
        // Salva il locale nel database
        localeService.creatOrUpdate(locale);
    
        model.addAttribute("message", "Locale creato con successo!");
        return "redirect:/area-gestore";
    }
    @GetMapping("/locale/delete/{idLocale}")
    public String delete(@PathVariable("idLocale") Long idLocale, HttpSession session) {
        Persona p = (Persona) session.getAttribute("persona");
        String role = (String) session.getAttribute("role");
    
        if ("gestore".equals(role) && p instanceof Gestore) {
            if (idLocale <= 0) {
                appService.setMessage("Errore: ID locale non valido");
            } else {
                localeService.delete(idLocale); // Assicurati che questo metodo esista e funzioni correttamente
                appService.setMessage("Eliminazione avvenuta con successo");
            }
            return "redirect:/area-gestore"; // Torna alla lista dei locali
        }
    
        appService.setMessage("Errore: richiesta non autorizzata");
        return "redirect:/loginpage"; // Reindirizza se non autorizzato
    }
    
     }    
