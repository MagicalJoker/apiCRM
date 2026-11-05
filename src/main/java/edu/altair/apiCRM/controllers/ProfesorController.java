package edu.altair.apiCRM.controllers;

import edu.altair.apiCRM.dtos.ProfesorRequestDTO;
import edu.altair.apiCRM.dtos.ProfesorResponseDTO;
import edu.altair.apiCRM.services.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }
    // 1. READ ALL - GET /api/profesores
    @GetMapping
    public ResponseEntity<List<ProfesorResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(profesorService.obtenerTodos()); // HTTP 200 OK
    }

    // 2. READ BY ID - GET /api/profesores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(profesorService.obtenerPorId(id)); // HTTP 200 OK
    }

    // 3. CREATE - POST /api/profesores
    @PostMapping
    public ResponseEntity<ProfesorResponseDTO> crear(@RequestBody ProfesorRequestDTO requestDto) {
        ProfesorResponseDTO creado = profesorService.crear(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED) // HTTP 201 Created
                .body(creado);
    }

    // 4. UPDATE - PUT /api/profesores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProfesorResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody ProfesorRequestDTO requestDto) {
        ProfesorResponseDTO actualizado = profesorService.actualizar(id, requestDto);
        return ResponseEntity.ok(actualizado); // HTTP 200 OK
    }
   // 5. DELETE - DELETE /api/profesores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        profesorService.eliminar(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
