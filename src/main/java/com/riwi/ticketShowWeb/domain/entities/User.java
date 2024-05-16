package com.riwi.ticketShowWeb.domain.entities;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity that represents a user")
public class User implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the user")
    private Long id;

    @Column(length = 45, nullable = false)
    @Schema(description =  "Name of the user")
    private String name;

    @Column(length = 45, nullable = false)
    @Schema(description =  "Password of the user")
    private String password;

    @Column(length = 50, nullable = false)
    @Schema(description =  "Email address of the user")
    private String email;

    @Temporal(TemporalType.DATE)
    @Schema(description =  "Date when the user was deleted")
    private Date deleteAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    @Schema(description = "Entity of the role the user is associated with")
    private Rol role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(this.role.getName()));
    }

    @Override
    public String getUsername() {

        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
