package com.qigong.ticketservice.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name ="seathold")
public class SeatHold implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="seathold_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer seatHoldId;

	@Version
    private Integer version;

    @Column(name="customer_email", length=50, nullable=false)
    private String customerEmail;

    @Column(name="confirmation_code", length=20)
    private String confirmationCode;

    @Column(name="hold_time", nullable=false)
    private Timestamp holdTime;

    @Column(name="reservation_time")
    private Timestamp reservationTime;

    @OneToMany(cascade={CascadeType.ALL}, mappedBy="seatHold", fetch=FetchType.LAZY)
    private Set<SeatHoldDetail> seatHoldDetails = new HashSet<SeatHoldDetail>();

    public SeatHold() {}

    public Integer getSeatHoldId() {
        return seatHoldId;
    }

    public void setSeatHoldId(Integer seatHoldId) {
        this.seatHoldId = seatHoldId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public Timestamp getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(Timestamp holdTime) {
        this.holdTime = holdTime;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Set<SeatHoldDetail> getSeatHoldDetails() {
        return seatHoldDetails;
    }

    public void setSeatHoldDetails(Set<SeatHoldDetail> seatHoldDetails) {
        this.seatHoldDetails = seatHoldDetails;
    }
}
