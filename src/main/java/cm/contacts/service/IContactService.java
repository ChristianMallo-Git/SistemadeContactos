package cm.contacts.service;

import cm.contacts.model.Contact;

import java.util.List;

public interface IContactService {

    public List<Contact> listContact();

    public Contact searchById(Integer idContact);

    public void saveContact(Contact contact);

    public void deleteContact(Contact contact);

}
