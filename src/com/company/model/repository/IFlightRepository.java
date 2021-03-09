package com.company.model.repository;

import com.company.model.entities.flight;

import java.util.ArrayList;

public interface IFlightRepository {

    ArrayList<flight> getAllFlights();

    void addNewFlight(flight newFlight);

}
