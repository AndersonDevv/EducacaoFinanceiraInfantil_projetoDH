package br.com.plataforma_financeira.plataforma_financeira.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.plataforma_financeira.plataforma_financeira.entity.ERole;
import br.com.plataforma_financeira.plataforma_financeira.entity.Role;
import br.com.plataforma_financeira.plataforma_financeira.entity.Usuario;
import br.com.plataforma_financeira.plataforma_financeira.payload.request.LoginRequest;
import br.com.plataforma_financeira.plataforma_financeira.payload.request.SignupRequest;
import br.com.plataforma_financeira.plataforma_financeira.payload.response.JwtResponse;
import br.com.plataforma_financeira.plataforma_financeira.payload.response.MessageResponse;
import br.com.plataforma_financeira.plataforma_financeira.repository.RoleRepository;
import br.com.plataforma_financeira.plataforma_financeira.repository.UsuarioRepository;
import br.com.plataforma_financeira.plataforma_financeira.secutiry.jwt.JwtUtils;
import br.com.plataforma_financeira.plataforma_financeira.service.UserDetailsImpl;
import br.com.plataforma_financeira.plataforma_financeira.service.UsuarioService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class UsuarioController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private UsuarioService usuarioService;

	@CrossOrigin
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getUsu_senha()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@CrossOrigin
	@PostMapping("/usuario")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Usuario user = new Usuario(
				             signUpRequest.getUsu_cpf(),
				             signUpRequest.getUsu_nome(), 
							 signUpRequest.getEmail(),
							 signUpRequest.getUsu_cep(),
							 signUpRequest.getUsu_tipo_endereco(),							 
							 signUpRequest.getUsu_nome_end(),
							 signUpRequest.getUsu_numero(),
							 signUpRequest.getUsu_bairro(),
							 signUpRequest.getUsu_complemento(),
							 signUpRequest.getUsu_cidade(),	
							 signUpRequest.getUsu_uf(),
							 signUpRequest.getUsu_tel_celular(),
							 signUpRequest.getUsu_tel_fixo(),	
							 signUpRequest.getUsu_status(),
							 encoder.encode(signUpRequest.getSenha()));
		
		
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "dep":
					Role modRole = roleRepository.findByName(ERole.ROLE_DEPENDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getUsu_id()).toUri();
		return ResponseEntity.ok(user);// ResponseEntity.created(uri).build();//ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	@CrossOrigin	
	@PutMapping("/usuario/{usu_id}")	
	public ResponseEntity<Usuario> updateUser(@RequestBody SignupRequest signUpRequest, @PathVariable Long usu_id) {
	
		
		Usuario user = new Usuario(
				             signUpRequest.getUsu_id(),
				             signUpRequest.getUsu_cpf(),
				             signUpRequest.getUsu_nome(), 
							 signUpRequest.getEmail(),
							 signUpRequest.getUsu_cep(),
							 signUpRequest.getUsu_tipo_endereco(),							 
							 signUpRequest.getUsu_nome_end(),
							 signUpRequest.getUsu_numero(),
							 signUpRequest.getUsu_bairro(),
							 signUpRequest.getUsu_complemento(),
							 signUpRequest.getUsu_cidade(),	
							 signUpRequest.getUsu_uf(),
							 signUpRequest.getUsu_tel_celular(),
							 signUpRequest.getUsu_tel_fixo(),	
							 signUpRequest.getUsu_status(),
							 encoder.encode(signUpRequest.getSenha()));		
		
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "dep":
					Role modRole = roleRepository.findByName(ERole.ROLE_DEPENDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		usuarioService.buscarUsuarioPorId(usu_id);
		userRepository.save(user);	
		return ResponseEntity.ok(user);
	}
	
	
	
	
	
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/usuario")
	public List<Usuario> getUsuarios(){
		List<Usuario> usuarios = usuarioService.findAll();
		return usuarios; 
	}
	
	@CrossOrigin
	@GetMapping("/usuario/email/{email}")
	public Usuario buscarUsuarioPorEmail(@PathVariable String email){
		Usuario usuario = usuarioService.findByEmail(email);
		return usuario; 
	}
	
	@CrossOrigin
	@GetMapping("/usuario/{usu_id}")
	public Usuario buscarUsuarioId(@PathVariable Long usu_id) {
		Usuario usuario  = usuarioService.buscarUsuarioPorId(usu_id);
		return usuario;
	}	
	
	
	@CrossOrigin
	@DeleteMapping("/usuario/{usu_id}")
	public ResponseEntity<Void> delete(@PathVariable Long usu_id){
		usuarioService.deletarUsuario(usu_id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/usuarioo/{usu_id}")	
    public ResponseEntity<Void> updateUsuario(@RequestBody Usuario usuario, @PathVariable Long usu_id) {				
		usuario.setUsu_id(usu_id);
		usuario = usuarioService.update(usuario);				
		return ResponseEntity.noContent().build();
		
	}
	
	
	
}
