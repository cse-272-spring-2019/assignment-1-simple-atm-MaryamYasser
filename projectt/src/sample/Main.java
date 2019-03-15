package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Main extends Application {

    public Index histor = new Index();
    public int i=histor.getIndex();
    public Transaction Visa = new Transaction(1000);
    public Inquire sob = new Inquire();
    public int w=sob.getChange();




    @Override
    public void start(Stage ATM) throws Exception {
        int password =2008;
        Label message = new Label("");
        SetLabel(message);
        Label transac = new Label("");
        SetLabel(transac);
        List <String> History =new ArrayList<String>(5);


        Scene HomePage, BWith, passwordInquiry, Withdrawal,Deposit,BDepo,Balance;

        //Homepage

        Button withdraw = new Button("Withdraw");
        Button deposit = new Button("Deposit");
        Button balanceInquiry = new Button("Balance Inquiry");
        Button close = new Button("Close");
        Button prev = new Button ("Previous Transaction");
        Button next = new Button("Next Transaction");

        SetButton(next);
        SetButton(prev);
        SetButton(close);
        SetButton(balanceInquiry);
        SetButton(deposit);
        SetButton(withdraw);

        HBox prevnext = new HBox(10);
        prevnext.setSpacing(196);
        prevnext.getChildren().addAll(prev,next);


        BorderPane out = new BorderPane();
        out.setStyle("-fx-background-color: slategrey");




        ATM.setTitle("My ATM");
        VBox roots = new VBox(10);
        roots.getChildren().addAll(withdraw, deposit, balanceInquiry,close,transac);
        roots.setAlignment(Pos.CENTER);
        roots.setStyle("-fx-background-color: slategrey");
        HomePage = new Scene(out, 500, 275);
        out.setBottom(prevnext);
        out.setCenter(roots);



        //FRONT PAGE
        Label name = new Label("Enter Card Passcode:");
        SetLabel(name);
        PasswordField pass = new PasswordField();
        pass.setFont(Font.font("Krungthep"));
        pass.setStyle("-fx-background-color: darkgray");


        pass.setPromptText("Enter Passcode Here");
        Button enter = new Button("Enter");
        enter.setOnAction(e -> {
            if (!isInt(pass, pass.getText())){
                 pass.clear();
                 PopUp.Error("Wrong Format","Please Enter Password In Integers");}


            if(Integer.parseInt(pass.getText())==password)
                ATM.setScene(HomePage);
            else
            {       pass.clear();
                    message.setText("Your password is incorrect!");
                    message.setTextFill(Color.NAVAJOWHITE);}
        });

        SetButton(enter);



        VBox grid = new VBox(15);
        grid.getChildren().addAll(name, pass, enter,message);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: slategrey");
        passwordInquiry = new Scene(grid, 500, 275);


        ATM.setScene(passwordInquiry);
        ATM.show();


        //withdrawal was succesful
        Label success = new Label("Withdrawal was Succesful!");
        SetLabel(success);
        Button homePage = new Button("More Services? Back To Home Page");
        homePage.setOnAction(e -> ATM.setScene(HomePage));
        Button close1 = new Button("Exit?");
        close1.setOnAction(e -> closeProgram());


        SetButton(homePage);
        SetButton(close1);

        VBox WS = new VBox(15);
        WS.getChildren().addAll(success, homePage,close1);
        WS.setAlignment(Pos.CENTER);
        WS.setStyle("-fx-background-color: slategrey");
        BWith = new Scene(WS, 500, 275);

        //the user chose withdraw

        TextField cashout = new TextField();
        SetTextField(cashout);
        cashout.setPromptText("Enter Amount To Be Withdrawn");
        Button press = new Button("Confirm");
        press.setOnAction(e -> {
            transac.setText("");

            if(!isInt(cashout,cashout.getText())){
                cashout.clear();
            PopUp.Error("Wrong Format","Please Enter Amount In Integers");
            }

            if (Integer.parseInt(cashout.getText())>Visa.getBalance()){
                PopUp.Error("Error","Not Enough Cash");
            cashout.clear();}


            else if(Integer.parseInt(cashout.getText())>5000){
                PopUp.Error("Wrong Format", "Exceeds Daily Limit");
            cashout.clear();}

            else if (isInt(cashout, cashout.getText())){

                w=w-Integer.parseInt(cashout.getText());
               System.out.println(w);
                History.add("Withdrawn:"+Integer.parseInt(cashout.getText()));
                cashout.clear();
                ATM.setScene(BWith);}

                });

        Button cancel1 = new Button("Cancel Withdraw and Return to HomePage");
        cancel1.setOnAction(e->ATM.setScene(HomePage));

        SetButton(press);
        SetButton(cancel1);

        VBox withd = new VBox(15);
        withd.getChildren().addAll(cashout, press,cancel1);
        withd.setAlignment(Pos.CENTER);
        withd.setStyle("-fx-background-color: slategrey");
        Withdrawal = new Scene(withd, 500, 275);
        withdraw.setOnAction(e -> ATM.setScene(Withdrawal));

        // USER CHOOSES DEPOSIT
        Label success1 = new Label("Deposit was Succesful!");
        SetLabel(success1);
        Button homePage1 = new Button("More Services? Back To Home Page");
        homePage1.setOnAction(e -> ATM.setScene(HomePage));
        Button close2 = new Button("Exit?");
        close2.setOnAction(e -> closeProgram());


        SetButton(homePage1);
        SetButton(close2);

        VBox DS = new VBox(15);
        DS.getChildren().addAll(success1, homePage1,close2);
        DS.setAlignment(Pos.CENTER);
        DS.setStyle("-fx-background-color: slategrey");
        BDepo = new Scene(DS, 500, 275);

        TextField cashin = new TextField();
         SetTextField(cashin);
       cashin.setPromptText("Enter Amount To Be Deposited");
        Button press1 = new Button("Confirm");
        press1.setOnAction(e -> {

             transac.setText("");
            if (!isInt(cashin, cashin.getText()))
                PopUp.Error("Wrong Format", "Please Enter The Amount In Integers");


            if(Integer.parseInt(cashin.getText())>5000){
                PopUp.Error("Error","Exceeds Daily Limit");
            cashin.clear();}


            else{
                System.out.println(Visa.getBalance());
                w=w+Integer.parseInt(cashin.getText());

                History.add("Deposited:"+Integer.parseInt(cashin.getText()));
                cashin.clear();
                ATM.setScene(BDepo);
            } });

        Button cancel = new Button("Cancel Deposit and Return to HomePage");
        cancel.setOnAction(e->ATM.setScene(HomePage));


        SetButton(press1);
        SetButton(cancel);

        VBox depos = new VBox(15);
        depos.getChildren().addAll(cashin, press1,cancel);
        depos.setAlignment(Pos.CENTER);
        depos.setStyle("-fx-background-color: slategrey");
        Deposit = new Scene(depos, 500, 275);
        deposit.setOnAction(e -> ATM.setScene(Deposit));

        //USER CHOOSES BALANCE INQUIRY

        balanceInquiry.setOnAction(e -> transac.setText("Your Balance is: "+(Visa.getBalance()+w)));

        histor.setIndex(0);

       //PREV AND NEXT

        prev.setOnAction(e->{
            transac.setText(History.get(i));
            histor.setIndex(i++);
        });

        next.setOnAction(e->{
            if(i>0){
            transac.setText(History.get(i-1));
            histor.setIndex(i--);};
        });



        //button functions
        close.setOnAction(e -> closeProgram());
    }






    public static void main(String[] args) {
        launch(args);}

    public boolean isInt(TextField pass,String input){
        try
        {
           int k= Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex){
            return false;}

    }
    
    
    public void closeProgram() {
        boolean result = PopUp.confirm("Thank you,Have A Good Day!", "Are you sure you want to close?");
        if (result)
            Platform.exit();
    }


    public void SetButton(Button sample){
        sample.setTextFill(Color.NAVAJOWHITE);
        sample.setFont(Font.font("Krungthep"));
        sample.setStyle("-fx-background-color: black");
    }

    public void SetTextField(TextField sample){
        sample.setFont(Font.font("Krungthep"));
        sample.setStyle("-fx-background-color: black");
        sample.setStyle("-fx-text-inner-color: black;");
    }

    public static void SetLabel(Label sample){
        sample.setTextFill(Color.NAVAJOWHITE);
        sample.setFont(Font.font("Krungthep"));
        sample.setStyle("-fx-background-color: black");
    }














}

