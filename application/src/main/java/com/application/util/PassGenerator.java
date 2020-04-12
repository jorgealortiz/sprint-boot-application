package com.application.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGenerator {

	public static void main(String ...args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);       
        // El String que mandamos al metodo encode es el password que queremos encriptar.
        // System.out.println(bCryptPasswordEncoder.encode("1234"));
        // Resultado: $2a$04$T3qSlBOcNbASty/rAndUlO9Nv1pEQ3owr40KsOQmU79C9VcbM2jn2

	}
}
