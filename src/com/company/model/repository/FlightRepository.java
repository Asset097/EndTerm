package com.company.model.repository;

import com.company.model.database.IDB;
import com.company.model.entities.flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FlightRepository implements IFlightRepository {

    private final IDB db;

    public FlightRepository(IDB db) {
        this.db = db;
    }

    public ArrayList<flight> getAllFlights(){

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        ArrayList < flight > res = new ArrayList<>();

        try {

            con = db.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select * from flights");

            while(rs.next()){
                res.add(new flight(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
            }

            return res;


        } catch (Exception e){
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (Exception e){
                System.out.println(e);
            }
        }
        return null;
    }

    public void addNewFlight(flight newFlight) {

        Connection con = null;

        try {
            con = db.getConnection();

            String sql = "insert into flights values (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, newFlight.getTicketNum());
            st.setString(2, newFlight.getName());
            st.setString(3, newFlight.getDestination());
            st.setString(4, newFlight.getDate());
            st.setString(5, newFlight.getTime());
            st.setInt(6, newFlight.getPrice());


            st.execute();

        } catch (Exception e){
            System.out.println(e);
        } finally {
            try {
                con.close();
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }
}
