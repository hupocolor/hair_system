package ui;

/**
 * @Author : hupo, 创建于:2023/3/13
 */
import dao.Database;
import hairdressing.*;
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

public class ComeUi extends Application{
    Database database;
    String cardId;
    int type; //0洗发 1剪发 2染发 3烫发
    public ComeUi(Database s){
        this.database = s;
    }

    boolean sign = true; //true表示正在登记，false已开始登记
    TextField itemField = new TextField("在此处显示指定的项目");
    TextField staffField = new TextField("在此处显示指定的员工");
    HBox root = new HBox(20);
    VBox buttonVbox = new VBox(15);
    VBox cusAndSta = new VBox(30);
    VBox cus = new VBox(5);
    VBox sta = new VBox(10);
    HBox cardIdAndYes = new HBox(5);
    HBox idAndYes = new HBox(5);

    Label message = new Label("请选择空闲的员工");
    Button clean = new Button("洗发");
    Button cut = new Button("剪发");
    Button dye = new Button("染发");
    Button perm = new Button("烫发");
    TextField cardIdField = new TextField("在此处输入卡号");
    TextField idField = new TextField("在此处输入员工号");
    Button cardIdYes = new Button("确认");
    Button idYes = new Button("确认");
    Button correct = new Button("确定录入");
    Button cancel = new Button("取消录入");
    @Override
    public void start(Stage arg0) throws Exception {
        // TODO 自动生成的方法存根
        cardIdYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                if(database.findCustomer(cardIdField.getText())!=null) {
                    cardId = cardIdField.getText();
                    sign = false;
                    cardIdField.setEditable(sign); //禁止用户在这时访问产生错误
                    message.setText("找到顾客，已锁定");
                    itemField.setText(database.findCustomer(cardId).getSpHairItem());
                    staffField.setText(database.findCustomer(cardId).getSpStaff());
                }
            }
        }); //卡号右边的确定按钮方法

        clean.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                if(!sign) {
                    type = 0;
                    itemField.setText("洗发");
                }else
                    message.setText("请先指定顾客");
            }
        });

        cut.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                if(!sign) {
                    type = 1;
                    itemField.setText("剪发");
                }else
                    message.setText("请先指定顾客");
            }
        });

        dye.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                if(!sign) {
                    type = 2;
                    itemField.setText("染发");
                }else
                    message.setText("请先指定顾客");
            }
        });

        perm.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                if(!sign) {
                    type = 3;
                    itemField.setText("烫发");
                }else
                    message.setText("请先指定顾客");
            }
        });

        idYes.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                if(!sign && database.findStaff(idField.getText())!=null) {
                    staffField.setText(database.findStaff(idField.getText()).getName());
                    idField.setEditable(sign);
                }else
                    message.setText("未找到员工或未先指定顾客");
            }
        });

        correct.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                switch(type) {
                    case 0:database.findCustomer(cardId).setHairItem(new Hairclean());break;
                    case 1:database.findCustomer(cardId).setHairItem(new Haircut());break;
                    case 2:database.findCustomer(cardId).setHairItem(new Hairdye());break;
                    case 3:database.findCustomer(cardId).setHairItem(new Perm());break;
                    default:break;
                }
                if(database.findCustomer(cardId).setStaff(database.findStaff(idField.getText())))
                    message.setText("指定成功！");
            }
        });

        cancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                sign = true;
                cardIdField.setEditable(sign);
                idField.setEditable(sign);
            }
        });

        staffField.setEditable(false);
        itemField.setEditable(false);
        cardIdAndYes.getChildren().addAll(cardIdField,cardIdYes);
        cus.getChildren().addAll(cardIdAndYes,itemField,staffField);
        idAndYes.getChildren().addAll(idField,idYes);
        sta.getChildren().addAll(idAndYes,message);
        cusAndSta.getChildren().addAll(cus,sta);
        buttonVbox.getChildren().addAll(clean,cut,dye,perm,correct,cancel);
        root.getChildren().addAll(cusAndSta,buttonVbox);
        arg0.setScene(new Scene(root,300,250));
        arg0.show();
    }

}
