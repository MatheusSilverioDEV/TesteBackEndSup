import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String cpf;
	private String nome;
	private String username;
	private Integer idade;
	private List<Venda> compras;




	public Cliente(String cpf, String nome, String username, Integer idade) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.username = username;
		this.idade = idade;
		this.compras = new ArrayList<>();

	}

	//getters e setters

	public List<Venda> getCompras() {
		return compras;
	}

	public void setCompras(List<Venda> compras) {
		this.compras = compras;
	}

	public String getCpf() {
		return cpf;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	//métodos
	public void adicionarCompra(Venda compra) {
		compras.add(compra);
	}


}


