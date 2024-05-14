package org.example;

import java.io.Serializable;
import java.util.UUID;

public class Customer implements Serializable {
    private String name;
    private int num;
    private UUID id;
    public Customer(String name, int num, UUID id){
        this.name = name;
        this.num = num;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
