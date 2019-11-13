package com.grandeflorum.houseRental.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "HOUSE_RENTAL")
public class HouseRental {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "SERIALNUMBER")
    private String serialnumber;

    @Column(name = "LINKER")
    private String linker;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "REGIONCODE")
    private String regioncode;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ROOM")
    private Short room;

    @Column(name = "HALL")
    private Short hall;

    @Column(name = "TRAFFIC")
    private String traffic;

    @Column(name = "HOUSEAGE")
    private Short houseage;

    @Column(name = "RENTAL")
    private Short rental;

    @Column(name = "AREA")
    private Short area;

    @Column(name = "ISDECORATED")
    private Short isdecorated;

    @Column(name = "CURFLOOR")
    private Short curfloor;

    @Column(name = "TOTALFLOOR")
    private Short totalfloor;

    @Column(name = "DIRECTION")
    private String direction;

    @Column(name = "RENTDATE")
    private Date rentdate;

    @Column(name = "USETYPE")
    private Short usetype;

    @Column(name = "HOUSE_ID")
    private String houseId;

    @Column(name = "SYS_DATE")
    private Date sysDate;

    @Column(name = "SYS_UPD_DATE")
    private Date sysUpdDate;

    @Transient
    private String ljzid;
    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return SERIALNUMBER
     */
    public String getSerialnumber() {
        return serialnumber;
    }

    /**
     * @param serialnumber
     */
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    /**
     * @return LINKER
     */
    public String getLinker() {
        return linker;
    }

    /**
     * @param linker
     */
    public void setLinker(String linker) {
        this.linker = linker == null ? null : linker.trim();
    }

    /**
     * @return TELEPHONE
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * @return REGIONCODE
     */
    public String getRegioncode() {
        return regioncode;
    }

    /**
     * @param regioncode
     */
    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode == null ? null : regioncode.trim();
    }

    /**
     * @return ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return ROOM
     */
    public Short getRoom() {
        return room;
    }

    /**
     * @param room
     */
    public void setRoom(Short room) {
        this.room = room;
    }

    /**
     * @return HALL
     */
    public Short getHall() {
        return hall;
    }

    /**
     * @param hall
     */
    public void setHall(Short hall) {
        this.hall = hall;
    }

    /**
     * @return TRAFFIC
     */
    public String getTraffic() {
        return traffic;
    }

    /**
     * @param traffic
     */
    public void setTraffic(String traffic) {
        this.traffic = traffic == null ? null : traffic.trim();
    }

    /**
     * @return HOUSEAGE
     */
    public Short getHouseage() {
        return houseage;
    }

    /**
     * @param houseage
     */
    public void setHouseage(Short houseage) {
        this.houseage = houseage;
    }

    /**
     * @return RENTAL
     */
    public Short getRental() {
        return rental;
    }

    /**
     * @param rental
     */
    public void setRental(Short rental) {
        this.rental = rental;
    }

    /**
     * @return AREA
     */
    public Short getArea() {
        return area;
    }

    /**
     * @param area
     */
    public void setArea(Short area) {
        this.area = area;
    }

    /**
     * @return ISDECORATED
     */
    public Short getIsdecorated() {
        return isdecorated;
    }

    /**
     * @param isdecorated
     */
    public void setIsdecorated(Short isdecorated) {
        this.isdecorated = isdecorated;
    }

    /**
     * @return CURFLOOR
     */
    public Short getCurfloor() {
        return curfloor;
    }

    /**
     * @param curfloor
     */
    public void setCurfloor(Short curfloor) {
        this.curfloor = curfloor;
    }

    /**
     * @return TOTALFLOOR
     */
    public Short getTotalfloor() {
        return totalfloor;
    }

    /**
     * @param totalfloor
     */
    public void setTotalfloor(Short totalfloor) {
        this.totalfloor = totalfloor;
    }

    /**
     * @return DIRECTION
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    /**
     * @return RENTDATE
     */
    public Date getRentdate() {
        return rentdate;
    }

    /**
     * @param rentdate
     */
    public void setRentdate(Date rentdate) {
        this.rentdate = rentdate;
    }

    /**
     * @return USETYPE
     */
    public Short getUsetype() {
        return usetype;
    }

    /**
     * @param usetype
     */
    public void setUsetype(Short usetype) {
        this.usetype = usetype;
    }

    /**
     * @return HOUSE_ID
     */
    public String getHouseId() {
        return houseId;
    }

    /**
     * @param houseId
     */
    public void setHouseId(String houseId) {
        this.houseId = houseId == null ? null : houseId.trim();
    }

    /**
     * @return SYS_DATE
     */
    public Date getSysDate() {
        return sysDate;
    }

    /**
     * @param sysDate
     */
    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }

    /**
     * @return SYS_UPD_DATE
     */
    public Date getSysUpdDate() {
        return sysUpdDate;
    }

    /**
     * @param sysUpdDate
     */
    public void setSysUpdDate(Date sysUpdDate) {
        this.sysUpdDate = sysUpdDate;
    }

    public String getLjzid() {
        return ljzid;
    }

    public void setLjzid(String ljzid) {
        this.ljzid = ljzid;
    }
}