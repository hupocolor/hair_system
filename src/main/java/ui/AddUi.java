package ui;

/**
 * @Author : hupo, 创建于:2023/3/13
 */
import pojo.Customer;
import pojo.Staff;
import dao.Database;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddUi extends Application{

    Button cAdd = new Button("添加顾客");
    Button sAdd = new Button("添加雇员");
    Label message = new Label();
    Database database;
    AddUi(Database d){
        database = d;
    }
    @Override
    public void start(Stage arg0) throws Exception {
        // TODO 自动生成的方法存根
        HBox root = new HBox(10);
        VBox vbox1 = new VBox(20); //雇员
        VBox vbox2 = new VBox(20); //顾客
        Label sLabel = new Label("请按 编号 姓名 性别 电话的顺序填写雇员信息");
        Label cLabel = new Label("请按 卡号 姓名 性别 电话的顺序填写顾客信息");
        TextField sid = new TextField();
        TextField cid = new TextField();
        TextField sname = new TextField();
        TextField cname = new TextField();
        TextField ssex = new TextField();
        TextField csex = new TextField();
        TextField stel = new TextField();
        TextField ctel = new TextField();

        cAdd.setOnAction(new EventHandler<ActionEvent>() { //添加顾客按钮方法

            @Override
            public void handle(ActionEvent arg0) {
                // TODO 自动生成的方法存根
                Customer c = new Customer(cid.getText(),cname.getText(),csex.getText(),ctel.getText());
                if(database.addCustomer(c))
                    message.setText("添加成功");
                else message.setText("添加失败");
            }
        });
        sAdd.setOnAction(new EventHandler<ActionEvent>() { //添加雇员按钮方法

            @Override
            public void handle(ActionEvent arg0) {
                // TODO 自动生成的方法存根
                Staff s = new Staff(sid.getText(),sname.getText(),ssex.getText(),stel.getText());
                if(database.addStaff(s))
                    message.setText("添加成功");
                else message.setText("添加失败");
            }
        });

        vbox1.getChildren().addAll(sAdd,sLabel,sid,sname,ssex,stel,message);
        vbox2.getChildren().addAll(cAdd,cLabel,cid,cname,csex,ctel);
        root.getChildren().addAll(vbox1,vbox2);
        Scene scene = new Scene(root,550,300);
        arg0.setScene(scene);
        arg0.setTitle("添加人员");
        arg0.show();
    }

}