package me.zhukov.ivanbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Entity
@Table(name = "account")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "amount")
    private int amount;
    @JoinColumn(name = "owner_id")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JsonIgnore
    private BankUser owner;
    @JoinColumn(name = "bank_id")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JsonIgnore
    private Bank bank;

    public void setOwner(BankUser bankUser) {
        this.owner = Optional.ofNullable(bankUser)
                .orElseThrow(() -> new IllegalArgumentException("Can`t remove user account"));
    }

    public void setBank(Bank bank) {
        this.bank = Optional.ofNullable(bank)
                .orElseThrow(() -> new IllegalArgumentException("Can`t remove bank"));
    }

    public void increaseAmount(int amount) {
        this.amount += amount;
    }

    public void decreaseAmount(int amount) {
        this.amount -= amount;
    }
}
