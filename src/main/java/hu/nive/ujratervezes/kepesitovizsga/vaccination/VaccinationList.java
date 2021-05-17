package hu.nive.ujratervezes.kepesitovizsga.vaccination;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class VaccinationList {

    private Map<LocalTime, Person> vaccinations = new TreeMap<>();
    private MetaData metaData;

    public Map<LocalTime, Person> getVaccinations() {
        return new TreeMap<>(vaccinations);
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void readFromFile(BufferedReader reader) throws IOException {
        loadMetaData(reader);
        skipTwoLines(reader);
        loadMap(reader);
    }

    private void loadMetaData(BufferedReader reader) throws IOException {
        String lineMetaData = reader.readLine().concat(reader.readLine());
        int index1 = lineMetaData.indexOf(",");
        int index2 = lineMetaData.indexOf("településre");
        int index3 = lineMetaData.indexOf(":");
        String postalCode = lineMetaData.substring(index1 - 4, index1);
        String townName = lineMetaData.substring(index1 + 2, index2 - 1);
        String dateString = lineMetaData.substring(index3 + 2);

        metaData = new MetaData(postalCode, townName, LocalDate.parse(dateString));
    }

    private void skipTwoLines(BufferedReader reader) throws IOException {
        reader.readLine();
        reader.readLine();
    }

    private void loadMap(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] temp = line.split(";");
            LocalTime time = LocalTime.parse(temp[0]);
            int age = Integer.parseInt(temp[3]);
            Person person;
            if (temp.length == 6) {
                person = new Person(temp[1], age, temp[4], temp[5], VaccinationType.NONE);
            } else {
                VaccinationType type = VaccinationType.valueOf(temp[6]);
                person = new Person(temp[1], age, temp[4], temp[5], type);
            }
            vaccinations.put(time, person);
        }
    }

    public List<Person> getPersonsMoreThanHundredYearsOld() {
        List<Person> moreThanHundredYearsOld = new ArrayList<>();
        for (Person p : vaccinations.values()) {
            if (p.getAge() > 100) {
                moreThanHundredYearsOld.add(p);
            }
        }
        return moreThanHundredYearsOld;
    }

    public List<Person> getAfternoonPersons() {
        List<Person> afternoonPersons = new ArrayList<>();
        for (LocalTime lt : vaccinations.keySet()) {
            if (lt.isAfter(LocalTime.NOON)) {
                afternoonPersons.add(vaccinations.get(lt));
            }
        }
        return afternoonPersons;
    }

    public boolean validateTaj() {
        StringBuilder sb = new StringBuilder();
        for (Person p : vaccinations.values()) {
            boolean valid = validate(p.getTaj());
            if (!valid) {
                sb.append(p.getTaj()).append(", ");
            }
        }
        if ("".equals(sb.toString())) {
            return true;
        } else {
            throw new IllegalArgumentException(sb.substring(0, sb.toString().length() - 2));
        }
    }

    private boolean validate(String taj) {
        int sum = 0;
        for (int i = 1; i <= taj.length() - 1; i += 2) {
            int digit = Integer.parseInt(taj.substring(i - 1, i));
            sum += digit * 3;
        }
        for (int i = 2; i < taj.length(); i += 2) {
            int digit = Integer.parseInt(taj.substring(i - 1, i));
            sum += digit * 7;
        }
        int remainder = sum % 10;
        int cdv = Integer.parseInt(taj.substring(taj.length() - 1));
        return remainder == cdv;
    }

    public String inviteExactPerson(LocalTime time) {
        StringBuilder sb = new StringBuilder("Kedves {nev}! Ön következik. Kérem, fáradjon be!");
        if (vaccinations.containsKey(time)) {
            Person person = vaccinations.get(time);
            return sb.toString().replace("{nev}", person.getName());
        } else {
            throw new IllegalArgumentException("Nem megfelelő a megadott időpont.");
        }
    }

    public Town getTown() {
        return new Town(metaData.getPostalCode(), metaData.getTownName());
    }

    public LocalDate getDateOfVaccination() {
        return metaData.getDate();
    }

    public Map<VaccinationType, Integer> getVaccinationStatistics() {
        Map<VaccinationType, Integer> statistics = new HashMap<>();
        for (Person p : vaccinations.values()) {
            VaccinationType type = p.getType();
            if (!statistics.containsKey(type)) {
                statistics.put(type, 1);
            } else {
                statistics.put(type, statistics.get(type) + 1);
            }
        }
        return statistics;
    }
}
