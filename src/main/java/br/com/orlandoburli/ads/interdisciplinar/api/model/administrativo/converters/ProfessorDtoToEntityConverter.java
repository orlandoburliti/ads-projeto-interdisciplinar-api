package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.ProfessorDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Professor;

@Component
public class ProfessorDtoToEntityConverter implements Converter<ProfessorDTO, Professor> {

	@Override
	public Professor convert(ProfessorDTO source) {
		final Professor destino = new Professor();

		destino.setId(source.getId());
		destino.setNome(source.getNome());
		destino.setEmail(source.getEmail());
		destino.setCpf(source.getCpf());
		destino.setSenha(source.getSenha());

		return destino;
	}

}
