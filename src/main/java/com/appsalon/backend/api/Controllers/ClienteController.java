package com.appsalon.backend.api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import com.appsalon.backend.api.Entities.*;
import com.appsalon.backend.api.Repositories.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NegocioRepository negocioRepository;

    // 🔹 GET ALL
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // 🔹 GET BY ID
    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Long id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(
                "No se encontró el cliente con id: " + id
            ));
    }

    // 🔹 CREATE
    @PostMapping
    public Cliente createCliente(
            @RequestParam Long idUsuario,
            @RequestParam Long idNegocio) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Negocio negocio = negocioRepository.findById(idNegocio)
            .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        cliente.setNegocio(negocio);
        cliente.setCli_fechaRegistro(LocalDate.now());

        return clienteRepository.save(cliente);
    }

    // 🔹 UPDATE (controlado)
    @PutMapping("/{id}")
    public Cliente updateCliente(
            @PathVariable Long id,
            @RequestParam Long idUsuario,
            @RequestParam Long idNegocio) {

        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Negocio negocio = negocioRepository.findById(idNegocio)
            .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        cliente.setUsuario(usuario);
        cliente.setNegocio(negocio);

        return clienteRepository.save(cliente);
    }

    // 🔹 DELETE
    @DeleteMapping("/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return "Cliente eliminado con éxito";
    }
}
