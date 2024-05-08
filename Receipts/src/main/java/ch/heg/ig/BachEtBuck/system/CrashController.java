package ch.heg.ig.BachEtBuck.system;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class CrashController {

	@GetMapping("/error")
	public ResponseEntity<?> triggerException() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Oups, cette page n'existe pas.");
	}

}
