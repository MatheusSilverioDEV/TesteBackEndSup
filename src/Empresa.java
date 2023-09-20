import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Empresa {
	private Integer id;
	private String nome;
	private String cnpj;
	private Double taxa;
	private Double saldo;
	private List<Produto> produtos; // Lista para os produtos da empresa.


	public Empresa() {
		produtos = new ArrayList<>(); // Inicializa a lista no construtor
	}

	public Empresa(Integer id, String nome, String cnpj, Double taxa, Double saldo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.taxa = taxa;
		this.saldo = saldo;
		produtos = new ArrayList<>(); // inicializa a lista no construtor
	}
	// Getters e setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	// métodos

	public void criarProduto(Integer id, String nome, Integer quantidade, Double preco) { 	// Método para criar e adicionar um novo produto à lista de produtos da empresa

		Produto novoProduto = new Produto(id, nome, quantidade, preco, this); // 'this' se refere à própria empresa vinculando o produto
		produtos.add(novoProduto); // Adiciona o novo produto à lista de produtos da empresa
	}




	public void registrarVenda(Venda venda) {
		BigDecimal comissao = venda.calcularComissaoSistema();
		BigDecimal novoSaldo = BigDecimal.valueOf(this.getSaldo()).add(comissao);
		this.setSaldo(novoSaldo.doubleValue()); // Atualiza o saldo da empresa
	}
}
