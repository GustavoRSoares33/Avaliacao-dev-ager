package br.com.soc.sistema.infra;

public enum PeriodoDisponivel {
	
	MANHA("1", "Manhã"),
	TARDE("2", "Tarde"),
	AMBOS("3", "Ambos");

	private String codigo;
	private String descricao;
	
	private PeriodoDisponivel(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static PeriodoDisponivel buscarPorCodigo(String codigo) {
		if(codigo == null || codigo.trim().isEmpty()) {
			return null;
		}
		
		for(PeriodoDisponivel periodo : PeriodoDisponivel.values()) {
			if(periodo.getCodigo().equals(codigo)) {
				return periodo;
			}
		}
		throw new IllegalArgumentException("Código de periodo inválido: " + codigo);
	}
	
}
