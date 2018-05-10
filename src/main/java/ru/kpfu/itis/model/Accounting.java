package ru.kpfu.itis.model;

import javax.persistence.*;

@Entity
@Table(name = "accounting")
public class Accounting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @OneToOne
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;
    @Column(nullable = false)
    private int count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
