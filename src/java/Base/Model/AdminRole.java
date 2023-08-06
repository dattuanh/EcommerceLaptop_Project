/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base.Model;

/**
 *
 * @author Giang Minh
 */
public class AdminRole {
    private Account accountId;
    private Role roleId;
    private Account modifiedBy;
    private String modifiedHistory;
    private boolean status;

    public AdminRole() {
    }

    public AdminRole(Account accountId, Role roleId, Account modifiedBy, String modifiedHistory, boolean status) {
        this.accountId = accountId;
        this.roleId = roleId;
        this.modifiedBy = modifiedBy;
        this.modifiedHistory = modifiedHistory;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public Account getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Account modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedHistory() {
        return modifiedHistory;
    }

    public void setModifiedHistory(String modifiedHistory) {
        this.modifiedHistory = modifiedHistory;
    }
    
}
