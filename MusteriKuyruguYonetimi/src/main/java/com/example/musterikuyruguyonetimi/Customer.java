package com.example.musterikuyruguyonetimi;

public class Customer {

    private String name;
    private String surname;
    private int age;
    private int phoneNumber;
    private int idNumber;
    private String transaction;
    private int number;

    public Customer(String name, String surname, int age, int phoneNumber, int idNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
