package com.osweld.dev.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="tokentype")
public class TokenType implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tokentype")
    private Long id;
    @NotEmpty(message = "Este campo no puede quedar vacio")
    private String tokentype;
    @Column(name = "create_at")
    private Date createAt;

    @PrePersist
    public void prePersist(){
        createAt = new Date();
    }

    public TokenType(Long id, @NotEmpty(message = "Este campo no puede quedar vacio") String tokentype, Date createAt) {
        this.id = id;
        this.tokentype = tokentype;
        this.createAt = createAt;
    }

    public TokenType() {
    }

    public TokenType(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTokentype() {
        return tokentype;
    }

    public void setTokentype(String tokentype) {
        this.tokentype = tokentype;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "TokenType{" +
                "id=" + id +
                ", tokentype='" + tokentype + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
