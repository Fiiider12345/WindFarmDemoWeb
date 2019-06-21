package sk.fri.uniza.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.apache.commons.lang.ArrayUtils;
import sk.fri.uniza.auth.Role;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.*;


/**
 * Definuje objekt User, tak ako bude ulozeny v databaze
 */
public class User implements Principal {
    private static Random rand = new Random((new Date()).getTime());
    private Long id;
    private String userName;
    private Set<String> roles;
    private byte[] secrete;
    private byte[] salt = new byte[8];

    /**
     * Hibernate need default constructor
     */
    public User() {
    }

    /**
     * Konstruktor, nastavi hotnoty podla parametrov
     * @param id Long
     * @param userName String
     * @param roles Set
     * @param password String
     */
    public User(Long id, String userName, Set<String> roles, String password) {
        this.id = id;
        this.userName = userName;
        this.roles = roles;
        if (password != null)
            setNewPassword(password);
    }

    /**
     * Konstruktor, nastavi hotnoty podla parametrov
     * @param userName String
     * @param roles Set
     * @param password String
     */
    public User(String userName, Set<String> roles, String password) {
        this.userName = userName;
        this.roles = roles;
        if (password != null)
            setNewPassword(password);
    }

    /**
     * Return MD5 hashed password with salt.
     *
     * @param salt     byte array of random generater salt
     * @param password String representing password
     * @return MD5 hashed password with salt
     */

    public static byte[] generateHashSecrete(byte[] salt, String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return md.digest(ArrayUtils.addAll(salt, password.getBytes()));
    }

    /**
     * Autentikacia tokenu
     * @param jwtToken String
     * @param signingKey Key
     * @return User
     * @throws AuthenticationException chybna authentikacia
     */
    public static User getInstance(String jwtToken, Key signingKey) throws AuthenticationException {
        Claims claimsJws;
        try {
            claimsJws = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(jwtToken)
                    .getBody();
            //OK, we can trust this JWT
        } catch (SignatureException e) {
            //don't trust the JWT!
            throw new AuthenticationException("Failed to authenticate token!", e);
        }

        Set<String> roles = parseRolesClaim(claimsJws);
        return new UserBuilder()
                .setUserName(claimsJws.getSubject())
                .setRoles(roles)
                .setId(Long.valueOf(claimsJws.getId()))
                .createUser();
    }

    /**
     * Role
     * @param claims Claims
     * @return Set<String>
     * @throws AuthenticationException chybna authentikacia
     */
    private static Set<String> parseRolesClaim(Claims claims) throws AuthenticationException {
        String scopesObject = claims.get("scope", String.class);
        String[] scopes = {};
        if (scopesObject != null) {
            scopes = scopesObject.split(",");
        }
        return ImmutableSet.copyOf(Arrays.asList(scopes));
    }

    /**
     * getter
     * @return Role
     */
    @JsonIgnore
    public Role getSystemRoles() {
        return Role.getInstance();
    }

    /**
     * setter
     * @param userName String
     */
    @JsonProperty("userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /*
      Implement Principal interface
     */
    /**
     * getter
     * @return userName
     */
    @JsonProperty("userName")
    @Override
    public String getName() {
        return userName;
    }

    /**
     * getter
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * getter
     * @return roles
     */
    public Set<String> getRoles() {
        return roles;
    }

    /**
     * setter
     * @param roles Set
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    /**
     * getter
     * @return String
     */
    @JsonIgnore
    public String getRolesString() {
        return String.join(",", roles);
    }

    /**
     * setter, generuje hash secrete
     * @param newPassword String
     */
    public void setNewPassword(String newPassword) {
        rand.nextBytes(salt);
        secrete = User.generateHashSecrete(salt, newPassword);
    }

    /**
     * Test if entered password is correct
     * @param password string
     * @return boolean
     */
    public boolean testPassword(String password) {
        byte[] bytes = generateHashSecrete(this.salt, password);
        return Arrays.equals(bytes, secrete);

    }

    /**
     * builder
     * @param roles list
     * @return boolean
     */
    public boolean isPresentInSomeRole(List<String> roles) {

        return roles.stream().anyMatch(this.roles::contains);
    }

}
