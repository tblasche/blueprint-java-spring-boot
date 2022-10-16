package de.tblasche.blueprintjavaspringboot.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @GetMapping("/alive")
  public ResponseEntity<Void> isAlive() {
    return ResponseEntity.ok().build();
  }

  @GetMapping("/ready")
  public ResponseEntity<Void> isReady() {
    return ResponseEntity.ok().build();
  }
}
