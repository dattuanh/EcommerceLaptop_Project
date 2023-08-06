/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin.controller.error;

/**
 *
 * @author Giang Minh
 */
public enum RepairStatusEnum {
        
    Pending, InProgress, Complete, Fail;
    
    public static RepairStatusEnum getRepairStatusEnum(int status) {
        switch (status) {
            case 1:
                return Pending;
            case 2:
                return InProgress;
            case 3:
                return Complete;
            case 4:
                return Fail;
            default:
                throw new AssertionError();
        }
    }
    
    public static String getRepairStatusString(int status) {
        switch (status) {
            case 1:
                return "Pending";
            case 2:
                return "In Progress";
            case 3:
                return "Complete";
            case 4:
                return "Fail";
            default:
                throw new AssertionError();
        }
    }
}
