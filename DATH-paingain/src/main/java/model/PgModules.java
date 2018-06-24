package model;
// Generated Jun 20, 2018 4:50:52 PM by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PgModules generated by hbm2java
 */
@Entity
@Table(name = "pg_modules", catalog = "dath")
public class PgModules implements java.io.Serializable {

	private Integer moduleId;
	private String moduleName;
	private Integer parent;
	private Integer moduleStatus;
	private Set<PgRolePermission> pgRolePermissions = new HashSet<PgRolePermission>(0);

	public PgModules() {
	}

	public PgModules(String moduleName) {
		this.moduleName = moduleName;
	}

	public PgModules(String moduleName, Integer parent) {
		this.moduleName = moduleName;
		this.parent = parent;
	}
	public PgModules(Integer moduleId,String moduleName, Integer parent, Integer moduleStatus) {
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.parent = parent;
		this.moduleStatus = moduleStatus;
	}
	public PgModules(String moduleName, Integer parent, Integer moduleStatus, Set<PgRolePermission> pgRolePermissions) {
		this.moduleName = moduleName;
		this.parent = parent;
		this.moduleStatus = moduleStatus;
		this.pgRolePermissions = pgRolePermissions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "MODULE_ID", unique = true, nullable = false)
	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "MODULE_NAME", nullable = false, length = 100)
	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@Column(name = "PARENT")
	public Integer getParent() {
		return this.parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	@Column(name = "MODULE_STATUS")
	public Integer getModuleStatus() {
		return this.moduleStatus;
	}

	public void setModuleStatus(Integer moduleStatus) {
		this.moduleStatus = moduleStatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pgModules")
	public Set<PgRolePermission> getPgRolePermissions() {
		return this.pgRolePermissions;
	}

	public void setPgRolePermissions(Set<PgRolePermission> pgRolePermissions) {
		this.pgRolePermissions = pgRolePermissions;
	}

}
