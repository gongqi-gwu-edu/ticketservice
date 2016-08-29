package com.qigong.ticketservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name ="level")
public class Level implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="level_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer levelId;

    @Version
    private Integer version;

    @Column(name="level_name", length=15, nullable=false)
    private String levelName;

    @Column(name="price", nullable=false)
    private BigDecimal price;

    @Column(name="row_number", nullable=false)
    private Integer rowNumber;

    @Column(name="seat_number_in_row", nullable=false)
    private Integer seatNumberInRow;

    @OneToMany(cascade={CascadeType.ALL}, mappedBy="level", fetch=FetchType.LAZY)
    private Set<SeatHoldDetail> seatHoldDetails = new HashSet<SeatHoldDetail>();

    public Level() {}

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getSeatNumberInRow() {
        return seatNumberInRow;
    }

    public void setSeatNumberInRow(Integer seatNumberInRow) {
        this.seatNumberInRow = seatNumberInRow;
    }

    public Set<SeatHoldDetail> getSeatHoldDetails() {
        return seatHoldDetails;
    }

    public void setSeatHoldDetails(Set<SeatHoldDetail> seatHoldDetails) {
        this.seatHoldDetails = seatHoldDetails;
    }
}
