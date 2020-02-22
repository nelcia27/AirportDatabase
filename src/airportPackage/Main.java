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
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class Main extends Component {
    public static Connection finalConn;
    private static int page;
    private JTable table1;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton addNewButton;
    private JButton USUŃButton;
    private JButton MODYFIKUJButton;
    private JButton refreshButton;
    private JButton wyszukajButton;
    private JComboBox columnacomboBox;
    private JTextField wyszukajtextField1;


    public Main() {
        comboBox1.addItem("BILETY");
        comboBox1.addItem("BRAMY");
        comboBox1.addItem("LINIE LOTNICZE");
        comboBox1.addItem("LOTY");
        comboBox1.addItem("LOTNISKA");
        comboBox1.addItem("PASAŻEROWIE");
        comboBox1.addItem("PRACOWNICY");
        comboBox1.addItem("SAMOLOTY");
        comboBox1.addItem("ZAŁOGI");

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entity = comboBox1.getSelectedItem().toString();
                String[] columns = new String[]{};
                Object[][] data = new Object[][]{};
                DefaultTableModel t1;
                switch (entity) {
                    case "BILETY":
                        List<Bilet> bilety = wypiszWolneBilety(finalConn);
                        data = new Object[bilety.size()][7];
                        columns = new String[]{
                                "ID", "RODZAJ", "WYLOT", "PRZYLOT", "BRAMA", "LINIA LOTNICZA", "LOTNISKO"
                        };
                        Object[] bilet;
                        for (int i = 0; i < bilety.size(); i++) {
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
                    case "BRAMY":
                        List<Brama> bramy = wypiszBramy(finalConn);
                        data = new Object[bramy.size()][1];
                        columns = new String[]{
                                "NUMER"
                        };
                        Object[] brama;
                        for (int i = 0; i < bramy.size(); i++) {
                            brama = new Object[]{
                                    bramy.get(i).getNumer()
                            };
                            data[i] = brama;
                        }
                        break;
                    case "LINIE LOTNICZE":
                        List<LiniaLotnicza> linie = wypiszLinieLotnicze(finalConn);
                        data = new Object[linie.size()][1];
                        columns = new String[]{
                                "NAZWA"
                        };
                        Object[] linia;
                        for (int i = 0; i < linie.size(); i++) {
                            linia = new Object[]{
                                    linie.get(i).getNazwa()
                            };
                            data[i] = linia;
                        }
                        break;
                    case "LOTY":
                        List<Lot> loty = wypiszLoty(finalConn);
                        data = new Object[loty.size()][7];
                        columns = new String[]{
                                "ID", "RODZAJ", "WYLOT", "PRZYLOT", "BRAMA", "LINIA LOTNICZA", "LOTNISKO"
                        };
                        Object[] lot;
                        for (int i = 0; i < loty.size(); i++) {
                            lot = new Object[]{
                                    loty.get(i).getId_lotu(),
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
                    case "LOTNISKA":
                        List<Lotnisko> lotniska = wypiszLotniska(finalConn);
                        data = new Object[lotniska.size()][3];
                        columns = new String[]{
                                "NAZWA", "MIASTO", "KRAJ"
                        };
                        Object[] lotnisko;
                        for (int i = 0; i < lotniska.size(); i++) {
                            lotnisko = new Object[]{
                                    lotniska.get(i).getNazwa(),
                                    lotniska.get(i).getMiasto(),
                                    lotniska.get(i).getKraj()
                            };
                            data[i] = lotnisko;
                        }
                        break;
                    case "PASAŻEROWIE":
                        List<Pasazer> pasazerowie = wypiszPasazerow(finalConn);
                        data = new Object[pasazerowie.size()][5];
                        columns = new String[]{
                                "PESEL", "IMIĘ", "NAZWISKO", "ID BILETU", "WAGA BAGAŻU"
                        };
                        Object[] pasazer;
                        for (int i = 0; i < pasazerowie.size(); i++) {
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
                    case "PRACOWNICY":
                        List<Osoba> osoby = wypiszOsoby(finalConn);
                        data = new Object[osoby.size()][5];
                        columns = new String[]{
                                "PESEL", "IMIĘ", "NAZWISKO", "ROLA", "ID LOTÓW"
                        };
                        Object[] osoba;
                        for (int i = 0; i < osoby.size(); i++) {
                            osoba = new Object[]{
                                    osoby.get(i).getPesel(),
                                    osoby.get(i).getImie(),
                                    osoby.get(i).getNazwisko(),
                                    osoby.get(i).getRola(),
                                    osoby.get(i).getId_lotu()
                            };
                            data[i] = osoba;
                        }
                        break;
                    case "SAMOLOTY":
                        List<Samolot> samoloty = wypiszSamoloty(finalConn);
                        data = new Object[samoloty.size()][2];
                        columns = new String[]{
                                "ŁADOWNOŚĆ", "LICZBA MIEJSC"
                        };
                        Object[] samolot;
                        for (int i = 0; i < samoloty.size(); i++) {
                            samolot = new Object[]{
                                    samoloty.get(i).getLadownosc(),
                                    samoloty.get(i).getLiczbaMiejsc()

                            };
                            data[i] = samolot;
                        }
                        break;
                    case "ZAŁOGI":
                        List<Osoba> zalogi = wypiszZalogi(finalConn);
                        data = new Object[zalogi.size()][5];
                        columns = new String[]{
                                "PESEL", "IMIĘ", "NAZWISKO", "ROLA", "ID LOTU"
                        };
                        Object[] osoba1;
                        for (int i = 0; i < zalogi.size(); i++) {
                            osoba = new Object[]{
                                    zalogi.get(i).getPesel(),
                                    zalogi.get(i).getImie(),
                                    zalogi.get(i).getNazwisko(),
                                    zalogi.get(i).getRola(),
                                    zalogi.get(i).getId_lotu()
                            };
                            data[i] = osoba;
                        }
                        break;
                    default:
                        break;
                }
                t1 = new DefaultTableModel(data, columns);
                table1.setModel(t1);

                switch (entity) {
                    case "BILETY":
                        columnacomboBox.removeAllItems();
                        columnacomboBox.addItem("ID");
                        columnacomboBox.addItem("RODZAJ");
                        columnacomboBox.addItem("WYLOT");
                        columnacomboBox.addItem("PRZYLOT");
                        columnacomboBox.addItem("BRAMA");
                        columnacomboBox.addItem("LINIA LOTNICZA");
                        columnacomboBox.addItem("LOTNISKO");
                        break;
                    case "BRAMY":
                        columnacomboBox.removeAllItems();
                        columnacomboBox.addItem("NUMER");
                        break;
                    case "LINIE LOTNICZE":
                        columnacomboBox.removeAllItems();
                        columnacomboBox.addItem("NAZWA");
                        break;
                    case "LOTY":
                        columnacomboBox.removeAllItems();
                        columnacomboBox.addItem("ID");
                        columnacomboBox.addItem("RODZAJ");
                        columnacomboBox.addItem("WYLOT");
                        columnacomboBox.addItem("PRZYLOT");
                        columnacomboBox.addItem("BRAMA");
                        columnacomboBox.addItem("LINIA LOTNICZA");
                        columnacomboBox.addItem("LOTNISKO");
                        break;
                    case "LOTNISKA":
                        columnacomboBox.removeAllItems();
                        columnacomboBox.addItem("NAZWA");
                        columnacomboBox.addItem("KRAJ");
                        columnacomboBox.addItem("MIASTO");
                        break;
                    case "PASAŻEROWIE":
                        columnacomboBox.removeAllItems();
                        columnacomboBox.addItem("PESEL");
                        columnacomboBox.addItem("IMIĘ");
                        columnacomboBox.addItem("NAZWISKO");
                        break;
                    case "PRACOWNICY":
                        columnacomboBox.removeAllItems();
                        columnacomboBox.addItem("IMIĘ");
                        columnacomboBox.addItem("NAZWISKO");
                        columnacomboBox.addItem("PESEL");
                        columnacomboBox.addItem("ROLA");
                        break;
                    case "SAMOLOTY":
                        columnacomboBox.removeAllItems();
                        columnacomboBox.addItem("ŁADOWNOŚĆ");
                        columnacomboBox.addItem("LICZBA MIEJSC");
                        break;
                    case "ZAŁOGI":
                        columnacomboBox.removeAllItems();
                        columnacomboBox.addItem("ID LOTU");
                        columnacomboBox.addItem("IMIĘ");
                        columnacomboBox.addItem("NAZWISKO");
                        columnacomboBox.addItem("PESEL");
                        columnacomboBox.addItem("ROLA");
                        break;

                }
            }
        });

        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entity = comboBox1.getSelectedItem().toString();
                switch (entity) {
                    case "BILETY":
                        JOptionPane.showMessageDialog(Main.this, "Nie można ręcznie dodawać biletów! Są one generowane automatycznie po dodaniu lotu.", "Błąd", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "BRAMY":
                        AddNewBramy dialog1 = new AddNewBramy(Main.this);
                        dialog1.setVisible(true);
                        break;
                    case "LINIE LOTNICZE":
                        AddNewLinia dialog2 = new AddNewLinia(Main.this);
                        dialog2.setVisible(true);
                        break;
                    case "LOTY":
                        AddNewLot dialog3 = new AddNewLot(Main.this);
                        dialog3.setVisible(true);
                        break;
                    case "LOTNISKA":
                        AddNewLotnisko dialog4 = new AddNewLotnisko(Main.this);
                        dialog4.setVisible(true);
                        break;
                    case "PASAŻEROWIE":
                        AddNewPasazer dialog5 = new AddNewPasazer(Main.this);
                        dialog5.setVisible(true);
                        break;
                    case "PRACOWNICY":
                        AddNewOsoba dialog6 = new AddNewOsoba(Main.this);
                        dialog6.setVisible(true);
                        break;
                    case "SAMOLOTY":
                        AddNewSamoloty dialog7 = new AddNewSamoloty(Main.this);
                        dialog7.setVisible(true);
                        break;
                    case "ZAŁOGI":
                        AddNewZaloga dialog8 = new AddNewZaloga(Main.this);
                        dialog8.setVisible(true);
                        break;
                }
            }
        });

        USUŃButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entity = comboBox1.getSelectedItem().toString();
                switch (entity) {
                    case "BILETY":
                        JOptionPane.showMessageDialog(Main.this, "Nie można ręcznie usuwać biletów! Są automatycznie kasowane przy usunięciu lotu.", "Błąd", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "BRAMY":
                        int wybrane1 = table1.getSelectedRow();
                        usunBrame(finalConn, Integer.parseInt(table1.getModel().getValueAt(wybrane1, 0).toString()));
                        break;
                    case "LINIE LOTNICZE":
                        int wybrane2 = table1.getSelectedRow();
                        usunLinieLotnicza(finalConn, table1.getModel().getValueAt(wybrane2, 0).toString());
                        break;
                    case "LOTY":
                        int wybrane3 = table1.getSelectedRow();
                        usunLot(finalConn, table1.getModel().getValueAt(wybrane3, 0).toString());
                        break;
                    case "LOTNISKA":
                        int wybrane4 = table1.getSelectedRow();
                        usunLotnisko(finalConn, table1.getModel().getValueAt(wybrane4, 0).toString());
                        break;
                    case "PASAŻEROWIE":
                        int wybrane5 = table1.getSelectedRow();
                        usunPasazera(finalConn, table1.getModel().getValueAt(wybrane5, 0).toString());
                        break;
                    case "PRACOWNICY":
                        int wybrane6 = table1.getSelectedRow();
                        usunOsobeZZalogi(finalConn, table1.getModel().getValueAt(wybrane6, 0).toString());
                        break;
                    case "SAMOLOTY":
                        int wybrane7 = table1.getSelectedRow();
                        usunSamolot(finalConn, Integer.parseInt(table1.getModel().getValueAt(wybrane7, 0).toString()),
                                Integer.parseInt(table1.getModel().getValueAt(wybrane7, 1).toString()));

                        break;
                    case "ZAŁOGI":
                        int wybrane8 = table1.getSelectedRow();
                        usunZaloge(finalConn, table1.getModel().getValueAt(wybrane8, 4).toString());
                        break;

                }
            }
        });

        MODYFIKUJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String entity = comboBox1.getSelectedItem().toString();
                switch (entity) {
                    case "BILETY":
                        JOptionPane.showMessageDialog(Main.this, "Nie można ręcznie modyfikować biletów!", "Błąd", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "BRAMY":
                        /*int wybrane1 = table1.getSelectedRow();
                        Brama brama=new Brama(Integer.parseInt(table1.getModel().getValueAt(wybrane1,0).toString()));
                        usunBrame(finalConn, Integer.parseInt(table1.getModel().getValueAt(wybrane1, 0).toString()));*/
                        break;
                    case "LINIE LOTNICZE":
                        break;
                    case "LOTY":
                        break;
                    case "LOTNISKA":
                        break;
                    case "PASAŻEROWIE":
                        break;
                    case "PRACOWNICY":
                        break;
                    case "SAMOLOTY":
                        break;
                    case "ZAŁOGI":
                        break;

                }
            }
        });

        wyszukajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //to do

            }
        });
    }

    public static void main(String[] args) {
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

    /*public static List<Object> wyszukaj(Connection conn, String tabela, String kolumna, String warunek) {

    }*/

    public static List<Lotnisko> wypiszLotniska(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Lotnisko> lotniska = new ArrayList<Lotnisko>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select nazwa, miasto, kraj from lotniska order by nazwa asc");
            while (rs.next()) {
                Lotnisko lotnisko = new Lotnisko(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
                lotniska.add(lotnisko);
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

    public static List<LiniaLotnicza> wypiszLinieLotnicze(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<LiniaLotnicza> linieLotnicze = new ArrayList<LiniaLotnicza>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select nazwa from linie_lotnicze order by nazwa asc");
            while (rs.next()) {
                LiniaLotnicza liniaLotnicza = new LiniaLotnicza(rs.getString(1));
                linieLotnicze.add(liniaLotnicza);
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

    public static List<Brama> wypiszBramy(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Brama> bramy = new ArrayList<Brama>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select numer from bramy order by numer asc");
            while (rs.next()) {
                Brama brama = new Brama(rs.getInt(1));
                bramy.add(brama);
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

    public static List<Samolot> wypiszSamoloty(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Samolot> samoloty = new ArrayList<Samolot>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select ladownosc, liczba_miejsc from samoloty order by liczba_miejsc asc");
            while (rs.next()) {
                Samolot samolot = new Samolot(rs.getInt(1),
                        rs.getInt(2));
                samoloty.add(samolot);
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

    public static List<Pasazer> wypiszPasazerow(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Pasazer> pasazerowie = new ArrayList<Pasazer>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select p.pesel, p.imie, p.nazwisko, b.id, g.waga from pasazerowie p left outer join bilety b on p.pesel=b.pasazer_pesel left outer join bagaze g on b.id=g.bilet_id order by p.nazwisko asc");
            while (rs.next()) {
                Pasazer pasazer = new Pasazer(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
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

    public static List<Osoba> wypiszOsoby(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Osoba> osoby = new ArrayList<Osoba>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select o.pesel, o.imie, o.nazwisko, o.rola, z.loty_id from osoby o left outer join zalogi z on z.osoby_pesel=o.pesel  order by z.loty_id, o.nazwisko asc");
            while (rs.next()) {
                Osoba osoba = new Osoba(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                osoba.setId_lotu(rs.getString(5));
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

    public static List<Osoba> wypiszZalogi(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Osoba> osoby = new ArrayList<Osoba>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select o.pesel, o.imie, o.nazwisko, o.rola, z.loty_id from zalogi z join osoby o on z.osoby_pesel=o.pesel  order by z.loty_id, o.nazwisko asc");
            while (rs.next()) {
                Osoba osoba = new Osoba(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                osoba.setId_lotu(rs.getString(5));
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

    public static List<Bilet> wypiszWolneBilety(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Bilet> bilety = new ArrayList<Bilet>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from bilety b join loty l on b.lot_id=l.id where b.pasazer_pesel is NULL");
            while (rs.next()) {
                Bilet bilet = new Bilet(rs.getString(1));
                bilet.lot = new Lot(rs.getDate(5), rs.getDate(6), rs.getString(7),
                        rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11));
                bilet.lot.setId_lotu(rs.getString(4));
                bilety.add(bilet);
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

    public static List<Lot> wypiszLoty(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Lot> loty = new ArrayList<Lot>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from loty order by data_wylotu asc");
            while (rs.next()) {
                Lot lot = new Lot(rs.getDate(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8));
                lot.setId_lotu(rs.getString(1));
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

    public static List<String> wypiszLotyID(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<String> loty = new ArrayList<String>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select id from loty order by data_wylotu asc");
            while (rs.next()) {
                String lot = rs.getString(1);
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

    public static List<String> wypiszBramyID(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<String> loty = new ArrayList<String>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select numer from bramy order by numer asc");
            while (rs.next()) {
                String lot = rs.getString(1);
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

    public static List<String> wypiszSamolotyID(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<String> loty = new ArrayList<String>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select id from samoloty order by id asc");
            while (rs.next()) {
                String lot = rs.getString(1);
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

    public static List<String> wypiszLinieLotniczeNazwy(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<String> loty = new ArrayList<String>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select nazwa from linie_lotnicze order by nazwa asc");
            while (rs.next()) {
                String lot = rs.getString(1);
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

    public static List<String> wypiszLotniskaNazwy(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        List<String> loty = new ArrayList<String>();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select nazwa from lotniska order by nazwa asc");
            while (rs.next()) {
                String lot = rs.getString(1);
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
            rs = stmt.executeQuery("select id from loty where lotnisko_nazwa='" + nazwa + "'");
            while (rs.next()) {
                rs1 = stmt.executeQuery("select id from bagaze where bilet_id='" + rs.getString(1) + "'");
                while (rs1.next()) {
                    stmt.executeQuery("delete from bagaze where bilet_id='" + rs1.getString(1) + "'");
                }
                stmt.executeQuery("delete from bilety where id='" + rs.getString(1) + "'");
                stmt.executeQuery("delete from loty where id='" + rs.getString(1) + "'");
            }
            String query = "delete from lotniska where nazwa='" + nazwa + "'";
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
            rs = stmt.executeQuery("select id from loty where linia_lotnicza_nazwa='" + nazwa + "'");
            while (rs.next()) {
                rs1 = stmt.executeQuery("select id from bagaze where bilet_id='" + rs.getString(1) + "'");
                while (rs1.next()) {
                    stmt.executeQuery("delete from bagaze where bilet_id='" + rs1.getString(1) + "'");
                }
                stmt.executeQuery("delete from bilety where id='" + rs.getString(1) + "'");
                stmt.executeQuery("delete from loty where id='" + rs.getString(1) + "'");
            }
            String query = "delete from linie_lotnicze where nazwa='" + nazwa + "'";
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
            rs = stmt.executeQuery("select id from loty where brama_numer=" + numer);
            while (rs.next()) {
                rs1 = stmt.executeQuery("select id from bagaze where bilet_id='" + rs.getString(1) + "'");
                while (rs1.next()) {
                    stmt.executeQuery("delete from bagaze where bilet_id='" + rs1.getString(1) + "'");
                }
                stmt.executeQuery("delete from bilety where id='" + rs.getString(1) + "'");
                stmt.executeQuery("delete from loty where id='" + rs.getString(1) + "'");
            }
            String query = "delete from bramy where numer='" + numer + "'";
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
        String pom1;
        String pom2;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs2 = stmt.executeQuery("select id from samoloty where ladownosc=" + ladownosc + " and liczba_miejsc=" + liczba_miejsc);
            while (rs2.next()) {
                pom1 = rs2.getString(1);
                rs = stmt.executeQuery("select id from loty where samolot_id=" + pom1);
                while (rs.next()) {
                    pom2 = rs.getString(1);
                    rs1 = stmt.executeQuery("select id from bilety where lot_id='" + pom2 + "'");
                    while (rs1.next()) {
                        stmt.executeQuery("delete from bagaze where bilet_id='" + rs1.getString(1) + "'");
                    }
                    stmt.executeQuery("delete from bilety where id='" + pom2 + "'");
                }
                stmt.executeQuery("delete from loty where id='" + pom1 + "'");
                stmt.executeUpdate("delete from samoloty where id='" + pom1 + "'");
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
            rs = stmt.executeQuery("select id from bilety where pasazer_pesel='" + pesel + "'");
            while (rs.next()) {
                String pomocniczy = rs.getString(1);
                stmt.executeQuery("delete from bagaze where bilet_id='" + pomocniczy + "'");
                stmt.executeQuery("update bilety set pasazer_pesel \"NULL\" where id='" + pomocniczy + "'");
            }
            String query = "delete from pasazerowie where pesel='" + pesel + "'";
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
            String query = "delete from Zalogi where loty_id='" + lot_id + "'";
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
            String query1 = "delete from osoby where pesel='" + pesel + "'";
            stmt.executeUpdate(query1);
            String query = "delete from zalogi where osoby_pesel='" + pesel + "'";
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
            rs = stmt.executeQuery("select id from bilety where lot_id='" + id_lotu + "'");
            while (rs.next()) {
                rs1 = stmt.executeQuery("select id from bagaze where bilet_id='" + rs.getString(1) + "'");
                while (rs1.next()) {
                    stmt.executeQuery("delete from bagaze where bilet_id='" + rs1.getString(1) + "'");
                }
                stmt.executeQuery("delete from bilety where id='" + rs.getString(1) + "'");
            }
            String query = "delete from loty where id='" + id_lotu + "'";
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
            stmt = conn.prepareStatement("insert into linie_lotnicze(nazwa) values(?)");
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
            rs.next();
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
            stmt = conn.prepareStatement("insert into zalogi(osoby_pesel,loty_id) values(?, ?)");
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
            cstmt = conn.prepareCall("{call generowanie_biletow(?)}");
            rs = stmt1.executeQuery("select sum(id) from loty");
            rs.next();
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

    //tylko w funkcji modyfikujBagaz
    public static void dodajBagaz(Connection conn, Bagaz bagaz) {
        PreparedStatement stmt = null;
        Statement stmt1 = null;
        ResultSet rs = null;
        try {
            stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt1.executeQuery("select max(id) from bagaze");
            rs.next();
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
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), -1, -1));
        comboBox1 = new JComboBox();
        panel1.add(comboBox1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addNewButton = new JButton();
        addNewButton.setText("DODAJ...");
        panel1.add(addNewButton, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        MODYFIKUJButton = new JButton();
        MODYFIKUJButton.setText("MODYFIKUJ");
        panel1.add(MODYFIKUJButton, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        refreshButton = new JButton();
        refreshButton.setText("ODŚWIEŻ");
        panel1.add(refreshButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        USUŃButton = new JButton();
        USUŃButton.setText("USUŃ");
        panel1.add(USUŃButton, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 5, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        scrollPane1.setViewportView(table1);
        wyszukajButton = new JButton();
        wyszukajButton.setText("Wyszukaj");
        panel1.add(wyszukajButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        columnacomboBox = new JComboBox();
        panel1.add(columnacomboBox, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wyszukajtextField1 = new JTextField();
        wyszukajtextField1.setToolTipText("Wprowadż wyswzukiwaną wartość");
        panel1.add(wyszukajtextField1, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
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
