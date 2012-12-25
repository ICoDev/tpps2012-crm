/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author KC
 */
@Entity
@Table(name = "contacts")
@NamedQueries({
    @NamedQuery(name = "Contacts.findAll", query = "SELECT c FROM Contacts c")})
public class Contacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "contacts_address")
    private String contactsAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "responsible")
    private String responsible;
    @Size(max = 15)
    @Column(name = "contacts_email")
    private String contactsEmail;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "contacts_telephon")
    private Integer contactsTelephon;
    @Size(max = 2147483647)
    @Column(name = "contacts_tags")
    private String contactsTags;
    @Size(max = 2147483647)
    @Column(name = "contacts_note")
    private String contactsNote;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "contacts_name")
    private String contactsName;
    @Size(max = 40)
    @Column(name = "contacts_post")
    private String contactsPost;
    @OneToMany(mappedBy = "contaktsId")
    private Set<Events> eventsSet;
    @OneToMany(mappedBy = "contactsId")
    private Set<Tasks> tasksSet;
    @JoinColumn(name = "deals_id", referencedColumnName = "id")
    @ManyToOne
    private Deals dealsId;
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @ManyToOne
    private Companies companyId;

    public Contacts() {
    }

    public Contacts(Integer id) {
        this.id = id;
    }

    public Contacts(Integer id, String responsible, String contactsName) {
        this.id = id;
        this.responsible = responsible;
        this.contactsName = contactsName;
    }

    public String getContactsAddress() {
        return contactsAddress;
    }

    public void setContactsAddress(String contactsAddress) {
        this.contactsAddress = contactsAddress;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getContactsEmail() {
        return contactsEmail;
    }

    public void setContactsEmail(String contactsEmail) {
        this.contactsEmail = contactsEmail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContactsTelephon() {
        return contactsTelephon;
    }

    public void setContactsTelephon(Integer contactsTelephon) {
        this.contactsTelephon = contactsTelephon;
    }

    public String getContactsTags() {
        return contactsTags;
    }

    public void setContactsTags(String contactsTags) {
        this.contactsTags = contactsTags;
    }

    public String getContactsNote() {
        return contactsNote;
    }

    public void setContactsNote(String contactsNote) {
        this.contactsNote = contactsNote;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPost() {
        return contactsPost;
    }

    public void setContactsPost(String contactsPost) {
        this.contactsPost = contactsPost;
    }

    public Set<Events> getEventsSet() {
        return eventsSet;
    }

    public void setEventsSet(Set<Events> eventsSet) {
        this.eventsSet = eventsSet;
    }

    public Set<Tasks> getTasksSet() {
        return tasksSet;
    }

    public void setTasksSet(Set<Tasks> tasksSet) {
        this.tasksSet = tasksSet;
    }

    public Deals getDealsId() {
        return dealsId;
    }

    public void setDealsId(Deals dealsId) {
        this.dealsId = dealsId;
    }

    public Companies getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Companies companyId) {
        this.companyId = companyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contacts)) {
            return false;
        }
        Contacts other = (Contacts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Contacts[ id=" + id + " ]";
    }
    
}
