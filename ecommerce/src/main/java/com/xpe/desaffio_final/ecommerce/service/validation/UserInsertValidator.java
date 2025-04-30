package com.xpe.desaffio_final.ecommerce.service.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xpe.desaffio_final.ecommerce.controller.exeptions.FieldMessage;
import com.xpe.desaffio_final.ecommerce.dto.UsuarioInsertDTO;
import com.xpe.desaffio_final.ecommerce.repository.UsuarioRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UsuarioInsertDTO> {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UsuarioInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		var usuario = repository.findByLogin(dto.getLogin());
		if (usuario != null) {
			list.add(new FieldMessage("email", "Email já existe"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}