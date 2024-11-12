package api_clima.entities.enums;

public enum UserRole {
    ADMIN("admin"),
    BASIC("basic");

    public String userRole;

    UserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }
}
