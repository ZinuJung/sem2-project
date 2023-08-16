package com.example.controllers;

import com.example.database.*;
import com.example.exceptions.AlertMessage;
import com.example.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardController implements Initializable {
    @FXML
    private AreaChart<?, ?> areaChartDataIncome;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCheckIn;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnRooms;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<?> cbBoxRoomType;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colRoomNumber;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private AnchorPane customersForm;

    @FXML
    private AnchorPane dashboardForm;

    @FXML
    private Label lbTodayBook;

    @FXML
    private Label lbTodayIncome;

    @FXML
    private Label lbTotalIncome;

    @FXML
    private Label lbUsername;

    @FXML
    private AnchorPane roomsForm;

    @FXML
    private TableView<Room> tableViewRooms;

    @FXML
    private Label lbId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtRoomNumber;

    @FXML
    private TextField txtRoomSearch;

    @FXML
    private Label lbCustomerId;

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtIdentity;

    @FXML
    private TextField txtContact;

    @FXML
    private TableView<Customer> tableViewCustomers;

    @FXML
    private TableColumn<Customer, Integer> colCustomerId;

    @FXML
    private TableColumn<Customer, String> colFullName;

    @FXML
    private TableColumn<Customer, String> colIdentity;

    @FXML
    private TableColumn<Customer, String> colContact;

    @FXML
    private AnchorPane checkinForm;

    @FXML
    private Label lbConfirmId;

    @FXML
    private TextField txtConfirmName;

    @FXML
    private TextField txtConfirmIdentity;

    @FXML
    private TextField txtConfirmContact;

    @FXML
    private ComboBox<String> cbBoxRoomType1;

    @FXML
    private ComboBox<String> cbBoxRoomNumber;

    @FXML
    private DatePicker dpCheckin;

    @FXML
    private DatePicker dpCheckout;

    @FXML
    private Button btnBookings;

    @FXML
    private AnchorPane bookingsForm;

    @FXML
    private TableView<Booking> tableViewBookings;

    @FXML
    private TableColumn<?, ?> colRoomId1;

    @FXML
    private TableColumn<?, ?> colCheckIn;

    @FXML
    private TableColumn<?, ?> colCheckOut;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private Label lbDate;

    @FXML
    private AnchorPane checkoutForm;

    @FXML
    private TableView<Checkout> tableViewCheckout;

    @FXML
    private TableColumn<?, ?> colParticular;

    @FXML
    private TableColumn<?, ?> colDay;

    @FXML
    private TableColumn<?, ?> colCheckoutPrice;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private Label lbCustomerCheckoutId;

    @FXML
    private Label lbRoomFee;

    @FXML
    private AnchorPane servicesForm;

    @FXML
    private Label lbRoomNumberOrder;

    @FXML
    private ComboBox<String> cbBoxServices;

    @FXML
    private TextField txtPriceOrder;

    @FXML
    private TextField txtQuantity;

    @FXML
    private AnchorPane serviceForm;

    @FXML
    private Button btnServices;

    @FXML
    private TableView<Service> tableViewService;

    @FXML
    private TableColumn<?, ?> colServiceId;

    @FXML
    private TableColumn<?, ?> colServiceName;

    @FXML
    private TableColumn<?, ?> colServicePrice;

    @FXML
    private TextField txtServiceName;

    @FXML
    private TextField txtServicePrice;

    @FXML
    private Label lbServiceId;

    @FXML
    private TableView<BookingService> tableViewServices;

    @FXML
    private TableColumn<?, ?> colBookingId;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colOrderQuantity;

    @FXML
    private TableView<CheckOutService> tableViewServiceCheckOut;

    @FXML
    private TableColumn<?, ?> colServiceCheckOut;

    @FXML
    private TableColumn<?, ?> colServicePriceCheckOut;

    @FXML
    private TableColumn<?, ?> colServiceAmount;

    @FXML
    private TableColumn<?, ?> colQuantityCheckOut;

    @FXML
    private Label lbTotalAmount;

    @FXML
    private Label lbServiceFee;

    @FXML
    private TableColumn<?, ?> colCustomerIdBooking;

    @FXML
    private Label lbDiscount;

    @FXML
    private TableView<Invoice> tableViewInvoice;

    @FXML
    private TableColumn<?, ?> colInvoiceId;

    @FXML
    private TableColumn<?, ?> colCustomerInvoiceId;

    @FXML
    private TableColumn<?, ?> colTotalRoom;

    @FXML
    private TableColumn<?, ?> colTotalService;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colInvoiceDate;

    @FXML
    private AnchorPane invoicesForm;

    @FXML
    private Button btnInvoice;

    @FXML
    private Button btnOrder;

    @FXML
    private TextField txtSearchCustomer;

    ObservableList list;
    AlertMessage alert = new AlertMessage();

    private String type[] =
            {"Single", "Double", "Twin", "Standard", "Superior", "Deluxe", "Suite", "View", "Corner", "Family", "VIP"};

    public void roomType() {
        ArrayList<String> listData = new ArrayList<>();
        for (String data : type) {
            listData.add(data);
        }
        list = FXCollections.observableArrayList(listData);
        cbBoxRoomType.setItems(list);
    }

    DbSetRoom r = new DbSetRoom();

    public static boolean containsOnlyDigits(String inputString) {
        String regex = "^[0-9]+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(inputString);

        return matcher.matches();
    }

    public static boolean containsOnlyStrings(String inputString) {
        String regex = "^[a-zA-Z]*$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(inputString);

        return matcher.matches();
    }

    public void addRoom() {
        if (txtRoomNumber.getText().isEmpty() || cbBoxRoomType.getSelectionModel().getSelectedItem() == null || txtPrice.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else if (!containsOnlyDigits(txtRoomNumber.getText()) || !containsOnlyDigits(txtPrice.getText())) {
            alert.errorMessage("Invalid Room#/Price!");
        } else {
            r.add(new Room(txtRoomNumber.getText(), (String) cbBoxRoomType.getSelectionModel().getSelectedItem(), Double.valueOf(txtPrice.getText())));
            showRoomsData();
            clear();
        }
    }

    public void selectRoom() {
        Room room = tableViewRooms.getSelectionModel().getSelectedItem();
        int num = tableViewRooms.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        lbId.setText(String.valueOf(room.getRoomId()));
        txtRoomNumber.setText(room.getRoomNumber());
        txtPrice.setText(String.valueOf(room.getBasePrice()));
    }

    public void updateRoom() {
        if (txtRoomNumber.getText().isEmpty() || cbBoxRoomType.getSelectionModel().getSelectedItem() == null || txtPrice.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else if (!containsOnlyDigits(txtRoomNumber.getText()) || !containsOnlyDigits(txtPrice.getText())) {
            alert.errorMessage("Invalid Room#/Price!");
        } else {
            r.update(new Room(Integer.valueOf(lbId.getText()), txtRoomNumber.getText(), (String) cbBoxRoomType.getSelectionModel().getSelectedItem(), Double.valueOf(txtPrice.getText())));
            showRoomsData();
            clear();
        }
    }

    public void deleteRoom() {
        if (lbId.getText().isEmpty()) {
            alert.errorMessage("Please select the data first!");
        } else {
            r.delete(Integer.valueOf(lbId.getText()));
            showRoomsData();
            clear();
        }
    }

    public void clear() {
        lbId.setText("");
        txtRoomNumber.setText("");
        cbBoxRoomType.getSelectionModel().clearSelection();
        txtPrice.setText("");
    }

    public void showRoomsData() {
        tableViewRooms.getItems().clear();

        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("basePrice"));

        tableViewRooms.setItems(r.findAll());
    }

    DbSetEmployee e = new DbSetEmployee();

    public void logout() {
        e.logout(btnLogout);
    }

    DbSetCustomer c = new DbSetCustomer();

    DbSetBooking b = new DbSetBooking();

    public void switchForm(ActionEvent event) {
        if (event.getSource() == btnDashboard) {
            dashboardForm.setVisible(true);
            roomsForm.setVisible(false);
            customersForm.setVisible(false);
            checkinForm.setVisible(false);
            checkoutForm.setVisible(false);
            servicesForm.setVisible(false);
            bookingsForm.setVisible(false);
            serviceForm.setVisible(false);
            invoicesForm.setVisible(false);

            btnDashboard.setStyle("-fx-background-color: linear-gradient(to bottom right, #5068c9, #bc59e4)");
            btnRooms.setStyle("-fx-background-color: transparent");
            btnCustomers.setStyle("-fx-background-color: transparent");
            btnBookings.setStyle("-fx-background-color: transparent");
            btnServices.setStyle("-fx-background-color: transparent");
            btnInvoice.setStyle("-fx-background-color: transparent");

            countBooking();
            incomeToday();
            totalIncome();
            chart();
        } else if (event.getSource() == btnRooms) {
            dashboardForm.setVisible(false);
            roomsForm.setVisible(true);
            customersForm.setVisible(false);
            bookingsForm.setVisible(false);
            checkinForm.setVisible(false);
            checkoutForm.setVisible(false);
            servicesForm.setVisible(false);
            serviceForm.setVisible(false);
            invoicesForm.setVisible(false);

            btnRooms.setStyle("-fx-background-color: linear-gradient(to bottom right, #5068c9, #bc59e4)");
            btnDashboard.setStyle("-fx-background-color: transparent");
            btnCustomers.setStyle("-fx-background-color: transparent");
            btnBookings.setStyle("-fx-background-color: transparent");
            btnServices.setStyle("-fx-background-color: transparent");
            btnInvoice.setStyle("-fx-background-color: transparent");

            showRoomsData();
        } else if (event.getSource() == btnCustomers) {
            dashboardForm.setVisible(false);
            roomsForm.setVisible(false);
            customersForm.setVisible(true);
            bookingsForm.setVisible(false);
            checkinForm.setVisible(false);
            checkoutForm.setVisible(false);
            servicesForm.setVisible(false);
            serviceForm.setVisible(false);
            invoicesForm.setVisible(false);

            btnCustomers.setStyle("-fx-background-color: linear-gradient(to bottom right, #5068c9, #bc59e4)");
            btnRooms.setStyle("-fx-background-color: transparent");
            btnDashboard.setStyle("-fx-background-color: transparent");
            btnBookings.setStyle("-fx-background-color: transparent");
            btnServices.setStyle("-fx-background-color: transparent");
            btnInvoice.setStyle("-fx-background-color: transparent");

            showCustomerData();
        } else if (event.getSource() == btnBookings) {
            dashboardForm.setVisible(false);
            roomsForm.setVisible(false);
            customersForm.setVisible(false);
            bookingsForm.setVisible(true);
            checkinForm.setVisible(false);
            checkoutForm.setVisible(false);
            servicesForm.setVisible(false);
            serviceForm.setVisible(false);
            invoicesForm.setVisible(false);

            btnBookings.setStyle("-fx-background-color: linear-gradient(to bottom right, #5068c9, #bc59e4)");
            btnRooms.setStyle("-fx-background-color: transparent");
            btnCustomers.setStyle("-fx-background-color: transparent");
            btnDashboard.setStyle("-fx-background-color: transparent");
            btnServices.setStyle("-fx-background-color: transparent");
            btnInvoice.setStyle("-fx-background-color: transparent");

            showBookingData();
        } else if (event.getSource() == btnServices) {
            dashboardForm.setVisible(false);
            roomsForm.setVisible(false);
            customersForm.setVisible(false);
            bookingsForm.setVisible(false);
            checkinForm.setVisible(false);
            checkoutForm.setVisible(false);
            servicesForm.setVisible(false);
            serviceForm.setVisible(true);
            invoicesForm.setVisible(false);

            btnServices.setStyle("-fx-background-color: linear-gradient(to bottom right, #5068c9, #bc59e4)");
            btnRooms.setStyle("-fx-background-color: transparent");
            btnCustomers.setStyle("-fx-background-color: transparent");
            btnBookings.setStyle("-fx-background-color: transparent");
            btnDashboard.setStyle("-fx-background-color: transparent");
            btnInvoice.setStyle("-fx-background-color: transparent");

            showServicesData();
        } else if (event.getSource() == btnInvoice) {
            dashboardForm.setVisible(false);
            roomsForm.setVisible(false);
            customersForm.setVisible(false);
            bookingsForm.setVisible(false);
            checkinForm.setVisible(false);
            checkoutForm.setVisible(false);
            servicesForm.setVisible(false);
            serviceForm.setVisible(false);
            invoicesForm.setVisible(true);

            btnInvoice.setStyle("-fx-background-color: linear-gradient(to bottom right, #5068c9, #bc59e4)");
            btnRooms.setStyle("-fx-background-color: transparent");
            btnCustomers.setStyle("-fx-background-color: transparent");
            btnBookings.setStyle("-fx-background-color: transparent");
            btnServices.setStyle("-fx-background-color: transparent");
            btnDashboard.setStyle("-fx-background-color: transparent");

            showInvoiceData();
        }
    }

    public void showCustomerData() {

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colIdentity.setCellValueFactory(new PropertyValueFactory<>("identityNumber"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        tableViewCustomers.setItems(c.findAll());
    }

//    public void searchCustomer() {
//
//        FilteredList<Customer> filter = new FilteredList<>(c.findAll(), e -> true);
//
//        txtSearchCustomer.textProperty().addListener((observable, oldValue, newValue) -> {
//            filter.setPredicate(predicateCustomer -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                String searchKey = newValue.toLowerCase();
//
//                if(predicateCustomer.getFullName().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else if (predicateCustomer.getIdentityNumber().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else if (predicateCustomer.getContactNumber().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            });
//        });
//
//        SortedList<Customer> sortList = new SortedList<>(filter);
//        sortList.comparatorProperty().bind(tableViewCustomers.comparatorProperty());
//        tableViewCustomers.setItems(sortList);
//    }

    public void clearCustomerForm() {
        lbCustomerId.setText("");
        txtFullName.setText("");
        txtIdentity.setText("");
        txtContact.setText("");
    }

    public void addCustomer() {
        if (txtFullName.getText().isEmpty() || txtIdentity.getText().isEmpty() || txtContact.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else if (!containsOnlyStrings(txtFullName.getText()) || !containsOnlyDigits(txtIdentity.getText()) || !containsOnlyDigits(txtContact.getText())) {
            alert.errorMessage("Invalid Name/Identity/Contact!");
        } else {
            c.add(new Customer(txtFullName.getText(), txtIdentity.getText(), txtContact.getText()));
            showCustomerData();
            clearCustomerForm();
        }
    }

    public void selectCustomer() {
        Customer customer = tableViewCustomers.getSelectionModel().getSelectedItem();
        int num = tableViewCustomers.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        lbCustomerId.setText(String.valueOf(customer.getCustomerId()));
        txtFullName.setText(customer.getFullName());
        txtIdentity.setText(customer.getIdentityNumber());
        txtContact.setText(customer.getContactNumber());
    }

    public void updateCustomer() {
        if (lbCustomerId.getText().isEmpty() || txtFullName.getText().isEmpty() || txtIdentity.getText().isEmpty() || txtContact.getText().isEmpty()) {
            alert.errorMessage("Please select the data first!");
        } else if (!containsOnlyStrings(txtFullName.getText()) || !containsOnlyDigits(txtIdentity.getText()) || !containsOnlyDigits(txtContact.getText())) {
            alert.errorMessage("Invalid Name/Identity/Contact!");
        } else {
            c.update(new Customer(Integer.valueOf(lbCustomerId.getText()), txtFullName.getText(), txtIdentity.getText(), txtContact.getText()));
            showCustomerData();
            clearCustomerForm();
        }
    }

    public void confirm() {
        if (lbCustomerId.getText().isEmpty() || txtFullName.getText().isEmpty() || txtIdentity.getText().isEmpty() || txtContact.getText().isEmpty()) {
            alert.errorMessage("Please select the data first!");
        } else {
            availableRoomType();
            checkinForm.setVisible(true);
            lbConfirmId.setText(lbCustomerId.getText());
            txtConfirmName.setText(txtFullName.getText());
            txtConfirmName.setEditable(false);
            txtConfirmIdentity.setText(txtIdentity.getText());
            txtConfirmIdentity.setEditable(false);
            txtConfirmContact.setText(txtContact.getText());
            txtConfirmContact.setEditable(false);
        }
    }

    public void cancel() {
        checkinForm.setVisible(false);
    }

    public void availableRoomType() {
        cbBoxRoomType1.setItems(r.getRoomTypeByStatus());
        availableRoomNumber();
    }

    public void availableRoomNumber() {
        cbBoxRoomNumber.setItems(r.getRoomNumberByRoomType(cbBoxRoomType1.getSelectionModel().getSelectedItem()));
    }

    public void checkin() {
        if (cbBoxRoomType1.getSelectionModel().isEmpty() || cbBoxRoomNumber.getSelectionModel().isEmpty()
                || dpCheckin.getValue() == null || dpCheckout.getValue() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            LocalDate checkinDate = dpCheckin.getValue();
            LocalDate checkoutDate = dpCheckout.getValue();
            Date checkin = java.sql.Date.valueOf(checkinDate);
            Date checkout = java.sql.Date.valueOf(checkoutDate);
            b.add(new Booking(Integer.valueOf(lbConfirmId.getText()), r.getRoomIdByRoomNumber(cbBoxRoomNumber.getSelectionModel().getSelectedItem()),
                    checkin, checkout));
            cbBoxRoomType1.getSelectionModel().clearSelection();
            cbBoxRoomNumber.getSelectionModel().clearSelection();
            dpCheckin.setValue(null);
            dpCheckout.setValue(null);
        }
    }

    public void showBookingData() {
        tableViewBookings.getItems().clear();

        colRoomId1.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colCustomerIdBooking.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("checkedOut"));

        tableViewBookings.setItems(b.findAll());
    }

    public int selectBooking() {
        Booking booking = tableViewBookings.getSelectionModel().getSelectedItem();
        if (booking != null) {
            if (b.checkStatus(booking.getBookingId()).equals("Checked-out")) {
                btnOrder.setDisable(true);
            } else {
                btnOrder.setDisable(false);
            }
        }
        int num = tableViewBookings.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return 0;
        }
        return booking.getRoomId();
    }

    public void showServicesData() {
        tableViewService.getItems().clear();

        colServiceId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        colServiceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        colServicePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableViewService.setItems(s.findAll());
    }

    public void addService() {
        if (txtServiceName.getText().isEmpty() || txtServicePrice.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else if (!containsOnlyDigits(txtServicePrice.getText()) || !containsOnlyStrings(txtServiceName.getText())) {
            alert.errorMessage("Invalid Service name/Price");
        } else {
            s.add(new Service(txtServiceName.getText(), Double.valueOf(txtServicePrice.getText())));
            showServicesData();
            clearService();
        }
    }

    public void selectService() {
        Service service = tableViewService.getSelectionModel().getSelectedItem();
        int num = tableViewService.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        lbServiceId.setText(String.valueOf(service.getServiceId()));
        txtServiceName.setText(service.getServiceName());
        txtServicePrice.setText(String.valueOf(service.getPrice()));
    }

    public void updateService() {
        if (lbServiceId.getText().isEmpty() || txtServiceName.getText().isEmpty() || txtServicePrice.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else if (!containsOnlyDigits(txtServicePrice.getText()) || !containsOnlyStrings(txtServiceName.getText())) {
            alert.errorMessage("Invalid Service name/Price");
        } else {
            s.update(new Service(Integer.valueOf(lbServiceId.getText()), txtServiceName.getText(), Double.valueOf(txtServicePrice.getText())));
            showServicesData();
            clearService();
        }
    }

    public void clearService() {
        lbServiceId.setText("");
        txtServiceName.setText("");
        txtServicePrice.setText("");
    }

    public void deleteService() {
        if (lbServiceId.getText().isEmpty()) {
            alert.errorMessage("Please select the data first!");
        } else {
            s.delete(Integer.valueOf(lbServiceId.getText()));
            showServicesData();
            clearService();
        }
    }

    public void order() {
        if (tableViewBookings.getSelectionModel().getSelectedItems().isEmpty()) {
            alert.errorMessage("Please select the data first!");
        } else {
            servicesForm.setVisible(true);
            txtPriceOrder.setEditable(false);
            lbRoomNumberOrder.setText(r.getRoomNumberbyRoomId(selectBooking()));

            serviceName();
            showOrdersData();
        }
    }

    public void serviceName() {
        cbBoxServices.setItems(s.getServiceName());
        txtPriceOrder.setText(String.valueOf(Double.valueOf(s.getServicePriceByServiceName(cbBoxServices.getSelectionModel().getSelectedItem()))));
    }

    DbSetService s = new DbSetService();

    DbSetBookingService bs = new DbSetBookingService();

    public void showOrdersData() {
        tableViewServices.getItems().clear();

        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        colOrderQuantity.setCellValueFactory(new PropertyValueFactory<>("serviceQuantity"));

        int roomId = r.getRoomIdByRoomNumber(lbRoomNumberOrder.getText());
        tableViewServices.setItems(bs.findByBookingId(b.getBookingIdByRoomId(roomId)));
    }

    public void clearOrder() {
        cbBoxServices.getSelectionModel().clearSelection();
        txtQuantity.setText("");
        txtPriceOrder.setText("");
    }

    public void addOrder() {
        if (cbBoxServices.getSelectionModel().isEmpty() || txtQuantity.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else if (!containsOnlyDigits(txtQuantity.getText())) {
            alert.errorMessage("Invalid quantity!");
        } else {
            int roomId = r.getRoomIdByRoomNumber(lbRoomNumberOrder.getText());
            int serviceId = s.getServiceIdByServiceName(cbBoxServices.getSelectionModel().getSelectedItem());
            bs.add(new BookingService(b.getBookingIdByRoomId(roomId), serviceId, Integer.valueOf(txtQuantity.getText())));
            showOrdersData();
            clearOrder();
        }
    }

    public void cancelOrder() {
        clearOrder();
        servicesForm.setVisible(false);
    }

    DbSetCheckout checkout = new DbSetCheckout();

    DbSetCheckoutService checkoutService = new DbSetCheckoutService();

    public void checkout() {
        if (lbCustomerId.getText().isEmpty()) {
            alert.errorMessage("Please select the data first!");
        } else if (!b.checkBooking(Integer.valueOf(lbCustomerId.getText()))) {
            alert.errorMessage("This customer is not booking!");
        } else {
            checkoutForm.setVisible(true);
            lbDate.setText(String.valueOf(b.getDateByCustomerId(Integer.valueOf(lbCustomerId.getText()))));
            lbCustomerCheckoutId.setText(lbCustomerId.getText());

            tableViewCheckout.getItems().clear();
            tableViewServiceCheckOut.getItems().clear();

            colParticular.setCellValueFactory(new PropertyValueFactory<>("roomType"));
            colDay.setCellValueFactory(new PropertyValueFactory<>("days"));
            colCheckoutPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

            colServiceCheckOut.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
            colQuantityCheckOut.setCellValueFactory(new PropertyValueFactory<>("serviceQuantity"));
            colServicePriceCheckOut.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
            colServiceAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

            List<Checkout> checkoutList = checkout.findByCustomerId(Integer.valueOf(lbCustomerId.getText()));
            tableViewCheckout.setItems(FXCollections.observableArrayList(checkoutList));

            List<CheckOutService> checkOutServiceList = checkoutService.findByCustomerId(Integer.valueOf(lbCustomerId.getText()));
            tableViewServiceCheckOut.setItems(FXCollections.observableArrayList(checkOutServiceList));
            double totalRoomAmount = 0;
            double totalServiceAmount = 0;

            for (Checkout item : checkoutList) {
                totalRoomAmount += item.getDays() * item.getPrice();
            }

            lbRoomFee.setText(String.valueOf(totalRoomAmount));

            for (CheckOutService item : checkOutServiceList) {
                totalServiceAmount += item.getAmount();
            }

            if (b.checkRegular(Integer.valueOf(lbCustomerCheckoutId.getText()))) {
                lbDiscount.setText("10");
            }

            lbServiceFee.setText(String.valueOf(totalServiceAmount));

            double roomFee = Double.valueOf(lbRoomFee.getText());
            double serviceFee = Double.valueOf(lbServiceFee.getText());

            if (lbDiscount.getText().isEmpty()) {
                lbTotalAmount.setText(String.valueOf(roomFee + serviceFee));
            } else {
                lbTotalAmount.setText(String.valueOf((roomFee + serviceFee) - ((roomFee + serviceFee) * 10 / 100)));
            }


        }
    }

    public void cancelCheckout() {
        checkoutForm.setVisible(false);
    }

    public void showInvoiceData() {
        tableViewInvoice.getItems().clear();

        colInvoiceId.setCellValueFactory(new PropertyValueFactory<>("invoiceId"));
        colCustomerInvoiceId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colTotalRoom.setCellValueFactory(new PropertyValueFactory<>("totalRoomFee"));
        colTotalService.setCellValueFactory(new PropertyValueFactory<>("totalServiceFee"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colInvoiceDate.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));

        tableViewInvoice.setItems(i.findAll());
    }

    DbSetInvoice i = new DbSetInvoice();

    public void pay() {
        if (lbDiscount.getText().isEmpty()) {
            try {
                i.add(new Invoice(Integer.valueOf(lbCustomerCheckoutId.getText()), Double.valueOf(lbRoomFee.getText()), Double.valueOf(lbServiceFee.getText()),
                        Double.valueOf(lbTotalAmount.getText()),
                        new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(lbDate.getText()).getTime())));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            try {
                i.add(new Invoice(Integer.valueOf(lbCustomerCheckoutId.getText()), Double.valueOf(lbRoomFee.getText()), Double.valueOf(lbServiceFee.getText()),
                        Double.valueOf(lbDiscount.getText()), Double.valueOf(lbTotalAmount.getText()),
                        new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(lbDate.getText()).getTime())));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
        bs.delete(Integer.valueOf(lbCustomerCheckoutId.getText()));
        b.updateStatus(Integer.valueOf(lbCustomerCheckoutId.getText()));
        alert.successMessage("Payment successful!");
        showInvoiceData();
        invoicesForm.setVisible(true);
    }

    public void countBooking() {
        lbTodayBook.setText(String.valueOf(b.countBooking(new java.sql.Date(new Date().getTime()))));
    }

    public void incomeToday() {
        lbTodayIncome.setText(String.valueOf(i.incomeToday(new java.sql.Date(new Date().getTime()))));
    }

    public void totalIncome() {
        lbTotalIncome.setText(String.valueOf(i.totalIncome()));
    }

    public void chart() {
        areaChartDataIncome.getData().clear();

        areaChartDataIncome.getData().add(i.getChart());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countBooking();
        incomeToday();
        totalIncome();
        chart();
        roomType();
        availableRoomType();
        serviceName();
//        searchCustomer();
        btnDashboard.setStyle("-fx-background-color: linear-gradient(to bottom right, #5068c9, #bc59e4)");
    }
}