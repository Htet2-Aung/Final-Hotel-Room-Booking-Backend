package com.hostmdy.hotelbooking.domain;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertFalse.List;


@Entity
@Table(name = "payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private  String holderName;
	
	private Double total;
	
	private Long cardNo;
	
	private Integer cvc;
	
	private String cardType;
	


	public Payment() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getHolderName() {
		return holderName;
	}


	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Long getCardNo() {
		return cardNo;
	}


	public void setCardNo(Long cardNo) {
		this.cardNo = cardNo;
	}


	public Integer getCvc() {
		return cvc;
	}


	public void setCvc(Integer cvc) {
		this.cvc = cvc;
	}


	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	@Override
	public String toString() {
		return "Payment [id=" + id + ", holderName=" + holderName + ", total=" + total + ", cardNo=" + cardNo + ", cvc="
				+ cvc + ", cardType=" + cardType + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(cardNo, cardType, cvc, holderName, id, total);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(cardNo, other.cardNo) && Objects.equals(cardType, other.cardType)
				&& Objects.equals(cvc, other.cvc) && Objects.equals(holderName, other.holderName)
				&& Objects.equals(id, other.id) && Objects.equals(total, other.total);
	}
	
}