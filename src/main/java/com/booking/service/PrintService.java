package com.booking.service;

import java.util.List;

import com.booking.models.*;

public class PrintService {
    List<Person> personList;

    public PrintService(List<Person> personList) {
        this.personList = personList;
    }

    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+=============================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), reservation.getReservationPrice(), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
    }

    public static void showAllCustomer(List<Person> personList) {
        int num = 1;
        System.out.printf("| %-4s | %-6s | %-15s | %-20s | %-10s |\n",
                "No.", "ID", "Nama", "Alamat", "Membership", "Uang");
        System.out.println("+=================================================================+");
        for (Person person : personList) {
            if (person instanceof Customer) {
                Customer customer = (Customer) person;
                System.out.printf("| %-4d | %-6s | %-15s | %-20s | %-10s | %-20s |\n",
                        num++, customer.getId(), customer.getName(), customer.getAddress(),
                        customer.getMember().getMembershipName(), customer.getWallet());
            }
        }
    }

    public static void showAllEmployee(List<Person> personList) {
        int num = 1;
        System.out.printf("| %-4s | %-6s | %-15s | %-20s | %-10s |\n",
                "No.", "ID", "Nama", "Alamat", "Pengalaman");
        System.out.println("+=================================================================+");
        for (Person person : personList) {
            if (person instanceof Employee) {
                Employee employee = (Employee) person;
                System.out.printf("| %-4d | %-6s | %-15s | %-20s | %-10d |\n",
                        num++, employee.getId(), employee.getName(), employee.getAddress(),
                        employee.getExperience());
            }
        }
    }

    public void showHistoryReservation(List<Reservation> reservationList){
        double totalProfit = 0;
        int num = 1;
        System.out.printf("| %-4s | %-6s | %-15s | %-25s | %-20s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Total Biaya", "Workstage");
        System.out.println("+===================================================================================+");
        for (Reservation reservation : reservationList) {
            System.out.printf("| %-4d | %-6s | %-15s | %-25s | %-20s | %-10s |\n",
                    num++, reservation.getReservationId(), reservation.getCustomer().getName(),
                    printServices(reservation.getServices()), reservation.getReservationPrice(),
                    reservation.getWorkstage());
        }
        System.out.println("Total Keuntungan: Rp. " + totalProfit);
    }
}
