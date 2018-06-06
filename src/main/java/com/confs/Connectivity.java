package com.confs;

import java.sql.*;
import java.util.Properties;

public class Connectivity {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String databaseURL = "jdbc:mysql://localhost:3306/konferencje";
    String userName = "root";
    String password = "root";

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(databaseURL, userName, password);
        System.out.println("Connected to database.");
        return conn;
    }

    public void addConference(Conference conf) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO konferencje (tytul, opis, data, czas, wlasciciel) VALUES (?,?,?,?,?)");
        preparedStatement.setString(1, conf.getName());
        preparedStatement.setString(2, conf.getDesc());
        preparedStatement.setString(3, conf.getDate());
        preparedStatement.setString(4, conf.getTime());
        preparedStatement.setString(5, String.valueOf(conf.getOwner()));
        preparedStatement.executeUpdate();
        conn.close();
        System.out.println("Conference added.");
    }

    public void deleteConference(int id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM konferencje WHERE id = (?)");
        preparedStatement.setString(1, String.valueOf(id));
        preparedStatement.executeUpdate();
        conn.close();
        System.out.println("Conference deleted.");
    }

    public Conference showConference(int id) throws SQLException, ClassNotFoundException {
        Conference conf = new Conference();
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM konferencje WHERE id = (?)");
        preparedStatement.setString(1, String.valueOf(id));
        ResultSet rs = preparedStatement.executeQuery();

        if(rs.next()) {
            conf.setId(id);
            conf.setName(rs.getString("tytul"));
            conf.setDesc(rs.getString("opis"));
            conf.setDate(rs.getString("data"));
            conf.setTime(rs.getString("czas"));
            conf.setOwner(rs.getInt("wlasciciel"));
            System.out.println("Conference's data downloaded: " + conf.getName());
        } else {
            System.out.println("ERROR while conference's data downloading: id not found in database.");
        }

        conn.close();
        return conf;
    }

    void updateConference(Conference conf) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE konferencje SET tytul = \"" + conf.getName() + "\", opis = \"" + conf.getDesc() + "\", data = '" + conf.getDate() + "', czas = '" + conf.getTime() + "', wlasciciel = '" + conf.getOwner() + "' WHERE id = " + conf.getId());
        preparedStatement.executeUpdate();
        conn.close();
        System.out.println("Conference updated.");
    }

    public boolean addUser(User user) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM uzytkownicy WHERE email=(?)");
        preparedStatement.setString(1, user.getEmail());
        ResultSet rs = preparedStatement.executeQuery();

        if(!rs.next()) {
            preparedStatement = conn.prepareStatement("INSERT INTO uzytkownicy (imie, nazwisko, email) VALUES (?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
        } else {
            System.out.println("ERROR while user adding: email already used.");
            conn.close();
            return false;
        }

        conn.close();
        System.out.println("User added.");
        return true;
    }

    public void deleteUser(int id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM uzytkownicy WHERE id = (?)");
        preparedStatement.setString(1, String.valueOf(id));
        preparedStatement.executeUpdate();
        conn.close();
        System.out.println("User deleted.");
    }

    public void deleteUser(String email) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM uzytkownicy WHERE email = (?)");
        preparedStatement.setString(1, email);
        preparedStatement.executeUpdate();
        conn.close();
        System.out.println("User deleted.");
    }

    public User showUser(int id) throws SQLException, ClassNotFoundException {
        User user = new User();
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM uzytkownicy WHERE id = (?)");
        preparedStatement.setString(1, String.valueOf(id));
        ResultSet rs = preparedStatement.executeQuery();

        if(rs.next()) {
            user.setId(id);
            user.setName(rs.getString("imie"));
            user.setSurname(rs.getString("nazwisko"));
            user.setEmail(rs.getString("email"));
            System.out.println("User's data downloaded: " + user.getName());
        } else {
            System.out.println("ERROR while user's data downloading: id not found in database.");
        }

        conn.close();
        return user;
    }

    public User showUser(String email) throws SQLException, ClassNotFoundException {
        User user = new User();
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM uzytkownicy WHERE email = (?)");
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();

        if(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("imie"));
            user.setSurname(rs.getString("nazwisko"));
            user.setEmail(email);
            System.out.println("User's data downloaded: " + user.getName());
        } else {
            System.out.println("ERROR while user's data downloading: e-mail not found in database.");
        }

        conn.close();
        return user;
    }

    void updateUser(User user) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE uzytkownicy SET imie = \"" + user.getName() + "\", nazwisko = \"" + user.getSurname() + "\" WHERE id = " + user.getId());
        preparedStatement.executeUpdate();
        conn.close();
        System.out.println("User updated: " + user.getName() + " " + user.getSurname() + ".");
    }

    public boolean addOwner(Owner owner) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM wlasciciele WHERE email=(?)");
        preparedStatement.setString(1, owner.getEmail());
        ResultSet rs = preparedStatement.executeQuery();

        if(!rs.next()) {
            preparedStatement = conn.prepareStatement("INSERT INTO wlasciciele (imie, nazwisko, email, telefon) VALUES (?,?,?,?)");
            preparedStatement.setString(1, owner.getName());
            preparedStatement.setString(2, owner.getSurname());
            preparedStatement.setString(3, owner.getEmail());
            preparedStatement.setString(4, owner.getPhone());
            preparedStatement.executeUpdate();
        } else {
            System.out.println("ERROR while owner adding: email already used.");
            conn.close();
            return false;
        }

        conn.close();
        System.out.println("Owner added.");
        return true;
    }

    public void deleteOwner(int id) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM wlasciciele WHERE id = (?)");
        preparedStatement.setString(1, String.valueOf(id));
        preparedStatement.executeUpdate();
        conn.close();
        System.out.println("Owner deleted.");
    }

    public void deleteOwner(String email) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM wlasciciele WHERE email = (?)");
        preparedStatement.setString(1, email);
        preparedStatement.executeUpdate();
        conn.close();
        System.out.println("Owner deleted.");
    }

    public Owner showOwner(int id) throws SQLException, ClassNotFoundException {
        Owner owner = new Owner();
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM wlasciciele WHERE id = (?)");
        preparedStatement.setString(1, String.valueOf(id));
        ResultSet rs = preparedStatement.executeQuery();

        if(rs.next()) {
            owner.setId(id);
            owner.setName(rs.getString("imie"));
            owner.setSurname(rs.getString("nazwisko"));
            owner.setEmail(rs.getString("email"));
            owner.setPhone(rs.getString("telefon"));
            System.out.println("Owner's data downloaded: " + owner.getName());
        } else {
            System.out.println("ERROR while owner's data downloading: id not found in database.");
        }

        conn.close();
        return owner;
    }

    public Owner showOwner(String email) throws SQLException, ClassNotFoundException {
        Owner owner = new Owner();
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM wlasciciele WHERE email = (?)");
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();

        if(rs.next()) {
            owner.setId(rs.getInt("id"));
            owner.setName(rs.getString("imie"));
            owner.setSurname(rs.getString("nazwisko"));
            owner.setEmail(email);
            owner.setPhone(rs.getString("telefon"));
            System.out.println("Owner's data downloaded: " + owner.getName());
        } else {
            System.out.println("ERROR while owner's data downloading: e-mail not found in database.");
        }

        conn.close();
        return owner;
    }

    void updateOwner(Owner owner) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("UPDATE wlasciciele SET imie = \"" + owner.getName() + "\", nazwisko = \"" + owner.getSurname() + "\", telefon = '" + owner.getPhone() + "' WHERE id = " + owner.getId());
        preparedStatement.executeUpdate();
        conn.close();
        System.out.println("Owner updated: " + owner.getName() + " " + owner.getSurname() + ".");
    }

}
