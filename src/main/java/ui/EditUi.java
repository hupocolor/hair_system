package ui;

/**
 * @Author : hupo, 创建于:2023/3/13
 */
import dao.Database;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditUi extends Application{
    Database database;
    Button find = new Button("查找");
    Button yes = new Button("确定");
    Button no = new Button("取消");
    Label message = new Label();
    TextField idField = new TextField();
    TextField nameField = new TextField();
    TextField sexField = new TextField();
    TextField telField = new TextField();
    int type = 0; //type用于判断是顾客还是雇员,0顾客 1雇员 2未找到
    boolean sign = true; //用于判断目前id框是否为可编辑
    public EditUi(Database d) {
        database = d;
    }
    @Override
    public void start(Stage arg0) throws Exception {
        // TODO 自动生成的方法存根
        VBox vbox = new VBox(20);
        HBox hbox = new HBox();

        find.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                if(database.findCustomer(idField.getText())!=null) {
                    sign = false;
                    idField.setEditable(sign);
                    type = 0;
                    nameField.setText(database.findCustomer(idField.getText()).getName()); //将检索到的信息导入文本框内
                    sexField.setText(database.findCustomer(idField.getText()).getSex());
                    telField.setText(database.findCustomer(idField.getText()).getTel());
                    message.setText("已找到顾客");
                }else if(database.findStaff(idField.getText())!=null) {
                    sign = false;
                    idField.setEditable(sign);
                    type = 1;
                    nameField.setText(database.findStaff(idField.getText()).getName());
                    sexField.setText(database.findStaff(idField.getText()).getSex());
                    telField.setText(database.findStaff(idField.getText()).getTel());
                    message.setText("已找到雇员");
                }else {
                    message.setText("未找到人员");
                    type = 2;}
            }
        });

        yes.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                if(type == 0) {
                    database.findCustomer(idField.getText()).setName(nameField.getText());
                    database.findCustomer(idField.getText()).setSex(sexField.getText());
                    database.findCustomer(idField.getText()).setTel(telField.getText());
                    sign = true;
                    message.setText("编辑成功");
                }else if(type == 1) {
                    database.findStaff(idField.getText()).setName(nameField.getText());
                    database.findStaff(idField.getText()).setSex(sexField.getText());
                    database.findStaff(idField.getText()).setTel(telField.getText());
                    sign = true;
                    message.setText("编辑成功");
                }else
                    message.setText("请先输入卡号或编号");
                idField.setEditable(sign);
            }
        });

        no.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                sign = true;
                idField.setEditable(sign);
                message.setText("请重新输入卡号或编号");
            }
        });

        hbox.getChildren().addAll(yes,no);
        vbox.getChildren().addAll(idField,find,nameField,sexField,telField,hbox,message);
        Scene scene = new Scene(vbox,250,300);
        arg0.setScene(scene);
        arg0.show();
    }

}
