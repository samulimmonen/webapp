package com.solitawebappbackend.demo.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "electricitydata", schema = "public")
public class Electricity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Column(name = "starttime")
    private LocalDateTime startTime;

    @Column(name = "productionamount")
    private BigDecimal productionAmount;

    @Column(name = "consumptionamount")
    private BigDecimal consumptionAmount;

    @Column(name = "hourlyprice")
    private BigDecimal hourlyPrice;

    public Electricity() {}

    public Electricity(Long id, LocalDate date, LocalDateTime startTime,
                       BigDecimal productionAmount, BigDecimal consumptionAmount,
                       BigDecimal hourlyPrice) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.productionAmount = productionAmount;
        this.consumptionAmount = consumptionAmount;
        this.hourlyPrice = hourlyPrice;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public BigDecimal getProductionAmount() { return productionAmount; }
    public void setProductionAmount(BigDecimal productionAmount) { this.productionAmount = productionAmount; }

    public BigDecimal getConsumptionAmount() { return consumptionAmount; }
    public void setConsumptionAmount(BigDecimal consumptionAmount) { this.consumptionAmount = consumptionAmount; }

    public BigDecimal getHourlyPrice() { return hourlyPrice; }
    public void setHourlyPrice(BigDecimal hourlyPrice) { this.hourlyPrice = hourlyPrice; }

    public String getStartTimeAsString() {
        return startTime != null ? startTime.toString() : null;
    }
}
