package brizplus;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataForTests {
    private final String USERNAME = "postgres";
    private final String PASSWORD = "yCsrC5W59dX";
    private final String URL = "jdbc:postgresql://10.1.0.27:5432/lsd";
    private final long ID = 32;
    private final String PATIENT = "Петрова Анна Ивановна";
    private final String XDATE = "21102022";
    private final String QUERY_TO_PIM_INDIVIDUAL = "SELECT * FROM public.pim_individual WHERE id=";
    private final String QUERY_TO_ERC = "SELECT * FROM erc.erc WHERE individ_id=";
    private int randAge;
    private String fullBirthDateNameSnilsErcFromDB;
    private Long listSnils;
    private String oms;

    public String getDataFromDB() throws ClassNotFoundException, SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            System.out.println("connect");

            ResultSet resultPimIndividual = statement.executeQuery(QUERY_TO_PIM_INDIVIDUAL + ID);
            while (resultPimIndividual.next()) {
                String birthDateInBase = resultPimIndividual.getString("birth_dt");
                String birthDate = parseBirthDate(birthDateInBase);
                String name = resultPimIndividual.getString("name");
                String patrName = resultPimIndividual.getString("patr_name");
                String surname = resultPimIndividual.getString("surname");
                listSnils = resultPimIndividual.getLong("list_snils");
                String listOmsDoc = resultPimIndividual.getString("list_oms_doc");
                String omsNum = parseOMS(listOmsDoc);
                fullBirthDateNameSnilsErcFromDB = birthDate + " "
                        + name + " "
                        + patrName + " "
                        + surname + " "
                        + listSnils + " "
                        + omsNum;
            }
            ResultSet resultErc = statement
                    .executeQuery(QUERY_TO_ERC + ID);
            while (resultErc.next()) {
                long ercNumber = resultErc.getLong("erc_num");
                fullBirthDateNameSnilsErcFromDB = fullBirthDateNameSnilsErcFromDB + " " + ercNumber;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fullBirthDateNameSnilsErcFromDB;
    }

    public String getSnils() {
        String snils = listSnils.toString();
        return snils;
    }

    public String parseOMS(String listOmsDoc) {
        String regEx = "\\d{16}";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(listOmsDoc);
        while (m.find()) {
            oms = m.group();
        }
        return oms;
    }

    public String parseBirthDate(String birthDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String str = formatter.format(date);
            return str;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String checkDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public String getXDate() {
        return XDATE;
    }

    public String getGestAge() {
        randAge = (1 + (int) (Math.random() * 42));
        String randomGesAge = Integer.toString(randAge);
        return randomGesAge;
    }

    public String getPatient() {
        return PATIENT;
    }
}
