/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author KC
 */
@Entity
@Table(name = "tasks")
@NamedQueries({
    @NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t")})
public class Tasks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "autor")
    private String autor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "responsible")
    private String responsible;
    @Column(name = "tasks_date")
    @Temporal(TemporalType.DATE)
    private Date tasksDate;
    @Column(name = "tasks_time")
    @Temporal(TemporalType.TIME)
    private Date tasksTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tasks_type")
    private String tasksType;
    @Size(max = 2147483647)
    @Column(name = "tasks_text")
    private String tasksText;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "status")
    private String status;
    @Size(max = 2147483647)
    @Column(name = "tasks_name")
    private String tasksName;
    @OneToMany(mappedBy = "tasksId")
    private Set<Events> eventsSet;
    @JoinColumn(name = "deals_id", referencedColumnName = "id")
    @ManyToOne
    private Deals dealsId;
    @JoinColumn(name = "contacts_id", referencedColumnName = "id")
    @ManyToOne
    private Contacts contactsId;

    public Tasks() {
    }

    public Tasks(Integer id) {
        this.id = id;
    }

    public Tasks(Integer id, String autor, String responsible, String tasksType, String status) {
        this.id = id;
        this.autor = autor;
        this.responsible = responsible;
        this.tasksType = tasksType;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public Date getTasksDate() {
        return tasksDate;
    }

    public void setTasksDate(Date tasksDate) {
        this.tasksDate = tasksDate;
    }

    public Date getTasksTime() {
        return tasksTime;
    }

    public void setTasksTime(Date tasksTime) {
        this.tasksTime = tasksTime;
    }

    public String getTasksType() {
        return tasksType;
    }

    public void setTasksType(String tasksType) {
        this.tasksType = tasksType;
    }

    public String getTasksText() {
        return tasksText;
    }

    public void setTasksText(String tasksText) {
        this.tasksText = tasksText;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTasksName() {
        return tasksName;
    }

    public void setTasksName(String tasksName) {
        this.tasksName = tasksName;
    }

    public Set<Events> getEventsSet() {
        return eventsSet;
    }

    public void setEventsSet(Set<Events> eventsSet) {
        this.eventsSet = eventsSet;
    }

    public Deals getDealsId() {
        return dealsId;
    }

    public void setDealsId(Deals dealsId) {
        this.dealsId = dealsId;
    }

    public Contacts getContactsId() {
        return contactsId;
    }

    public void setContactsId(Contacts contactsId) {
        this.contactsId = contactsId;
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
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Tasks[ id=" 
                + id + " " 
                + autor + " "
                + responsible + " "
                + status + " "
                + tasksType + " "
                + tasksDate.toString() + " "
                + tasksTime.toString() + " "
                + " ]";
    }
    
}
