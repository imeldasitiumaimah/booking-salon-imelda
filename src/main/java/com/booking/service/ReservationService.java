package com.booking.service;


import com.booking.models.*;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private static List<Person> personList = PersonRepository.getAllPerson();
    private static List<Service> serviceList = ServiceRepository.getAllService();
    private static List<Reservation> reservationList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void createReservation() {
        System.out.printf("| %-4s | %-6s | %-15s | %-20s | %-10s |\n",
                "No.", "ID", "Nama", "Alamat", "Membership", "Uang");
        System.out.println("+================================================================================+");
        int num = 1;
        for (Person person : personList) {
            if (person instanceof Customer) {
                Customer customer = (Customer) person;
                System.out.printf("| %-4d | %-6s | %-15s | %-20s | %-10s | %-15s |\n",
                        num++, customer.getId(), customer.getName(), customer.getAddress(),
                        customer.getMember().getMembershipName(), customer.getWallet());
            }
        }

        System.out.print("Silahkan Masukkan Customer Id: ");
        String customerId = input.nextLine();

        System.out.printf("| %-4s | %-6s | %-15s | %-20s | %-10s |\n",
                "No.", "ID", "Nama", "Alamat", "Pengalaman");
        System.out.println("+======================================================================+");
        num = 1;
        for (Person person : personList) {
            if (person instanceof Employee) {
                Employee employee = (Employee) person;
                System.out.printf("| %-4d | %-6s | %-15s | %-20s | %-10d |\n",
                        num++, employee.getId(), employee.getName(), employee.getAddress(),
                        employee.getExperience());
            }
        }

        System.out.print("Silahkan Masukkan Employee Id: ");
        String employeeId = input.nextLine();

        System.out.printf("| %-4s | %-6s | %-15s | %-20s |\n",
                "No.", "ID", "Nama", "Harga");
        System.out.println("+=====================================================+");
        num = 1;
        for (Service service : serviceList) {
            System.out.printf("| %-4d | %-6s | %-15s | %-20s |\n",
                    num++, service.getServiceId(), service.getServiceName(), service.getPrice());
        }

        double totalBiaya = 0;
        boolean pilihLain = true;
        while (pilihLain) {
            System.out.print("\nSilahkan Masukkan Service Id: ");
            String serviceId = input.nextLine();
            Service selectedService = serviceList.stream()
                    .filter(service -> service.getServiceId().equals(serviceId))
                    .findFirst()
                    .orElse(null);
            if (selectedService != null) {
                totalBiaya += selectedService.getPrice();
                System.out.print("Ingin pilih service yang lain (Y/T)? ");
                String jawaban = input.nextLine();
                if (jawaban.equalsIgnoreCase("T")) {
                    pilihLain = false;
                }
            } else {
                System.out.println("Service Id tidak valid.");
            }
        }
        System.out.println("\nBooking Berhasil!");
        System.out.println("Total Biaya Booking : Rp. " + totalBiaya);
    }

    public static void getCustomerByCustomerId() {

    }

    public static void editReservationWorkstage() {
        
    }


    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
