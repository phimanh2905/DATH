package model;
// Generated Jun 20, 2018 4:50:52 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PgOrderDetails generated by hbm2java
 */
@Entity
@Table(name = "pg_order_details", catalog = "dath")
public class PgOrderDetails implements java.io.Serializable {

	private Integer orderDetailId;
	private PgOrders pgOrders;
	private PgProducts pgProducts;
	private int amount;
	private int unitPrice;
	private int unitSale;

	public PgOrderDetails() {
	}

	public PgOrderDetails(PgOrders pgOrders, PgProducts pgProducts, int amount, int unitPrice, int unitSale) {
		this.pgOrders = pgOrders;
		this.pgProducts = pgProducts;
		this.amount = amount;
		this.unitPrice = unitPrice;
		this.unitSale = unitSale;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ORDER_DETAIL_ID", unique = true, nullable = false)
	public Integer getOrderDetailId() {
		return this.orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID", nullable = false)
	public PgOrders getPgOrders() {
		return this.pgOrders;
	}

	public void setPgOrders(PgOrders pgOrders) {
		this.pgOrders = pgOrders;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", nullable = false)
	public PgProducts getPgProducts() {
		return this.pgProducts;
	}

	public void setPgProducts(PgProducts pgProducts) {
		this.pgProducts = pgProducts;
	}

	@Column(name = "AMOUNT", nullable = false)
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Column(name = "UNIT_PRICE", nullable = false)
	public int getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "UNIT_SALE", nullable = false)
	public int getUnitSale() {
		return this.unitSale;
	}

	public void setUnitSale(int unitSale) {
		this.unitSale = unitSale;
	}

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PgOrderDetails){
            obj =(PgOrderDetails)obj;
            return this.getPgProducts().getProductId() == ((PgOrderDetails)obj).getPgProducts().getProductId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getPgProducts().getProductId()*1234; //To change body of generated methods, choose Tools | Templates.
    }
    
        
}
