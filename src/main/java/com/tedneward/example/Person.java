package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
    private int age;
    private String name;
    private double salary;
    private String ssn;
    private boolean propertyChangeFired = false;
    private static int count = 0;
    
    public Person() {
        this("", 0, 0.0d);
    }
    
    public Person(String n, int a, double s) {
        name = n;
        age = a;
        salary = s;
        ssn = "";
        count++;
    }
    
    public int getAge() {
        return age;
    }
    
    //throw an IllegalArgumentException when passed a value less than zero
    public void setAge(int age) {
        if(age >= 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("The value of age cannot be lower than 0.");
        }
        
    }
    
    public String getName() {
        return name;
    }
    
    //throw an IllegalArgumentException when passed a null String
    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("The value of name cannot be null.");
        } else {
            this.name = name;
        }
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public String getSSN() {
        return ssn;
    }
    public void setSSN(String value) {
        String old = ssn;
        ssn = value;
        
        this.pcs.firePropertyChange("ssn", old, value);
        propertyChangeFired = true;
    }
    public boolean getPropertyChangeFired() {
        return propertyChangeFired;
    }
    
    public void setPropertyChangeFired(boolean propertyChangeFired) {
        this.propertyChangeFired = propertyChangeFired;
    }
    
    public double calculateBonus() {
        return salary * 1.10;
    }
    
    public String becomeJudge() {
        return "The Honorable " + name;
    }
    
    public int timeWarp() {
        return age + 10;
    }
    
    //count the total number of Person instances created
    public static int count() {
        return count;
    }
    
    //override toString()
    public String toString() {
        return "[Person name:" + name + " age:" + age + " salary:" + salary +"]";
    }
    
    //override equals() and return true if two Person instances have the same name and age
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (other instanceof Person) {
            Person p = (Person)other;
            return this.name.equals(p.name) && this.age == p.age;
        }
        return false;
    }
    
    //Create an AgeComparator class that compares two Persons and arranges them by age
    public static class AgeComparator implements Comparator<Person> {
        public int compare(Person p1, Person p2) {
            return p1.age - p2.age;
        }
    }
    
    @Override
    //Make Person be Comparable, such that when I compare two Persons, they arrange themselves by salary in reverse order
    public int compareTo(Person other) {
        return (int) (other.getSalary() - this.getSalary());
    }
    
    /*Create a static method "getNewardFamily" that returns an ArrayList<Person> consisting of four Person objects: Ted, age 41, salary 250000; Charlotte, age 43, salary 150000; Michael, age 22, salary
    10000; and Matthew, age 15, salary 0.*/
    public static ArrayList<Person> getNewardFamily() {
        Person p1 = new Person("Ted", 41, 250000);
        Person p2 = new Person("Charlotte", 43, 150000);
        Person p3 = new Person("Michael", 22, 10000);
        Person p4 = new Person("Matthew", 15, 0);
        ArrayList people = new ArrayList<Person>();
        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);
        return people;
        
    }
    
    // PropertyChangeListener support; you shouldn't need to change any of
    // these two methods or the field
    //
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
    
}
