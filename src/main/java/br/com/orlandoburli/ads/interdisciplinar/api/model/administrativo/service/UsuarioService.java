package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.dto.UsuarioDTO;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.entities.Usuario;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.repositories.UsuarioRepository;
import br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.requests.UsuarioConsultaRequest;
import br.com.orlandoburli.ads.interdisciplinar.api.model.exceptions.BusinessException;
import br.com.orlandoburli.ads.interdisciplinar.api.model.geral.ConsultaResponse;
import br.com.orlandoburli.ads.interdisciplinar.api.utils.ValidatorUtils;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private ValidatorUtils utils;

	private Usuario insert(Usuario u) throws BusinessException {
		this.utils.validate(u);

		return this.repository.save(u);
	}

	public UsuarioDTO insert(UsuarioDTO usuario) throws BusinessException {
		return this.conversionService.convert(this.insert(this.conversionService.convert(usuario, Usuario.class)),
				UsuarioDTO.class);
	}

	private Usuario update(Usuario u) throws BusinessException {
		this.utils.validate(u);

		return this.repository.save(u);
	}

	public UsuarioDTO update(UsuarioDTO usuario) throws BusinessException {
		return this.conversionService.convert(this.update(this.conversionService.convert(usuario, Usuario.class)),
				UsuarioDTO.class);
	}

	public void delete(Integer id) {
		this.repository.findById(id).ifPresent(u -> this.repository.delete(u));
	}

	public Optional<UsuarioDTO> get(Integer id) {
		final Optional<Usuario> op = this.repository.findById(id);

		if (op.isPresent()) {
			return Optional.of(this.conversionService.convert(op.get(), UsuarioDTO.class));
		}

		return Optional.empty();
	}

	public ConsultaResponse<UsuarioDTO> pesquisar(UsuarioConsultaRequest request) {
		final String filtroString = ("%" + (request.getFiltro() == null ? "" : request.getFiltro().trim()) + "%")
				.replace(" ", "%").toUpperCase();

		final Direction direction = StringUtils.isEmpty(request.getDirecao()) ? Direction.ASC
				: Direction.valueOf(request.getDirecao().toUpperCase());

		final String ordenar = StringUtils.isEmpty(request.getOrdenar()) ? "nome" : request.getOrdenar();

		final Page<Usuario> page = this.repository.findByNomeOrEmailOrCpf(filtroString,
				PageRequest.of(request.getPageNumber(), request.getPageSize(), Sort.by(direction, ordenar)));

		return new ConsultaResponse<>(page.getContent().stream().map(a -> {
			final UsuarioDTO dto = this.conversionService.convert(a, UsuarioDTO.class);
			dto.setSenha(null);
			return dto;
		}).collect(Collectors.toList()), page.getTotalElements(), page.getTotalPages());
	}
}
