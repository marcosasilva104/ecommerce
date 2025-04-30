package com.xpe.desaffio_final.ecommerce.service.validation;

import com.auth0.jwt.interfaces.Payload;

public @interface UserUpdateValid {
	String message() default "Validation error";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
