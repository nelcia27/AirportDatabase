package airportPackage;
//import javafx.event.ActionEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class Main {
    private static Connection finalConn;
    private static int page;
    private JTable table1;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton addNewButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JButton refreshButton;
    private JButton RightButton;
    private JButton leftButton;
    private JLabel PageLabel;


    public Main() {
        comboBox1.addItem("Bilety");
        comboBox1.addItem("Bramy");
        comboBox1.addItem("Linie Lotnicze");
        comboBox1.addItem("Loty");
        comboBox1.addItem("Lotniska");
        comboBox1.addItem("Pasażerowie");
        comboBox1.addItem("Samoloty");
        comboBox1.addItem("Załogi");


        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entity = comboBox1.getSelectedItem().toString();
                String[] columns = new String[]{};
                Object[][] data = new Object[][]{};
                DefaultTableModel t1;
                switch (entity) {
                    case "Bilety":
                        List<Bilet> bilety = wypiszWolneBilety(finalConn, 1);
                        data = new Object[10][7];
                        columns = new String[]{
                                "Id", "Trasa", "Wylot", "Przylot", "Brama", "Linia", "Lotnisko"
                        };
                        Object[] bilet;
                        for (int i = 0; i < 10; i++) {
                            bilet = new Object[]{
                                    bilety.get(i).getId(),
                                    bilety.get(i).lot.getZ_do(),
                                    bilety.get(i).lot.getData_wylotu().toString(),
                                    bilety.get(i).lot.getData_przylotu().toString(),
                                    bilety.get(i).lot.getBrama(),
                                    bilety.get(i).lot.getLiniaLotnicza(),
                                    bilety.get(i).lot.getSkomunikowaneLotnisko()
                            };
                            data[i] = bilet;
                        }
                        break;
                    case "Bramy":
                        List<Brama> bramy = wypiszBramy(finalConn, 1);
                        data = new Object[10][1];
                        columns = new String[]{
                                "Numer"
                        };
                        Object[] brama;
                        for (int i = 0; i < 10; i++) {
                            brama = new Object[]{
                                    bramy.get(i).getNumer()
                            };
                            data[i] = brama;
                        }
                        break;
                    case "Linie Lotnicze":
                        List<LiniaLotnicza> linie = wypiszLinieLotnicze(finalConn, 1);
                        data = new Object[10][1];
                        columns = new String[]{
                                "Nazwa"
                        };
                        Object[] linia;
                        for (int i = 0; i < 10; i++) {
                            linia = new Object[]{
                                    linie.get(i).getNazwa()
                            };
                            data[i] = linia;
                        }
                        break;
                    case "Loty":

                        List<Lot> loty = wypiszLoty(finalConn, 1);
                        data = new Object[10][6];
                        columns = new String[]{
                                "Trasa", "Wylot", "Przylot", "Brama", "Linia", "Lotnisko"
                        };
                        Object[] lot;
                        for (int i = 0; i < 10; i++) {
                            lot = new Object[]{
                                    loty.get(i).getZ_do(),
                                    loty.get(i).getData_wylotu().toString(),
                                    loty.get(i).getData_przylotu().toString(),
                                    loty.get(i).getBrama(),
                                    loty.get(i).getLiniaLotnicza(),
                                    loty.get(i).getSkomunikowaneLotnisko()
                            };
                            data[i] = lot;
                        }


                        break;
                    case "Lotniska":
                        List<Lotnisko> lotniska = wypiszLotniska(finalConn, 1);
                        data = new Object[10][3];
                        columns = new String[]{
                                "Nazwa", "Miasto", "Kraj"
                        };
                        Object[] lotnisko;
                        for (int i = 0; i < 10; i++) {
                            lotnisko = new Object[]{
                                    lotniska.get(i).getNazwa(),
                                    lotniska.get(i).getMiasto(),
                                    lotniska.get(i).getKraj()

                            };
                            data[i] = lotnisko;
                        }
                        break;
                    case "Pasażerowie":
                        List<Pasazer> pasazerowie = wypiszPasazerow(finalConn, 1);
                        data = new Object[10][5];
                        columns = new String[]{
                                "Pesel", "Imie", "Nazwisko", "Bilet", "Waga bagażu"
                        };
                        Object[] pasazer;
                        for (int i = 0; i < 10; i++) {
                            pasazer = new Object[]{
                                    pasazerowie.get(i).getPesel(),
                                    pasazerowie.get(i).getImie(),
                                    pasazerowie.get(i).getNazwisko(),
                                    pasazerowie.get(i).getId_biletu(),
                                    pasazerowie.get(i).getWaga_bagazu()
                            };
                            data[i] = pasazer;
                        }
                        break;
                    case "Samoloty":
                        List<Samolot> samoloty = wypiszSamoloty(finalConn, 1);
                        data = new Object[10][2];
                        columns = new String[]{
                                "Ladowność", "Liczba miejsc"
                        };
                        Object[] samolot;
                        for (int i = 0; i < 10; i++) {
                            samolot = new Object[]{
                                    samoloty.get(i).getLadownosc(),
                                    samoloty.get(i).getLiczbaMiejsc()

                            };
                            data[i] = samolot;
                        }
                        break;
                    case "Załogi":
                        List<Osoba> zalogi = wypiszZalogi(finalConn, 1);
                        data = new Object[10][4];
                        columns = new String[]{
                                "Pesel", "Imię", "Nazwisko", "Rola"
                        };
                        Object[] osoba;
                        for (int i = 0; i < 10; i++) {
                            osoba = new Object[]{
                                    zalogi.get(i).getPesel(),
                                    zalogi.get(i).getImie(),
                                    zalogi.get(i).getNazwisko(),
                                    zalogi.get(i).getRola()
                            };
                            data[i] = osoba;
                        }
                        break;
                    default:
                        break;

                }
                t1 = new DefaultTableModel(data, columns);
                table1.setModel(t1);
            }
        });


    }

    public static void main(String[] args) {
//        JFrame f=new JFrame("Lotnisko");
////        final JTable tf=new JTable();
////        tf.setBounds(50,50, 150,20);
////        JButton okButton=new JButton("OK");
////        okButton.setBounds(50,100,95,30);
//        f.setContentPane(new mainForm().panel1);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.pack();
//        f.setVisible(true);


        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "inf136803");
        connectionProps.put("password", "inf136803");
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//admlab2.cs.put.poznan.pl:1521/" + "dblab02_students.cs.put.poznan.pl", connectionProps);
            System.out.println("Połączono z bazą danych");

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "nie udało się połączyć z bazą danych", ex);
            System.exit(-1);
        }

        //tu wywołac funkcje realizującą konkretne zadanie np
        finalConn = conn;
        JFrame frame = new JFrame("Aplikacja Lotniska");
