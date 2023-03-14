package dao;

import pojo.Customer;
import pojo.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : hupo, 创建于:2023/3/13
 */
public class Database {
    public List<Customer> cList = new ArrayList<Customer>();
    public List<Staff> sList = new ArrayList<Staff>();

    public Database(){
        try {
            dataInit();
        } catch (ClassNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
//		testint();
    }
    public boolean addCustomer(Customer c) {
        if(cList.add(c))
            return true;
        return false;
    };
    public boolean addStaff(Staff s) {
        if(sList.add(s))
            return true;
        return false;
    }
    public Customer findCustomer(String id) {
        for(Customer each : cList) {
            if(each.getCardId().equals(id))
                return each;
        }
        return null;
    }
    public Staff findStaff(String id) {
        for(Staff each : sList) {
            if(each.getId().equals(id))
                return each;
        }
        return null;
    }

    public boolean delCustomer(Customer c) {
        if(cList.remove(c))
            return true;
        return false;
    }
    public boolean delStaff(Staff s) {
        if(sList.remove(s))
            return true;
        return false;
    }

    public void dataInit() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hair?serverTimezone=UTC","root","123456");
        Statement stat = conn.createStatement();
        String sql1 = "SELECT * FROM Customer";
        String sql2 = "SELECT * FROM Staff";
        ResultSet rs = stat.executeQuery(sql1); //execute执行，query：查询，resultset：结果集
        while(rs.next()) {
            Customer c = new Customer(rs.getString("cardId"), rs.getString("name"), rs.getString("sex"), rs.getString("tel"));
            c.setSpendMoney(rs.getInt("spendMoney"));
            cList.add(c);
        }
        rs = stat.executeQuery(sql2);
        while(rs.next()) {
            Staff s = new Staff(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("tel"));
            s.setGains(rs.getInt("gain"));
            sList.add(s);
        }
        conn.close();
        stat.close();
        conn.close();

    }
    public void saveAll() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hair","root","123456");
        Statement stat = conn.createStatement();
        String sql1 = "delete from Customer";
        stat.execute(sql1);
        String sql2 = "delete from Staff";
        stat.execute(sql2);
        for(Customer each:cList) {
            if(each.getStaff()!=null) {
                each.spendMoney();
            }
            String sql3 = "insert into Customer (cardId,name,sex,tel,spendMoney) values "+
                    String.format("('%s','%s','%s','%s',%d)", each.getCardId(),each.getName(),each.getSex(),each.getTel(),each.getSpendMoney());
            stat.execute(sql3);
        }
        for(Staff each:sList) {
            String sql4 = "insert into Staff (id,name,sex,tel,gain) values "+
                    String.format("('%s','%s','%s','%s',%d)", each.getId(),each.getName(),each.getSex(),each.getTel(),each.getGains());
            stat.execute(sql4);
        }
        conn.close();
        stat.close();
        conn.close();
    }
}
