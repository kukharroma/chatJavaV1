package com.chat.model;


import com.chat.model.authority.AuthorityBean;
import com.chat.model.authority.GrantedAuthorityImpl;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 *
 */
public class User implements UserDetails {

    /**
     * user's id
     */
    @Id
    private ObjectId id;

    /**
     * user's name (or login)
     */
    private String name;

    /**
     * user's password
     */
    private String password;

    /**
     * set of user's roles
     */
    private Set<AuthorityBean> authorities;

    private boolean isAccountNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;

    /**
     * show user is online or offline
     */
    private boolean online = false;

    /**
     * Returns a Collection of all roles which user has
     *
     * @return - list of roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null && !authorities.isEmpty()) {
            Collection<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
            for (AuthorityBean authorityBean : authorities) {
                GrantedAuthority grantedAuthority = new GrantedAuthorityImpl(authorityBean.getAuthority().toString());
                result.add(grantedAuthority);
            }
            return result;
        }
        return null;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public void setAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Set<AuthorityBean> authorities) {
        this.authorities = authorities;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

}
