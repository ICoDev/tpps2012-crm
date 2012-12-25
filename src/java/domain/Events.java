/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author KC
 */
@Entity
@Table(name = "events")
@NamedQueries({
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e")})
public class Events implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "events_date")
    @Temporal(TemporalType.DATE)
    private Date eventsDate;
    @Size(max = 2147483647)
    @Column(name = "events_text")
    private String eventsText;
    @Column(name = "events_time")
    @Temporal(TemporalType.TIME)
    private Date eventsTime;
    @JoinColumn(name = "tasks_id", referencedColumnName = "id")
    @ManyToOne
    private Tasks tasksId;
    @JoinColumn(name = "deals_id", referencedColumnName = "id")
    @ManyToOne
    private Deals dealsId;
    @JoinColumn(name = "contakts_id", referencedColumnName = "id")
    @ManyToOne
    private Contacts contaktsId;

    public Events() {
    }

    public Events(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEventsDate() {
        return eventsDate;
    }

    public void setEventsDate(Date eventsDate) {
        this.eventsDate = eventsDate;
    }

    public String getEventsText() {
        return eventsText;
    }

    public void setEventsText(String eventsText) {
        this.eventsText = eventsText;
    }

    public Date getEventsTime() {
        return eventsTime;
    }

    public void setEventsTime(Date eventsTime) {
        this.eventsTime = eventsTime;
    }

    public Tasks getTasksId() {
        return tasksId;
    }

    public void setTasksId(Tasks tasksId) {
        this.tasksId = tasksId;
    }

    public Deals getDealsId() {
        return dealsId;
    }

    public void setDealsId(Deals dealsId) {
        this.dealsId = dealsId;
    }

    public Contacts getContaktsId() {
        return contaktsId;
    }

    public void setContaktsId(Contacts contaktsId) {
        this.contaktsId = contaktsId;
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
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.Events[ id=" + id + " "
                + eventsText + " "
                + eventsDate.toString() + " "
                + eventsTime.toString() + " "
                + " ]";
    }   
}
