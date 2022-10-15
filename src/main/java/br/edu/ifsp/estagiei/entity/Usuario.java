package br.edu.ifsp.estagiei.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.ifsp.estagiei.constants.TipoUsuarioEnum;
import br.edu.ifsp.estagiei.entity.listener.Auditavel;
import br.edu.ifsp.estagiei.entity.listener.AuditoriaListener;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditoriaListener.class)
@Table(name = "tb_usuario")
public class Usuario implements UserDetails, Auditavel {

	private static final long serialVersionUID = 8024417533446604625L;

	@Id
	@SequenceGenerator(name = "tb_usuario_cod_usuario_seq", sequenceName = "tb_usuario_cod_usuario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_usuario_cod_usuario_seq")
	@Column(name = "cod_usuario", updatable = false, nullable = false)
	private Long codUsuario;
	@Column(name = "senha")
	private String senha;
	@Column(name = "email")
	private String email;
	@Column(name = "avatar")
	private String avatar;
	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;
	@Column(name = "tip_usuario")
	private TipoUsuarioEnum tipoUsuario;
	@Embedded
	private Auditoria auditoria;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REMOVE })
	@JsonIgnore
	private Pessoa pessoa;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REMOVE })
	@JoinColumn(name = "cod_empresa", referencedColumnName = "cod_empresa", insertable = false, updatable = false)
	private Empresa empresa;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_usu_perm", joinColumns = @JoinColumn(name = "cod_usuario"), inverseJoinColumns = @JoinColumn(name = "cod_permissao"))
	private Set<Permissao> permissoes = new HashSet<>();

	@Override
	public int hashCode() {
		return Objects.hash(codUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(codUsuario, other.codUsuario);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Permissao permissao : permissoes) {
			authorities.add(new SimpleGrantedAuthority(permissao.getDescricao()));
		}
		return authorities;
	}

	public void addPermissao(Permissao permissao) {
		this.permissoes.add(permissao);
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
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

	public boolean hasEmpresa() {
		return this.empresa != null;
	}

	public boolean hasEmail() {
		return EstagieiUtils.isNotEmptyOrNull(email);
	}
}
