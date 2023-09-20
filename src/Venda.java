import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class Venda {
	private Integer codigo;
	private List<Produto> itens;
	private Double valor;
	private Double comissaoSistema;
	private Empresa empresa;
	private Cliente cliente;

	public Venda(Integer codigo, List<Produto> itens, Double valor, Double comissaoSistema, Empresa empresa, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.itens = itens;
		this.valor = valor;
		this.comissaoSistema = comissaoSistema;
		this.empresa = empresa;
		this.cliente = cliente;
	}

	//getter and setter
	public Venda() {
		super();
	}

	public Integer getCodigo() {
		return codigo;
	}


	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public List<Produto> getItens() {
		return itens;
	}

	public void setItens(List<Produto> itens) {
		this.itens = itens;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getComissaoSistema() {
		return comissaoSistema;
	}

	public void setComissaoSistema(Double comissaoSistema) {
		this.comissaoSistema = comissaoSistema;
	}

	//métodos

	public boolean validarVenda() { // Verificar se todos os produtos da venda estão relacionados à empresa

		for (Produto produto : itens) {
			if (!produto.getEmpresa().getNome().equals(empresa.getNome())) {
				System.out.println("A empresa não pode vender o produto '" + produto.getNome() + "'.");
				try {
					throw new VendaInvalidaException("A empresa não pode vender o produto '" + produto.getNome() + "'.");
				} catch (VendaInvalidaException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return true;
	}

	public void confirmarVenda() {    // Continuar com a confirmação da venda
		if (validarVenda()) {
			System.out.println("Venda confirmada.");
			gerarResumoDaVenda(); // Chama o método para gerar o resumo da venda

			cliente.adicionarCompra(this); // adiciona a venda a lista de compras.


		} else {
			System.out.println("Venda não pode ser confirmada devido a produtos não relacionados à empresa.");
		}
	}

	public void gerarResumoDaVenda() {
		System.out.println("Resumo da Venda:");
		System.out.println("Codigo da Venda: " + codigo);
		System.out.println("Cliente: " + cliente.getNome());
		System.out.println("Itens da Venda:");

		double valorTotal = 0.0; // Inicializa o valor total como zero
		DecimalFormat df = new DecimalFormat("0.00"); // Define o formato de duas casas decimais para a moeda

		for (Produto produto : itens) {
			System.out.println("Produto: " + produto.getNome() + " Valor: R$" + df.format(produto.getPreco()) + " Quantidade: " + produto.getQuantidade() + " unidades");

			double valorParcial = produto.getQuantidade() * produto.getPreco(); // Calcula o valor parcial do produto (quantidade * preço) e adiciona ao valor total

			valorTotal += valorParcial;
		}

		double valorComissao = getComissaoSistema();

		System.out.println("Valor Total: R$" + df.format(valorTotal));
	}
	public BigDecimal calcularComissaoSistema() { // calcula as comissões no sistema
		BigDecimal valorBigDecimal = BigDecimal.valueOf(this.getValor());
		BigDecimal taxaBigDecimal = BigDecimal.valueOf(this.getEmpresa().getTaxa());
		return valorBigDecimal.multiply(taxaBigDecimal).setScale(2, RoundingMode.HALF_UP);
	}

}





