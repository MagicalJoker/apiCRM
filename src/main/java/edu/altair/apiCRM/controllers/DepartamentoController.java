package edu.altair.apiCRM.controllers;

import edu.altair.apiCRM.dtos.DepartamentoRequestDTO;
import edu.altair.apiCRM.dtos.DepartamentoResponseDTO;
import edu.altair.apiCRM.services.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }
    // 1. READ ALL - GET /api/departamentos
    @GetMapping
    public ResponseEntity<List<DepartamentoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(departamentoService.obtenerTodos()); // HTTP 200 OK
    }

    // 2. READ BY ID - GET /api/departamentos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.obtenerPorId(id)); // HTTP 200 OK
    }

    // 3. CREATE - POST /api/departamentos
    @PostMapping
    public ResponseEntity<DepartamentoResponseDTO> crear(@RequestBody DepartamentoRequestDTO requestDto) {
        DepartamentoResponseDTO creado = departamentoService.crear(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED) // HTTP 201 Created
                .body(creado);
    }

    // 4. UPDATE - PUT /api/departamentos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody DepartamentoRequestDTO requestDto) {
        DepartamentoResponseDTO actualizado = departamentoService.actualizar(id, requestDto);
        return ResponseEntity.ok(actualizado); // HTTP 200 OK
    }
   // 5. DELETE - DELETE /api/departamentos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        departamentoService.eliminar(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
