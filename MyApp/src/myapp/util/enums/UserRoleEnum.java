/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp.util.enums;

/**
 *
 * @author HP
 */
public enum UserRoleEnum {
    //Constructor is called
    ADMIN("admin"), PASSENGER("passenger"); //enum constant list
    
    private final String roleName;

    //Constructor
    UserRoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static UserRoleEnum fromString(String roleName) {
        for (UserRoleEnum role : UserRoleEnum.values()) {
            if (role.roleName.equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant for role name: " + roleName);
    }
}
