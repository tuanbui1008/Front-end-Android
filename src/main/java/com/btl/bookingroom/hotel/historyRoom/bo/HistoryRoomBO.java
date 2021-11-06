package com.btl.bookingroom.hotel.historyRoom.bo;

import javax.persistence.*;

@Entity(name = "dbo_history_room")
public class HistoryRoomBO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "id_book_room")
    private int idBookRoom;

    @Column(name = "id_room")
    private int idRoom;

    @Column(name = "amount_person")
    private int amountPerson;

    @Column(name = "amount_room")
    private int amountRoom;

    public HistoryRoomBO() {
    }

    public HistoryRoomBO(int id, int idBookRoom, int idRoom, int amountPerson, int amountRoom) {
        this.id = id;
        this.idBookRoom = idBookRoom;
        this.idRoom = idRoom;
        this.amountPerson = amountPerson;
        this.amountRoom = amountRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBookRoom() {
        return idBookRoom;
    }

    public void setIdBookRoom(int idBookRoom) {
        this.idBookRoom = idBookRoom;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getAmountPerson() {
        return amountPerson;
    }

    public void setAmountPerson(int amountPerson) {
        this.amountPerson = amountPerson;
    }

    public int getAmountRoom() {
        return amountRoom;
    }

    public void setAmountRoom(int amountRoom) {
        this.amountRoom = amountRoom;
    }
}
