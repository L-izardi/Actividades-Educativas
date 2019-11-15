package gt.com.actividades.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tc_menu")
public class TcMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long menuId;

    @NotNull
    private String menuDesc;

    @NotNull
    private byte statusId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;


    public long getMenuId() {
        return this.menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public String getMenuDesc() {
        return this.menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public byte getStatusId() {
        return this.statusId;
    }

    public void setStatusId(byte statusId) {
        this.statusId = statusId;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}