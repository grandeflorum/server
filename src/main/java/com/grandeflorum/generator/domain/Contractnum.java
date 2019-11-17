package domain;

import javax.persistence.*;

public class Contractnum {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Object id;

    @Column(name = "TYPE")
    private Object type;

    @Column(name = "TIME")
    private Object time;

    @Column(name = "MAXNUM")
    private Short maxnum;

    /**
     * @return ID
     */
    public Object getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Object id) {
        this.id = id;
    }

    /**
     * @return TYPE
     */
    public Object getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Object type) {
        this.type = type;
    }

    /**
     * @return TIME
     */
    public Object getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Object time) {
        this.time = time;
    }

    /**
     * @return MAXNUM
     */
    public Short getMaxnum() {
        return maxnum;
    }

    /**
     * @param maxnum
     */
    public void setMaxnum(Short maxnum) {
        this.maxnum = maxnum;
    }
}