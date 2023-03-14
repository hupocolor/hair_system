package pojo;

/**
 * @Author : hupo, 创建于:2023/3/13
 */

import hairdressing.HairItem;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private String cardId;
    private String name;
    private String sex;
    private String tel;
    private int spendMoney = 0; //消费金额
    private Staff staff = null; //指定的雇员
    private HairItem hairItem = null; //当前指定的美发项目


    //以下数据类型用于表格显示
    private SimpleStringProperty spId;
    private SimpleStringProperty spName;
    private SimpleStringProperty spSex;
    private SimpleStringProperty spTel;
    private SimpleStringProperty spSpendMoney;
    private SimpleStringProperty spStaff;
    private SimpleStringProperty spHairItem;
    //-----------------------------------------------------

    public Customer(String cardId, String name, String sex, String tel) {
        super();
        this.cardId = cardId;
        this.spId = new SimpleStringProperty(cardId);
        this.name = name;
        this.spName = new SimpleStringProperty(name);
        this.sex = sex;
        this.spSex = new SimpleStringProperty(sex);
        this.tel = tel;
        this.spTel = new SimpleStringProperty(tel);
        this.spStaff = new SimpleStringProperty("null");
        this.spHairItem = new SimpleStringProperty("null");
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
        spId.set(cardId);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        spName.set(name);
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
        spSex.set(sex);
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
        spTel.set(tel);
    }
    public Staff getStaff() {
        return staff;
    }
    public boolean setStaff(Staff staff) {
        if(staff == null)
            spStaff.set("null");
        if(staff!=null && staff.isFree()) {
            this.staff = staff;
            spStaff.set(staff.toString()); //将员工的名字设置为property
            staff.setItem(hairItem);
            staff.setIsfree(false); //将员工设置为忙碌状态
            return true;
        }
        return false;
    }
    public HairItem getHairItem() {
        return hairItem;
    }
    public void setHairItem(HairItem hairItem) {
        this.hairItem = hairItem;
        if(hairItem == null)
            spHairItem.set("null");
        else
            spHairItem.set(hairItem.toString());
    }
    public int getSpendMoney() {
        return spendMoney;
    }
    public void spendMoney() {  //顾客消费动作
        spendMoney += hairItem.getmoney();
        spSpendMoney.set(Integer.toString(spendMoney));
        setHairItem(null);
        setStaff(null);
        staff.setIsfree(true);
        staff.gain();
        staff.setItem(null);
    }
    public void setSpendMoney(int spendMoney) {
        this.spendMoney = spendMoney;
    }
    public String showMe() { //显示所有信息方法
        return("卡号: "+this.cardId+"\n姓名: "+this.name+"\n性别: "+this.sex+"\n电话: "+this.tel+"\n消费金额: "+this.spSpendMoney.get()+
                "\n指定雇员: "+this.spStaff.get()+"\n美发项目: "+this.spHairItem.get());
    }


    //StringProperty的get和set方法，用于表格的显示
    public String getSpId() {
        return spId.get();
    }
    public void setSpId(String spId) {
        this.spId.set(spId);
    }
    public String getSpName() {
        return spName.get();
    }
    public void setSpName(String spName) {
        this.spName.set(spName);
    }
    public String getSpSex() {
        return spSex.get();
    }
    public void setSpSex(String spSex) {
        this.spSex.set(spSex);
    }
    public String getSpTel() {
        return spTel.get();
    }
    public void setSpTel(String spTel) {
        this.spTel.set(spTel);
    }
    public String getSpSpendMoney() {
//		return spSpendMoney.get();
        return Integer.toString(spendMoney);
    }
    public void setSpSpendMoney(String spSpendMoney) {
        this.spSpendMoney.set(spSpendMoney);
    }
    public String getSpStaff() {
        return spStaff.get();
    }
    public void setSpStaff(String spStaff) {
        this.spStaff.set(spStaff);
    }
    public String getSpHairItem() {
        return spHairItem.get();
    }
    public void setSpHairItem(String spHairItem) {
        this.spHairItem.set(spHairItem);
    }

}

