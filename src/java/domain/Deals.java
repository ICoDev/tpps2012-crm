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
@Table(name = "deals")
@NamedQueries({
    @NamedQuery(name = "Deals.findAll", query = "SELECT d FROM Deals d")})
public class Deals implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "responsible")
    private String responsible;
    @Size(max = 2147483647)
    @Column(name = "deals_tag")
    private String dealsTag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "deals_type")
    private String dealsType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "money")
    private int money;
    @Size(max = 2147483647)
    @Column(name = "deals_name")
    private String dealsName;
    @OneToMany(mappedBy = "dealsId")
    private Set<Events> eventsSet;
    @OneToMany(mappedBy = "dealsId")
    private Set<Tasks> tasksSet;
    @OneToMany(mappedBy = "dealsId")
    private Set<Contacts> contactsSet;

    public Deals() {
    }

    public Deals(Integer id) {
        this.id = id;
    }

    public Deals(Integer id, String responsible, String dealsType, int money) {
        this.id = id;
        this.responsible = responsible;
        this.dealsType = dealsType;
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getDealsTag() {
        return dealsTag;
    }

    public void setDealsTag(String dealsTag) {
        this.dealsTag = dealsTag;
    }

    public String getDealsType() {
        return dealsType;
    }

    public void setDealsType(String dealsType) {
        this.dealsType = dealsType;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDealsName() {
        return dealsName;
    }

    public void setDealsName(String dealsName) {
        this.dealsName = dealsName;
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

    public Set<Contacts> getContactsSet() {
        return contactsSet;
    }

    public void setContactsSet(Set<Contacts> contactsSet) {
        this.contactsSet = contactsSet;
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
        if (!(object instanceof Deals)) {
            return false;
        }
        Deals other = (Deals) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Deals[ id=" + id + " ]";
    }
    
}
