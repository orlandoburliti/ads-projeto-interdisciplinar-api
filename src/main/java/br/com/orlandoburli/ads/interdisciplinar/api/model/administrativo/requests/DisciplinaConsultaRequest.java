package br.com.orlandoburli.ads.interdisciplinar.api.model.administrativo.requests;

import java.io.Serializable;

public class DisciplinaConsultaRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String filtro;

	private String ordenar;

	private String direcao;

	private Integer pageNumber;

	private Integer pageSize;

	public String getFiltro() {
		return this.filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String getOrdenar() {
		return this.ordenar;
	}

	public void setOrdenar(String ordenar) {
		this.ordenar = ordenar;
	}

	public String getDirecao() {
		return this.direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}

	public Integer getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