//        frame.setSize(1000, 600);
        frame.setContentPane(new Main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Window Closed");
                try {
                    finalConn.close();
                    System.out.println("Odlaczono od bazy danych");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

//        okButton.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent e) {
//                List<Lotnisko> lista=wypiszLotniska(finalConn,3);
//                //tf.setTableHeader();
//            }
//        });


//        try {
//            conn.close();
//            System.out.println("Odlaczono od bazy danych");
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public static List<Lotnisko> wypiszLotniska(Connection conn, int part) {
        Statement stmt = null;
        ResultSet rs = null;
        int stop_number = part * 10;
        int start_number = stop_number - 10;
        List<Lotnisko> lotniska = new ArrayList<Lotnisko>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select nazwa, miasto, kraj from lotniska order by nazwa asc");
            rs.relative(start_number);
            while (rs.next() && start_number < stop_number) {
                Lotnisko lotnisko = new Lotnisko(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
                lotniska.add(lotnisko);
                start_number += 1;
            }
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
        return lotniska;
    }

    public static List<LiniaLotnicza> wypiszLinieLotnicze(Connection conn, int part) {
        Statement stmt = null;
        ResultSet rs = null;
        int stop_number = part * 10;
        int start_number = stop_number - 10;
        List<LiniaLotnicza> linieLotnicze = new ArrayList<LiniaLotnicza>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select nazwa from linie_lotnicze order by nazwa asc");
            rs.relative(start_number);
            while (rs.next() && start_number < stop_number) {
                LiniaLotnicza liniaLotnicza = new LiniaLotnicza(rs.getString(1));
                linieLotnicze.add(liniaLotnicza);
                start_number += 1;
            }
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
        return linieLotnicze;
    }

    public static List<Brama> wypiszBramy(Connection conn, int part) {
        Statement stmt = null;
        ResultSet rs = null;
        int stop_number = part * 10;
        int start_number = stop_number - 10;
        List<Brama> bramy = new ArrayList<Brama>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select numer from bramy order by numer asc");
            rs.relative(start_number);
            while (rs.next() && start_number < stop_number) {
                Brama brama = new Brama(rs.getInt(1));
                bramy.add(brama);
                start_number += 1;
            }
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
        return bramy;
    }

    public static List<Samolot> wypiszSamoloty(Connection conn, int part) {
        Statement stmt = null;
        ResultSet rs = null;
        int stop_number = part * 10;
        int start_number = stop_number - 10;
        List<Samolot> samoloty = new ArrayList<Samolot>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select ladownosc, liczba_miejsc from samoloty order by liczba_miejsc asc");
            rs.relative(start_number);
            while (rs.next() && start_number < stop_number) {
                Samolot samolot = new Samolot(rs.getInt(1),
                        rs.getInt(2));
                samoloty.add(samolot);
                start_number += 1;
            }
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
        return samoloty;
    }

    public static List<Pasazer> wypiszPasazerow(Connection conn, int part) {
        Statement stmt = null;
        ResultSet rs = null;
        int stop_number = part * 10;
        int start_number = stop_number - 10;
        List<Pasazer> pasazerowie = new ArrayList<Pasazer>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select p.pesel, p.imie, p.nazwisko, b.id as \"id biletu\", g.waga as \"waga bagazu\" from pasazer p" +
                    "join bilety b on p.pesel=b.pasazer_pesel join bagaze g on b.id=g.bilet_id order by p.nazwisko asc");
            rs.relative(start_number);
            while (rs.next() && start_number < stop_number) {
                Pasazer pasazer = new Pasazer(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
                start_number += 1;
                pasazerowie.add(pasazer);
            }
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
        return pasazerowie;
    }

    public static List<Osoba> wypiszZalogi(Connection conn, int part) {
        Statement stmt = null;
        ResultSet rs = null;
        int stop_number = part * 10;
        int start_number = stop_number - 10;
        List<Osoba> osoby = new ArrayList<Osoba>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select o.pesel, o.imie, o.nazwisko, o.rola, z.loty_id from zalogi z join osoby o on z.osoby_pesel=o.pesel  order by z.loty_id, o.nazwisko asc");
            rs.relative(start_number);
            while (rs.next() && start_number < stop_number) {
                Osoba osoba = new Osoba(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                start_number += 1;
                osoby.add(osoba);
            }
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
        return osoby;
    }

    public static List<Bilet> wypiszWolneBilety(Connection conn, int part) {
        Statement stmt = null;
        ResultSet rs = null;
        int stop_number = part * 10;
        int start_number = stop_number - 10;
        List<Bilet> bilety = new ArrayList<Bilet>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from bilety b join loty l on b.lot_id=l.id where b.pasazer_pesel is NULL");
            rs.relative(start_number);
            while (rs.next() && start_number < stop_number) {
                Bilet bilet = new Bilet(rs.getString(1));
                bilet.lot = new Lot(rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getString(7),
                        rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
                bilety.add(bilet);
                start_number += 1;
            }
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
        return bilety;
    }

    public static List<Lot> wypiszLoty(Connection conn, int part) {
        Statement stmt = null;
        ResultSet rs = null;
        int stop_number = part * 10;
        int start_number = stop_number - 10;
        List<Lot> loty = new ArrayList<Lot>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from loty order by data_wylotu asc");
            rs.relative(start_number);
            while (rs.next() && start_number < stop_number) {
                Lot lot = new Lot(rs.getString(1),
                        rs.getDate(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
                start_number += 1;
                loty.add(lot);
            }
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
        return loty;
    }

    public static void usunLotnisko(Connection conn, String nazwa) {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        int changes = 0;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select id_lotu from loty where lotnisko_nazwa=" + nazwa);
            while (rs.next()) {
                rs1 = stmt.executeQuery("select id from bagaze where bilet_id=" + rs.getString(1));
                while (rs1.next()) {
                    stmt.executeQuery("delete from bagaze where bilet_id=" + rs1.getString(1));
                }
                stmt.executeQuery("delete from bilety where bilet_id=" + rs.getString(1));
                stmt.executeQuery("delete from loty where id=" + rs.getString(1));
            }
            String query = "delete from lotniska where nazwa=" + nazwa;
            changes = stmt.executeUpdate(query);
            //można tak sprawdzac czy jest poprawnie usunięte
            System.out.println("Usunieto " + changes + " lotnisk/lotnisko.");
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void usunLinieLotnicza(Connection conn, String nazwa) {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        int changes = 0;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select id_lotu from loty where linia_lotnicza_nazwa=" + nazwa);
            while (rs.next()) {
                rs1 = stmt.executeQuery("select id from bagaze where bilet_id=" + rs.getString(1));
                while (rs1.next()) {
                    stmt.executeQuery("delete from bagaze where bilet_id=" + rs1.getString(1));
                }
                stmt.executeQuery("delete from bilety where bilet_id=" + rs.getString(1));
                stmt.executeQuery("delete from loty where id=" + rs.getString(1));
            }
            String query = "delete from linie_lotnicze where nazwa=" + nazwa;
            changes = stmt.executeUpdate(query);
            //można tak sprawdzac czy jest poprawnie usunięte
            System.out.println("Usunieto " + changes + " linie lotnicza.");
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void usunBrame(Connection conn, int numer) {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        int changes = 0;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select id_lotu from loty where brama_numer=" + numer);
            while (rs.next()) {
                rs1 = stmt.executeQuery("select id from bagaze where bilet_id=" + rs.getString(1));
                while (rs1.next()) {
                    stmt.executeQuery("delete from bagaze where bilet_id=" + rs1.getString(1));
                }
                stmt.executeQuery("delete from bilety where bilet_id=" + rs.getString(1));
                stmt.executeQuery("delete from loty where id=" + rs.getString(1));
            }
            String query = "delete from samoloty where numer=" + numer;
            changes = stmt.executeUpdate(query);
            //można tak sprawdzac czy jest poprawnie usunięte
            System.out.println("Usunieto " + changes + " brame/bramy.");
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void usunSamolot(Connection conn, int ladownosc, int liczba_miejsc) {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs2 = stmt.executeQuery("select id from samoloty where ladownosc=" + ladownosc + " and liczba_miejsc=" + liczba_miejsc);
            while (rs2.next()) {
                rs = stmt.executeQuery("select id_lotu from loty where brama_numer=" + rs2.getString(1));
                while (rs.next()) {
                    rs1 = stmt.executeQuery("select id from bagaze where bilet_id=" + rs.getString(1));
                    while (rs1.next()) {
                        stmt.executeQuery("delete from bagaze where bilet_id=" + rs1.getString(1));
                    }
                    stmt.executeQuery("delete from bilety where bilet_id=" + rs.getString(1));
                    stmt.executeQuery("delete from loty where id=" + rs.getString(1));
                }
                stmt.executeUpdate("delete from samoloty where id=" + rs2.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void usunPasazera(Connection conn, String pesel) {
        Statement stmt = null;
        ResultSet rs = null;
        int changes = 0;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select id from bilety where pasazer_pesel=" + pesel);
            while (rs.next()) {
                stmt.executeQuery("delete from bagaze where bilet_id=" + rs.getString(1));
                stmt.executeQuery("update bilety set pasazer_pesel=\"NULL\" where id=" + rs.getString(1));
            }
            String query = "delete from pasazerowie where pesel=" + pesel;
            changes = stmt.executeUpdate(query);
            //można tak sprawdzac czy jest poprawnie usunięte
            System.out.println("Usunieto " + changes + " pasazera/pasazerow.");
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void usunZaloge(Connection conn, String lot_id) {
        Statement stmt = null;
        int changes = 0;
        try {
            stmt = conn.createStatement();
            String query = "delete from Zalogi where lot_id=" + lot_id;
            changes = stmt.executeUpdate(query);
            //można tak sprawdzac czy jest poprawnie usunięte
            System.out.println("Usunieto " + changes + " zaloge/zalogi.");
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void usunOsobeZZalogi(Connection conn, String pesel) {
        Statement stmt = null;
        int changes = 0;
        try {
            stmt = conn.createStatement();
            String query = "delete from zalogi where osoby_pesel=" + pesel;
            changes = stmt.executeUpdate(query);
            //można tak sprawdzac czy jest poprawnie usunięte
            System.out.println("Usunieto " + changes + " osobe/osob.");
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void usunLot(Connection conn, String id_lotu) {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        int changes = 0;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select id from bilety where id_lotu=" + id_lotu);
            while (rs.next()) {
                rs1 = stmt.executeQuery("select id from bagaze where bilet_id=" + rs.getString(1));
                while (rs1.next()) {
                    stmt.executeQuery("delete from bagaze where bilet_id=" + rs1.getString(1));
                }
                stmt.executeQuery("delete from bilety where bilet_id=" + rs.getString(1));
            }
            String query = "delete from loty where id_lotu=" + id_lotu;
            changes = stmt.executeUpdate(query);
            //można tak sprawdzac czy jest poprawnie usunięte
            System.out.println("Usunieto " + changes + " lot/lotow.");
        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void dodajLotnisko(Connection conn, Lotnisko lotnisko) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into lotniska(nazwa,miasto,kraj) values(?, ?, ?)");
            stmt.setString(1, lotnisko.getNazwa());
            stmt.setString(2, lotnisko.getMiasto());
            stmt.setString(3, lotnisko.getKraj());
            stmt.executeUpdate();


        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void dodajLinieLotnicza(Connection conn, LiniaLotnicza liniaLotnicza) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into linia_lotnicza(nazwa) values(?)");
            stmt.setString(1, liniaLotnicza.getNazwa());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void dodajBrame(Connection conn, Brama brama) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into bramy(numer) values(?)");
            stmt.setInt(1, brama.getNumer());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void dodajSamolot(Connection conn, Samolot samolot) {
        PreparedStatement stmt = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        try {
            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt1.executeQuery("select max(id) from samoloty");
            int liczba = parseInt(rs.getString(1)) + 1;
            stmt = conn.prepareStatement("insert into samoloty(id,ladownosc,liczba_miejsc) values(?, ?, ?)");
            stmt.setString(1, String.valueOf(liczba));
            stmt.setInt(2, samolot.getLadownosc());
            stmt.setInt(3, samolot.getLiczbaMiejsc());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void dodajOsobe(Connection conn, Osoba osoba) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into osoby(pesel,imie,nazwisko,rola) values(?, ?, ?, ?)");
            stmt.setString(1, osoba.getPesel());
            stmt.setString(2, osoba.getImie());
            stmt.setString(3, osoba.getNazwisko());
            stmt.setString(4, osoba.getRola());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void dodajZaloge(Connection conn, Zaloga zaloga, String id_lotu) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into zalogi(pesel_osoby,loty_id) values(?, ?)");
            for (int i = 0; i < zaloga.getCzlonkowie().size(); i++) {
                stmt.setString(1, zaloga.getCzlonkowie().get(i).getPesel());
                stmt.setString(2, id_lotu);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    //automatycznie generuje juz wolne bilety na ten lot
    public static void dodajLot(Connection conn, Lot lot) {
        PreparedStatement stmt = null;
        Statement stmt1 = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        try {
            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            cstmt = conn.prepareCall("{call generowanie boletow(?)}");
            rs = stmt1.executeQuery("select max(id) from loty");
            int liczba = parseInt(rs.getString(1)) + 1;
            cstmt.setString(1, String.valueOf(liczba));
            stmt = conn.prepareStatement("insert into loty(id,data_wylotu,data_przylotu,rodzaj,brama_numer,linia_lotnicza_nazwa,lotnisko_nazwa,samolot_id) values(?,?,?,?,?,?,?,?)");
            stmt.setString(1, String.valueOf(liczba));
            stmt.setDate(2, lot.getData_wylotu());
            stmt.setDate(3, lot.getData_przylotu());
            stmt.setString(4, lot.getZ_do());
            stmt.setInt(5, lot.getBrama());
            stmt.setString(6, lot.getLiniaLotnicza());
            stmt.setString(7, lot.getSkomunikowaneLotnisko());
            stmt.setString(8, lot.getSamolot());
            stmt.executeUpdate();
            cstmt.execute();

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void dodajPasazera(Connection conn, Pasazer pasazer) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into pasazerowie(pesel,imie,nazwisko) values(?, ?, ?)");
            stmt.setString(1, pasazer.getPesel());
            stmt.setString(2, pasazer.getImie());
            stmt.setString(3, pasazer.getNazwisko());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    public static void dodajBagaz(Connection conn, Bagaz bagaz) {
        PreparedStatement stmt = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        try {
            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt1.executeQuery("select max(id) from bagaze");
            int liczba = parseInt(rs.getString(1)) + 1;
            stmt = conn.prepareStatement("insert into bagaze(id,waga,bilet_id) values(?, ?, ?)");
            stmt.setString(1, String.valueOf(liczba));
            stmt.setInt(2, bagaz.getWaga());
            stmt.setString(3, bagaz.getBilet_id());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Bład wykonania polecenia" + ex.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) { /* kod obsługi */ }
            }
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 5, new Insets(0, 0, 0, 0), -1, -1));
        comboBox1 = new JComboBox();
        panel1.add(comboBox1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addNewButton = new JButton();
        addNewButton.setText("Add New");
        panel1.add(addNewButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modifyButton = new JButton();
        modifyButton.setText("Modify");
        panel1.add(modifyButton, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        refreshButton = new JButton();
        refreshButton.setText("Refresh");
        panel1.add(refreshButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RightButton = new JButton();
        RightButton.setText(">");
        panel1.add(RightButton, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(20, -1), null, null, 0, false));
        leftButton = new JButton();
        leftButton.setText("<");
        panel1.add(leftButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(20, -1), null, null, 0, false));
        PageLabel = new JLabel();
        PageLabel.setText("1/2");
        panel1.add(PageLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(20, -1), null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        panel1.add(deleteButton, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        scrollPane1.setViewportView(table1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    //te funkcje nie sa implementowane bo działaja na zasadzie usun rekord i wstaw nowy ze zmiana
    //===========================================================================================
    //modyfikuj lotnisko

    //modyfikuj linie lotnicza

    //modyfikuj brame

    //modyfikuj samolot

    //zajmij bilet

    //modyfikuj pasazera

    //modyfikuj bilet

    //modyfikuj lot
}
