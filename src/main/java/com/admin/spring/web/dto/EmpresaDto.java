package com.admin.spring.web.dto;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.admin.spring.api.endereco.Endereco;

public class EmpresaDto {
    
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE })
	private Endereco endereco;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nome;
	private String email;
	private String telefone;



    public EmpresaDto() {
    }

    public EmpresaDto(Endereco endereco, Long id, String nome, String email, String telefone) {
        this.endereco = endereco;
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EmpresaDto endereco(Endereco endereco) {
        setEndereco(endereco);
        return this;
    }

    public EmpresaDto id(Long id) {
        setId(id);
        return this;
    }

    public EmpresaDto nome(String nome) {
        setNome(nome);
        return this;
    }

    public EmpresaDto email(String email) {
        setEmail(email);
        return this;
    }

    public EmpresaDto telefone(String telefone) {
        setTelefone(telefone);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EmpresaDto)) {
            return false;
        }
        EmpresaDto empresaDto = (EmpresaDto) o;
        return Objects.equals(endereco, empresaDto.endereco) && Objects.equals(id, empresaDto.id) && Objects.equals(nome, empresaDto.nome) && Objects.equals(email, empresaDto.email) && Objects.equals(telefone, empresaDto.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endereco, id, nome, email, telefone);
    }

    @Override
    public String toString() {
        return "{" +
            " endereco='" + getEndereco() + "'" +
            ", id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", email='" + getEmail() + "'" +
            ", telefone='" + getTelefone() + "'" +
            "}";
    }

}
