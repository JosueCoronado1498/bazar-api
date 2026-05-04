package com.jcoronado.bazar.controller;

import com.jcoronado.bazar.model.Rol;
import com.jcoronado.bazar.model.Usuario;
import com.jcoronado.bazar.repository.IUsuarioRepository;
import com.jcoronado.bazar.security.jwt.JwtService;
import com.jcoronado.bazar.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService userDetailsService;

    @PostMapping("/register")
    public String register(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol(Rol.USER);
        usuarioRepository.save(usuario);
        return "Usuario registrado";
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtService.generarToken(userDetails);
    }

}
