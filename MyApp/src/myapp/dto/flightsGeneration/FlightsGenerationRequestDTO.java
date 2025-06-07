/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.dto.flightsGeneration;

import java.time.LocalDate;

/**
 *
 * @author HP
 */
public class FlightsGenerationRequestDTO {

    private LocalDate week_startAt; //yyyy-MM-dd
    private LocalDate week_endAt; //yyyy-MM-dd

    //------- [End : constructor, getter(), setter(), toString()]-------
    public FlightsGenerationRequestDTO() {
    }

    public FlightsGenerationRequestDTO(LocalDate week_startAt, LocalDate week_endAt) {
        setWeek_startAt(week_startAt);
        setWeek_endAt(week_endAt);
    }

    public LocalDate getWeek_startAt() {
        return week_startAt;
    }

    public LocalDate getWeek_endAt() {
        return week_endAt;
    }

    public void setWeek_startAt(LocalDate week_startAt) {
        if (week_startAt == null) {
            throw new IllegalArgumentException("Week start date must not be null.");
        }
        this.week_startAt = week_startAt;

        // validate that end date is not before start date
        if (this.week_endAt != null && this.week_endAt.isBefore(this.week_startAt)) {
            throw new IllegalArgumentException("Week end date cannot be before start date.");
        }
    }

    public void setWeek_endAt(LocalDate week_endAt) {
        if (week_endAt == null) {
            throw new IllegalArgumentException("Week end date must not be null.");
        }
        this.week_endAt = week_endAt;

        // validate that end date is not before start date
        if (this.week_startAt != null && this.week_endAt.isBefore(this.week_startAt)) {
            throw new IllegalArgumentException("Week end date cannot be before start date.");
        }
    }

    @Override
    public String toString() {
        return "FlightsGenerationRequestDTO{" + "week_startAt=" + week_startAt + ", week_endAt=" + week_endAt + '}';
    }
    //------- [End : constructor, getter(), setter(), toString()]-------

}
