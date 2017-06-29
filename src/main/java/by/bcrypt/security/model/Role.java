package by.bcrypt.security.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_role_id;

    private String alias ;

    private String frendly_name;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<UserRoles> userRoles = new ArrayList<>();


    public Role() {

    }

    public Role(String alias, String frendly_name) {
        this.alias = alias;
        this.frendly_name = frendly_name;

    }


    public Long getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(Long user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFrendly_name() {
        return frendly_name;
    }

    public void setFrendly_name(String frendly_name) {
        this.frendly_name = frendly_name;
    }

    public List<UserRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }
}
