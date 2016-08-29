package com.qigong.ticketservice.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name ="seathold_detail")
public class SeatHoldDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	@Version
    private Integer version;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="seathold_id", nullable=false)
    private SeatHold seatHold;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="level_id", nullable=false)
    private Level level;

    @Column(name="seat_number", nullable=false)
    private Integer seatNumber;

    public SeatHoldDetail() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public SeatHold getSeatHold() {
        return seatHold;
    }

    public void setSeatHold(SeatHold seatHold) {
        this.seatHold = seatHold;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }
}
