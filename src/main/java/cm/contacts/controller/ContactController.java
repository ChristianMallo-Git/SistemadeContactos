package cm.contacts.controller;

import cm.contacts.model.Contact;
import cm.contacts.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ContactController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ContactController.class);


    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String init(ModelMap model){
        List<Contact> contacts = contactService.listContact();
        contacts.forEach((contact)->LOGGER.info(contact.toString()));
        model.put("contacts", contacts);
        return "index";
    }

    @GetMapping("/add")
    public String showAdd(){
        return "add";
    }

    @PostMapping("/add")
    public String addContat(@ModelAttribute("contactForm") Contact contact ){
        LOGGER.info("Contact to add: " + contact);
        contactService.saveContact(contact);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable(value = "id") int idContact,
                           ModelMap model){
        Contact contact = contactService.searchById(idContact);
        LOGGER.info("Contact to edit (show): " + contact);
        model.put("contact", contact);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("contact") Contact contact){
        LOGGER.info("Contact to save: " + contact);
        contactService.saveContact(contact);
        return "redirect:/";
    }

@GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int idContact,
                         ModelMap model){
        Contact contact = new Contact();
        contact.setIdContact(idContact);
        contactService.deleteContact(contact);
        return "redirect:/";
}

}
