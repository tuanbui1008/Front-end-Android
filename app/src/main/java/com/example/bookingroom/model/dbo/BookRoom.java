package com.example.bookingroom.model.dbo;

import java.util.Date;

public class BookRoom {
    private int id;
    private int idCustomer;
    private Date dateRegister;
    private Date dateOfArrival;
    private Date dateGo;

    public BookRoom() {
    }

    public BookRoom(int id, int idCustomer, Date dateRegister, Date dateOfArrival, Date dateGo) {
        this.id = id;
        this.idCustomer = idCustomer;
        this.dateRegister = dateRegister;
        this.dateOfArrival = dateOfArrival;
        this.dateGo = dateGo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Date getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(Date dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public Date getDateGo() {
        return dateGo;
    }

    public void setDateGo(Date dateGo) {
        this.dateGo = dateGo;
    }
}
