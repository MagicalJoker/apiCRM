package edu.altair.apiCRM.controllers;

import edu.altair.apiCRM.dtos.CursoRequestDTO;
import edu.altair.apiCRM.dtos.CursoResponseDTO;
import edu.altair.apiCRM.services.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }
    // 1. READ ALL - GET /api/cursos
    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(cursoService.obtenerTodos()); // HTTP 200 OK
    }

    // 2. READ BY ID - GET /api/cursos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.obtenerPorId(id)); // HTTP 200 OK
    }

    // 3. CREATE - POST /api/cursos
    @PostMapping
    public ResponseEntity<CursoResponseDTO> crear(@RequestBody CursoRequestDTO requestDto) {
        CursoResponseDTO creado = cursoService.crear(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED) // HTTP 201 Created
                .body(creado);
    }

    // 4. UPDATE - PUT /api/cursos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody CursoRequestDTO requestDto) {
        CursoResponseDTO actualizado = cursoService.actualizar(id, requestDto);
        return ResponseEntity.ok(actualizado); // HTTP 200 OK
    }
   // 5. DELETE - DELETE /api/cursos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        cursoService.eliminar(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
