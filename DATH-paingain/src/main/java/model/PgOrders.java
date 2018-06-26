package model;
// Generated Jun 20, 2018 4:50:52 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PgOrders generated by hbm2java
 */
@Entity
@Table(name = "pg_orders", catalog = "dath")
public class PgOrders implements java.io.Serializable {

	private Integer orderId;
	private PgOrderStatus pgOrderStatus;
	private PgUsers pgUsers;
	private Date orderDate;
	private Date approvedDate;
	private String shipName;
	private String shipAddress;
	private String shipPhone;
	private Set<PgOrderDetails> pgOrderDetailses = new HashSet<PgOrderDetails>(0);

	public PgOrders() {
	}

	public PgOrders(PgUsers pgUsers) {
		this.pgUsers = pgUsers;
	}

	public PgOrders(PgOrderStatus pgOrderStatus, PgUsers pgUsers, Date orderDate, Date approvedDate, String shipName,
			String shipAddress, String shipPhone, Set<PgOrderDetails> pgOrderDetailses) {
		this.pgOrderStatus = pgOrderStatus;
		this.pgUsers = pgUsers;
		this.orderDate = orderDate;
		this.approvedDate = approvedDate;
		this.shipName = shipName;
		this.shipAddress = shipAddress;
		this.shipPhone = shipPhone;
		this.pgOrderDetailses = pgOrderDetailses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ORDER_ID", unique = true, nullable = false)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_STATUS_KEY")
	public PgOrderStatus getPgOrderStatus() {
		return this.pgOrderStatus;
	}

	public void setPgOrderStatus(PgOrderStatus pgOrderStatus) {
		this.pgOrderStatus = pgOrderStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	public PgUsers getPgUsers() {
		return this.pgUsers;
	}

	public void setPgUsers(PgUsers pgUsers) {
		this.pgUsers = pgUsers;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE", length = 26)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APPROVED_DATE", length = 26)
	public Date getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	@Column(name = "SHIP_NAME", length = 100)
	public String getShipName() {
		return this.shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	@Column(name = "SHIP_ADDRESS", length = 300)
	public String getShipAddress() {
		return this.shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	@Column(name = "SHIP_PHONE", length = 20)
	public String getShipPhone() {
		return this.shipPhone;
	}

	public void setShipPhone(String shipPhone) {
		this.shipPhone = shipPhone;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pgOrders")
	public Set<PgOrderDetails> getPgOrderDetailses() {
		return this.pgOrderDetailses;
	}

	public void setPgOrderDetailses(Set<PgOrderDetails> pgOrderDetailses) {
		this.pgOrderDetailses = pgOrderDetailses;
	}

}
