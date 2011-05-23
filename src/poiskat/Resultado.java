package poiskat;

public class Resultado {
	private String titulo;
	private String descricao;
	private String url;
	public Resultado(String _titulo, String _descricao, String _url) {
		titulo = _titulo;
		descricao = _descricao;
		url = _url;
	}
	public Resultado() {
		titulo = "";
		descricao = "";
		url = "";
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
