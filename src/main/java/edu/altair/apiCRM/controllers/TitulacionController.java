package edu.altair.apiCRM.controllers;

import edu.altair.apiCRM.dtos.TitulacionRequestDTO;
import edu.altair.apiCRM.dtos.TitulacionResponseDTO;
import edu.altair.apiCRM.services.TitulacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titulaciones")
public class TitulacionController {

    private final TitulacionService titulacionService;

    public TitulacionController(TitulacionService titulacionService) {
        this.titulacionService = titulacionService;
    }
    // 1. READ ALL - GET /api/titulaciones
    @GetMapping
    public ResponseEntity<List<TitulacionResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(titulacionService.obtenerTodos()); // HTTP 200 OK
    }

    // 2. READ BY ID - GET /api/titulaciones/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TitulacionResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(titulacionService.obtenerPorId(id)); // HTTP 200 OK
    }

    // 3. CREATE - POST /api/titulaciones
    @PostMapping
    public ResponseEntity<TitulacionResponseDTO> crear(@RequestBody TitulacionRequestDTO requestDto) {
        TitulacionResponseDTO creado = titulacionService.crear(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED) // HTTP 201 Created
                .body(creado);
    }

    // 4. UPDATE - PUT /api/titulaciones/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TitulacionResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody TitulacionRequestDTO requestDto) {
        TitulacionResponseDTO actualizado = titulacionService.actualizar(id, requestDto);
        return ResponseEntity.ok(actualizado); // HTTP 200 OK
    }
   // 5. DELETE - DELETE /api/titulaciones/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        titulacionService.eliminar(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
