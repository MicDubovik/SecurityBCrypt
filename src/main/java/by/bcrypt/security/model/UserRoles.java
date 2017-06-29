package by.bcrypt.security.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_users_roles")
public class UserRoles implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRoles() {
    }

    public UserRoles(User user){

    }

    public UserRoles(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
