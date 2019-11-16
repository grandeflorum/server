package domain;

import javax.persistence.*;

@Table(name = "SYS_REGION")
public class SysRegion {
    @Column(name = "OBJECTID")
    private Short objectid;

    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Object id;

    @Column(name = "CODE")
    private Object code;

    @Column(name = "NAME")
    private Object name;

    @Column(name = "PARENT_ID")
    private Object parentId;

    @Column(name = "RANK")
    private Short rank;

    @Column(name = "REGIONTYOE")
    private Short regiontyoe;

    @Column(name = "SHAPE")
    private Object shape;

    @Column(name = "X_SHIFT")
    private Short xShift;

    @Column(name = "Y_SHIFT")
    private Short yShift;

    @Column(name = "SE_ANNO_CAD_DATA")
    private byte[] seAnnoCadData;

    /**
     * @return OBJECTID
     */
    public Short getObjectid() {
        return objectid;
    }

    /**
     * @param objectid
     */
    public void setObjectid(Short objectid) {
        this.objectid = objectid;
    }

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
     * @return CODE
     */
    public Object getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(Object code) {
        this.code = code;
    }

    /**
     * @return NAME
     */
    public Object getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(Object name) {
        this.name = name;
    }

    /**
     * @return PARENT_ID
     */
    public Object getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    /**
     * @return RANK
     */
    public Short getRank() {
        return rank;
    }

    /**
     * @param rank
     */
    public void setRank(Short rank) {
        this.rank = rank;
    }

    /**
     * @return REGIONTYOE
     */
    public Short getRegiontyoe() {
        return regiontyoe;
    }

    /**
     * @param regiontyoe
     */
    public void setRegiontyoe(Short regiontyoe) {
        this.regiontyoe = regiontyoe;
    }

    /**
     * @return SHAPE
     */
    public Object getShape() {
        return shape;
    }

    /**
     * @param shape
     */
    public void setShape(Object shape) {
        this.shape = shape;
    }

    /**
     * @return X_SHIFT
     */
    public Short getxShift() {
        return xShift;
    }

    /**
     * @param xShift
     */
    public void setxShift(Short xShift) {
        this.xShift = xShift;
    }

    /**
     * @return Y_SHIFT
     */
    public Short getyShift() {
        return yShift;
    }

    /**
     * @param yShift
     */
    public void setyShift(Short yShift) {
        this.yShift = yShift;
    }

    /**
     * @return SE_ANNO_CAD_DATA
     */
    public byte[] getSeAnnoCadData() {
        return seAnnoCadData;
    }

    /**
     * @param seAnnoCadData
     */
    public void setSeAnnoCadData(byte[] seAnnoCadData) {
        this.seAnnoCadData = seAnnoCadData;
    }
}