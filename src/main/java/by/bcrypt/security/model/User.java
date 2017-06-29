package by.bcrypt.security.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(unique = true)
    private String username;

    private String passwd_hash;

    private boolean enabled;



    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<UserRoles> roleSet = new ArrayList<>();

    public User() {
    }

    public User(String username, String passwd_hash) {
        this.username = username;
        this.passwd_hash = passwd_hash;
        this.enabled = true;
    }

    public User(String username, String passwd_hash, Set<GrantedAuthority> grantedAuthorities) {


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<UserRoles> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(List<UserRoles> roleSet) {
        this.roleSet = roleSet;
    }

    public String getPasswd_hash() {
        return passwd_hash;
    }

    public void setPasswd_hash(String passwd_hash) {
        this.passwd_hash = passwd_hash;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
