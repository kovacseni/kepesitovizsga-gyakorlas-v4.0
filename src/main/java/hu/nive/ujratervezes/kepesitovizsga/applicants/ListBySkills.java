package hu.nive.ujratervezes.kepesitovizsga.applicants;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListBySkills implements ApplicantListGenerator {

    @Override
    public List<Applicant> getListFromDatabase(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select first_name, last_name, skill from applicants;")) {

            return getList(rs);

        } catch (SQLException sqle) {
            throw new IllegalStateException("Can not get data.", sqle);
        }
    }

    private List<Applicant> getList(ResultSet rs) throws SQLException {
        List<Applicant> applicants = new ArrayList<>();
        while (rs.next()) {
            if (rs.getString("skill").length() == 3) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String skill = rs.getString("skill");

                applicants.add(new Applicant(firstName, lastName, skill));
            }
        }
        return applicants;
    }
}
