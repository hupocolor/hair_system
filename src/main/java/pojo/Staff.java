package pojo;
import hairdressing.HairItem;
import javafx.beans.property.SimpleStringProperty;
/**
 * @Author : hupo, 创建于:2023/3/13
 */
public class Staff {
    private String id;
    private String name;
    private String sex;
    private String tel;
    private HairItem item = null; //美发项目
    private int gains = 0; //员工收益
    private boolean free = true;

    //以下数据用于显示
    private SimpleStringProperty spId;
    private SimpleStringProperty spName;
    private SimpleStringProperty spSex;
    private SimpleStringProperty spTel;
    private SimpleStringProperty spMoney;
    private SimpleStringProperty spFree;
    private SimpleStringProperty spHairItem;
    //

    public String toString() {
        return name;
    }
    public Staff(String id, String name, String sex, String tel) {
        super();
        this.id = id;
        this.spId = new SimpleStringProperty(id);
        this.name = name;
        this.spName = new SimpleStringProperty(name);
        this.sex = sex;
        this.spSex = new SimpleStringProperty(sex);
        this.tel = tel;
        this.spTel = new SimpleStringProperty(tel);
        this.spFree = new SimpleStringProperty("空闲");
        this.spHairItem = new SimpleStringProperty("null");
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        this.spName.set(name);
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
        this.spSex.set(sex);
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
        this.spTel.set(tel);
    }
    public HairItem getItem() {
        return item;
    }
    public void setItem(HairItem item) {
        this.item = item;
        if(item == null)
            this.spHairItem.set("null");
        else
            this.spHairItem.set(item.toString());
    }

    public boolean isFree() {
        return free;
    }
    public void setIsfree(boolean free) {
        this.free = free;
        if(free) {
            this.spFree.set("空闲");
        }else this.spFree.set("忙碌");
    }
    public void setGains(int gain) {
        this.gains = gain;
    }
    public int getGains() {
        return gains;
    }
    public void gain() {
        gains += item.getmoney();
        spMoney.set(Integer.toString(gains));
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
    public String getMoney() {
//		return spMoney.get();
        return Integer.toString(gains);
    }
    public void setMoney(String spSpendMoney) {
        this.spMoney.set(spSpendMoney);
    }
    public String getSpFree() {
        return spFree.get();
    }
    public void setSpStaff(String spFree) {
        this.spFree.set(spFree);
    }
    public String getSpHairItem() {
        return spHairItem.get();
    }
    public void setSpHairItem(String spHairItem) {
        this.spHairItem.set(spHairItem);
    }
    public void setSpMoney(String spMoney) {
        this.spMoney.set(spMoney);
    }
    public String getSpMoney() {
//		return spMoney.get();
        return Integer.toString(gains);
    }
    public String showMe() {
        return("编号: "+this.id+"\n姓名: "+this.name+"\n性别: "+this.sex+"\n电话: "+this.tel+"\n收益: "+this.spMoney.get()+
                "\n是否空闲: "+this.spFree.get()+"\n美发项目: "+this.spHairItem.get());
    }

}