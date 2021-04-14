package net.alepuzio.restservice.server.jwt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtStub {

		private Logger logger = Logger.getLogger(this.getClass());

		@PostMapping("/auth/greetings")
		@ResponseBody
		public ResponseEntity<?> createAuthenticationToken(
				@RequestBody JwtRequest authenticationRequest) throws Exception {
			logger.info("createAuthenticationToken");
			//return null;
			throw new UnsupportedOperationException("createAuthenticationToken");
		}
}
