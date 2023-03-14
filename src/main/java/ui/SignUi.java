package ui;

import dao.Database;
import javafx.application.Application;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pojo.Customer;
import pojo.Staff;

import java.sql.SQLException;

/**
 * @Author : hupo, 创建于:2023/3/13
 */

public class SignUi extends Application {
    Database database = new Database();
    Label help = new Label("顾客卡号以C开头，员工编号以S开头");
    Button add = new Button("增加人员");
    Button del = new Button("删除");
    TextField idField = new TextField("在此处输入编号或者卡号");
    Button edit = new Button("编辑");
    Button find = new Button("查找");
    Button come = new Button("登记顾客");
    Button pay = new Button("顾客结账");
    Button showCustomer = new Button("显示所有顾客");
    Button showStaff = new Button("显示所有雇员");
    Button save = new Button("保存所有");
    BorderPane view = new BorderPane(); // 增加一个布局用于显示

    public void getNewTableViewC(BorderPane borderPane) { //获取顾客列表视图
        VBox vbox = new VBox();
//		List<Customer> testList = Arrays.asList(
//				new Customer("111","112","113","114"),
//				new Customer("001","002","003","004")
//				);
        ObservableList<Customer> obList = FXCollections.observableArrayList(database.cList);
        TableView<Customer> tableView = new TableView<Customer>(obList); // 依据指定数据创建表格视图
        tableView.setPrefSize(700, 600);
        TableColumn idCol = new TableColumn("卡号");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<>("spId"));
        TableColumn nameCol = new TableColumn("姓名");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("spName"));
        TableColumn sexCol = new TableColumn("性别");
        sexCol.setMinWidth(100);
        sexCol.setCellValueFactory(new PropertyValueFactory<>("spSex"));
        TableColumn telCol = new TableColumn("电话");
        telCol.setMinWidth(100);
        telCol.setCellValueFactory(new PropertyValueFactory<>("spTel"));
        TableColumn moneyCol = new TableColumn("消费金额");
        moneyCol.setMinWidth(100);
        moneyCol.setCellValueFactory(new PropertyValueFactory<>("spSpendMoney"));
        TableColumn staffCol = new TableColumn("指定雇员");
        staffCol.setMinWidth(100);
        staffCol.setCellValueFactory(new PropertyValueFactory<>("spStaff"));
        TableColumn itemCol = new TableColumn("消费项目");
        itemCol.setMinWidth(100);
        itemCol.setCellValueFactory(new PropertyValueFactory<>("spHairItem"));
        tableView.getColumns().addAll(idCol,nameCol,sexCol,telCol,moneyCol,staffCol,itemCol);
        vbox.getChildren().add(tableView);
        borderPane.setCenter(vbox);
    }

    public void getNewTableViewS(BorderPane borderPane) { //获取雇员列表视图
        VBox vbox = new VBox();
//		List<Customer> testList = Arrays.asList(
//				new Customer("111","112","113","114"),
//				new Customer("001","002","003","004")
//				);
        ObservableList<Staff> obList = FXCollections.observableArrayList(database.sList);
        TableView<Staff> tableView = new TableView<Staff>(obList); // 依据指定数据创建表格视图
        tableView.setPrefSize(700, 600);
        TableColumn idCol = new TableColumn("编号");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<>("spId"));
        TableColumn nameCol = new TableColumn("姓名");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("spName"));
        TableColumn sexCol = new TableColumn("性别");
        sexCol.setMinWidth(100);
        sexCol.setCellValueFactory(new PropertyValueFactory<>("spSex"));
        TableColumn telCol = new TableColumn("电话");
        telCol.setMinWidth(100);
        telCol.setCellValueFactory(new PropertyValueFactory<>("spTel"));
        TableColumn moneyCol = new TableColumn("收益");
        moneyCol.setMinWidth(100);
        moneyCol.setCellValueFactory(new PropertyValueFactory<>("spMoney"));
        TableColumn freeCol = new TableColumn("是否空闲");
        freeCol.setMinWidth(100);
        freeCol.setCellValueFactory(new PropertyValueFactory<>("spFree"));
        TableColumn itemCol = new TableColumn("美发项目");
        itemCol.setMinWidth(100);
        itemCol.setCellValueFactory(new PropertyValueFactory<>("spHairItem"));
        tableView.getColumns().addAll(idCol,nameCol,sexCol,telCol,moneyCol,freeCol,itemCol);
        vbox.getChildren().add(tableView);
        borderPane.setCenter(vbox);
    }

    public void getFindTable(BorderPane borderPane) {
        Label findShow = new Label();
        if(database.findCustomer(idField.getText())!=null) {
            findShow.setText(database.findCustomer(idField.getText()).showMe());
        }else if(database.findStaff(idField.getText())!=null) {
            findShow.setText(database.findStaff(idField.getText()).showMe());
        }else
            findShow.setText("请输入正确的编号或卡号");
        borderPane.setCenter(findShow);
    }

    public void getDeleteView(BorderPane borderPane) {
        Label delShow = new Label();
        Button yes = new Button("确定");
        Stage yesStage = new Stage();
        if(database.findCustomer(idField.getText())!=null) {
            VBox dvbox = new VBox();
            yes.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    // TODO 自动生成的方法存根
                    database.delCustomer(database.findCustomer(idField.getText()));
                    delShow.setText("删除成功");
                    yesStage.close();
                }
            });
            delShow.setText("已找到顾客，确定删除？");
            dvbox.getChildren().addAll(delShow,yes);
            Scene mess = new Scene(dvbox);
            yesStage.setScene(mess);
            yesStage.show();
        }else if(database.findStaff(idField.getText())!=null) {
            VBox dvbox = new VBox();
            yes.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    // TODO 自动生成的方法存根
                    database.delStaff(database.findStaff(idField.getText()));
                    delShow.setText("删除成功");
                    yesStage.close();
                }
            });
            delShow.setText("已找到雇员，确定删除？");
            dvbox.getChildren().addAll(delShow,yes);
            Scene mess = new Scene(dvbox);
            yesStage.setScene(mess);
            yesStage.show();
        }else {
            VBox dvbox = new VBox();
            delShow.setText("未找到人员，请重试！");
            dvbox.getChildren().addAll(delShow);
            Scene mess = new Scene(dvbox);
            yesStage.setScene(mess);
            yesStage.show();
        }
    }

    public void start(Stage primaryStage) throws Exception {
        // TODO 自动生成的方法存根

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                try {
                    new AddUi(database).start(new Stage()); //嵌套匿名类调用ui
                } catch (Exception e) {
                    e.printStackTrace();
                };
            }
        }); //增加人员的按钮方法

        showCustomer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                getNewTableViewC(view);
            }
        }); //显示所有顾客的按钮方法

        showStaff.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                getNewTableViewS(view);
            }
        }); //显示所有雇员的按钮方法

        find.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                getFindTable(view);
            }
        }); //查找按钮方法

        del.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                getDeleteView(view);;
            }
        }); //删除按钮方法

        come.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                try {
                    new ComeUi(database).start(new Stage());
                } catch (Exception e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
            }
        }); //等级按钮方法

        edit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                try {
                    new EditUi(database).start(new Stage());
                } catch (Exception e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
            }
        }); //编辑按钮方法

        pay.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO 自动生成的方法存根
                Stage mesStage = new Stage();
                Label messageL = new Label("请输入正确的需要结账的顾客卡号");
                if(database.findCustomer(idField.getText())!=null && database.findCustomer(idField.getText()).getStaff()!=null) {
                    database.findCustomer(idField.getText()).spendMoney();
                    messageL.setText("结账成功");
                }
                BorderPane root = new BorderPane();
                root.setCenter(messageL);
                mesStage.setScene(new Scene(root,100,100));
                mesStage.show();
            }
        });

        save.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO 自动生成的方法存根
                try {
                    database.saveAll();
                } catch (ClassNotFoundException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
            }
        });

        VBox root = new VBox(10);
        HBox tool_1 = new HBox(10);
        HBox tool_2 = new HBox(20);
        tool_1.getChildren().addAll(find,idField,add,del,edit,save);
        tool_2.getChildren().addAll(come,pay,showCustomer,showStaff,help);
        root.getChildren().addAll(tool_1,tool_2,view);
        Scene scene = new Scene(root,700,700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("美发店管理系统");
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
