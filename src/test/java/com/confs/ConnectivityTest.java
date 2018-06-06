package com.confs;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class ConnectivityTest {
    @Test
    public void testAddingConference() throws SQLException, ClassNotFoundException {
        Conference conf = new Conference();
        conf.setName("Test Nazwa");
        conf.setDesc("Opis konferencji. Test.");
        conf.setDate("2018-07-01");
        conf.setTime("12:00");
        conf.setOwner(1);
        Connectivity con = new Connectivity();
        con.addConference(conf);
        Assert.assertTrue(true);
        //Assert.assertEquals("foo", "pilka");
    }

    @Test
    public void testDeletingConference() throws SQLException, ClassNotFoundException {
        Connectivity con = new Connectivity();
        con.deleteConference(2);
        Assert.assertTrue(true);
    }

    @Test
    public void testShowConference() throws SQLException, ClassNotFoundException {
        Connectivity con = new Connectivity();
        con.showConference(1);
        Assert.assertTrue(true);
    }

    @Test
    public void testUpdateConference() throws SQLException, ClassNotFoundException {
        Conference conf = new Conference();
        conf.setId(3);
        conf.setName("Funkiel nówka");
        conf.setDesc("Nie poganiaj mnie, bo tracę oddech. Szczura.");
        conf.setDate("2018-07-01");
        conf.setTime("12:00");
        conf.setOwner(2);
        Connectivity con = new Connectivity();
        con.updateConference(conf);
        Assert.assertTrue(true);
    }

    @Test
    public void testAddingOwner() throws SQLException, ClassNotFoundException {
        Owner owner = new Owner();
        owner.setName("Bolesław");
        owner.setSurname("Krzywousty");
        owner.setEmail("boleslaw.krzywousty@wp.pl");
        owner.setPhone("122-548-966");
        Connectivity con = new Connectivity();
        con.addOwner(owner);
        Assert.assertTrue(true);
    }

    @Test
    public void testUpdateOwner() throws SQLException, ClassNotFoundException {
        Owner owner = new Owner();
        owner.setId(2);
        owner.setName("Bolesław");
        owner.setSurname("Krzywousty");
        owner.setEmail("boleslaw.krzywousty@wp.pl");
        owner.setPhone("122-548-000");
        Connectivity con = new Connectivity();
        con.updateOwner(owner);
        Assert.assertTrue(true);
    }

    @Test
    public void testShowUser() throws SQLException, ClassNotFoundException {
        Connectivity con = new Connectivity();
        con.showUser("wojtek.gruszecki@gmail.com");
        Assert.assertTrue(true);
    }

    @Test
    public void testAddingUser() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setName("Kazimierz");
        user.setSurname("PrzerwaTetmajer");
        user.setEmail("kazimierz_tetmajer@protonmail.com");
        Connectivity con = new Connectivity();
        con.addUser(user);
        Assert.assertTrue(true);
    }

    @Test
    public void testUpdateUser() throws SQLException, ClassNotFoundException {
        User user = new User();
        user.setId(2);
        user.setName("Kazimierz");
        user.setSurname("Przerwa-Tetmajer");
        Connectivity con = new Connectivity();
        con.updateUser(user);
        Assert.assertTrue(true);
    }
}

