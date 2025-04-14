//package com.example.SpringS.security;
//
//import org.springframework.ldap.core.LdapTemplate;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomLdapUserDetailsService extends LdapUserDetailsService {
//
//    private final LdapTemplate ldapTemplate;
//
//    public CustomLdapUserDetailsService(LdapTemplate ldapTemplate) {
//        this.ldapTemplate = ldapTemplate;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Burada LDAP serverinə sorğu göndərib istifadəçi məlumatlarını çəkirik.
//        return User.withUsername(username)
//                .password("password")  // Şifrə kodlanmış formada olmalıdır
//                .roles("USER")  // İstifadəçinin rolu
//                .build();
//    }
//}
