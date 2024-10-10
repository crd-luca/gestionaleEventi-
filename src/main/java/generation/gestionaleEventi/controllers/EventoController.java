package generation.gestionaleEventi.controllers;

import generation.gestionaleEventi.entities.Evento;
import generation.gestionaleEventi.entities.Gestore;
import generation.gestionaleEventi.entities.Locale;
import generation.gestionaleEventi.entities.Persona;
import generation.gestionaleEventi.repositories.EventoRepository;
import generation.gestionaleEventi.services.AppService;
import generation.gestionaleEventi.services.EventoService;
import generation.gestionaleEventi.services.LocaleService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Controller
public class EventoController {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private LocaleService localeService;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AppService appService;

    @GetMapping("/creaEvento-form")
    public String creaEventoForm(@RequestParam("idLocale") Long idLocale, HttpSession session, Model model) {
        Locale locale = localeService.findById(idLocale); // Assicurati che questo ritorni un oggetto Locale valido
        if (locale != null) {
            model.addAttribute("locale", locale);
        } else {
            // Gestisci il caso in cui il locale non viene trovato, ad esempio, mostrando un messaggio di errore
            model.addAttribute("error", "Locale non trovato");
            return "errorPage"; // O un'altra vista di errore
        }
        return "creaEvento"; // Ritorna il template
    }
    

    @PostMapping("/createEvent")
    public String createEvent(
            @RequestParam("nome") String nome,
            @RequestParam(value = "capienza", required = false) Integer capienza,
            @RequestParam(value = "orario", required = false) String orario,
            @RequestParam("giorno") String giorno,
            @RequestParam(value = "prezzo", required = false) Double prezzo,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "idLocale") Long idLocale,
            HttpSession session,
            Model model) {

        // Controlla i permessi
        String userRole = (String) session.getAttribute("role");
        if (!"gestore".equalsIgnoreCase(userRole)) {
            model.addAttribute("message", "Non hai i permessi per creare un evento.");
            return "errorPage";
        }

        // Percorso dove salvare le immagini
        String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";
        String fileName = null;

        // Salvataggio dell'immagine se fornita
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                fileName = imageFile.getOriginalFilename();
                File file = new File(uploadDir + fileName);
                imageFile.transferTo(file); // Salva il file
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "Errore durante il salvataggio dell'immagine.");
                return "errorPage"; // Gestisci l'errore
            }
        }

        // Recupera il locale
        Locale locale = localeService.findById(idLocale);
        if (locale == null) {
            model.addAttribute("message", "Locale non trovato con l'ID fornito.");
            return "errorPage"; // Gestisci il caso in cui il locale non esiste
        }

        try {
            Date dataGiorno = Date.valueOf(giorno); // Converte 'giorno' nel formato corretto
            
            // Crea l'oggetto Evento
            Evento evento = new Evento();
            evento.setNome(nome);
            evento.setCapienza(capienza != null ? capienza : 0);  // Valore predefinito
            evento.setOrario(orario != null ? orario : "");       // Valore predefinito
            evento.setGiorno(dataGiorno); // Imposta la data
            evento.setPrezzo(prezzo != null ? prezzo : 0.0);      // Valore predefinito
            evento.setImmagineUrl(fileName != null ? "/images/" + fileName : "");  // Imposta URL immagine
            evento.setLocale(locale); // Associa l'evento al locale

            // Salva l'evento nel database
            eventoService.creatOrUpdate(evento);
            model.addAttribute("message", "Evento creato con successo!");
            return "redirect:/area-gestore/eventi"; // Reindirizza alla lista eventi
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", "Formato della data non valido.");
            return "errorPage"; // Gestisci errore di formato
        }
    }

    @GetMapping("/eventi/search")
    public String searchEventi(@RequestParam(value = "giorno", required = false) String giorno, Model model) {
        if (giorno == null) {
            model.addAttribute("error", "Data non valida");
            return "index"; // Torna alla homepage se la data è mancante
        } else {
            List<Evento> eventi = eventoRepository.findByGiorno(Date.valueOf(giorno));
            model.addAttribute("eventi", eventi);
            model.addAttribute("giorno", Date.valueOf(giorno)); // Indica che è stata effettuata una ricerca
            return "ricercaEventi"; // Restituisce la vista con i risultati
        }
    }

    @GetMapping("/area-gestore/eventi")
    public String areaEventi(HttpSession session, Model model) {
        Persona p = (Persona) session.getAttribute("persona");
        String role = (String) session.getAttribute("role");

        if ("gestore".equals(role) && p instanceof Gestore) {
            Gestore gestore = (Gestore) p;
            model.addAttribute("persona", gestore);
            model.addAttribute("gestore", gestore);

            // Ottieni la lista degli eventi
            model.addAttribute("eventi", eventoService.findByIdGestore(gestore.getId()));
            return "areaEventi"; // Nome del template
        }

        session.invalidate();
        return "redirect:/loginpage"; // Reindirizza se non autorizzato
    }

    @GetMapping("/evento/delete/{id}")
    public String delete(@PathVariable("id") Long idEvento, HttpSession session) {
        Persona p = (Persona) session.getAttribute("persona");
        String role = (String) session.getAttribute("role");

        if ("gestore".equals(role) && p instanceof Gestore) {
            if (idEvento <= 0) {
                appService.setMessage("Errore: ID evento non valido");
            } else {
                eventoService.delete(idEvento);
                appService.setMessage("Eliminazione avvenuta con successo");
            }
            return "redirect:/area-gestore"; // Torna alla lista eventi
        }

        appService.setMessage("Errore: richiesta non autorizzata");
        return "redirect:/loginpage"; // Reindirizza se non autorizzato
    }

    @PostMapping("/evento/update")
    public String update(
            @RequestParam("id") Long id,
            @RequestParam("nome") String nome,
            @RequestParam("orario") String orario,
            @RequestParam("giorno") String giorno,
            @RequestParam("prezzo") Double prezzo,
            HttpSession session) {

        Persona p = (Persona) session.getAttribute("persona");
        String role = (String) session.getAttribute("role");
        AppService as = context.getBean(AppService.class);

        if ("gestore".equals(role) && p instanceof Gestore) {
            try {
                Evento e = new Evento();
                e.setId(id);
                e.setNome(nome);
                e.setOrario(orario);
                e.setGiorno(Date.valueOf(giorno)); // Assicurati che la data sia nel formato 'yyyy-MM-dd'
                e.setPrezzo(prezzo);

                eventoService.creatOrUpdate(e);
                as.setMessage("Modifica avvenuta con successo");
                return "redirect:/area-gestore/eventi"; // Reindirizza alla lista eventi
            } catch (IllegalArgumentException e) {
                as.setMessage("Errore nel formato dei dati forniti");
                return "redirect:/area-gestore/eventi"; // Torna alla lista eventi
            }
        }

        as.setMessage("Errore: richiesta non autorizzata");
        return "redirect:/loginpage"; // Reindirizza se non autorizzato
    }
}
