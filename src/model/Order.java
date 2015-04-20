package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")

public class Order {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;
	@ManyToOne
	private Customer customer;
	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="orders_id")
	private List<OrderLine> orderLines;
	
	private int state;

	public Order(){
		this.id = null;
		this.creationTime = new Date();
		this.customer = null;
		this.orderLines=new ArrayList<>();
		this.state=0;
	}
	
	public Order(Long id, Date creationTime, Customer customer) {
		this.id = id;
		this.creationTime = new Date();
		this.customer = customer;
		this.orderLines=new ArrayList<>();
		this.state=0;
	}
	
	public void addOrderLine(OrderLine orderLine){
		this.orderLines.add(orderLine);
	}
	
	public Long getId() {
		return id;
	}
	public Date getCreationTime() {
		return creationTime;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	} 
	
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", creationTime=" + creationTime
				+ ", customer=" + customer + ", orderLines=" + orderLines + "]";
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	
	
	
}
