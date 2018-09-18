
import java.util.List;
import java.util.stream.Collectors;


public class GeradorObservacao { 

	//Texto prÃ© definido
	static final String NOTA = "Fatura %s de simples remessa: ";
		
	/**
	 * Gera observações, com texto pre-definido, incluindo os números, das notas fiscais, recebidos no parâmetro
	 * @param lista
	 * @return
	 */
	public String geraObservacao(List<Integer> lista) 
	{
		return !lista.isEmpty() ? this.retornaCodigos(lista) : "";
	}
	
	/**
	 * Retorna codigos concatenado e com texto formatado
	 * @param lista
	 * @return
	 */
	private String retornaCodigos(List<Integer> lista) {
		String textoNota =  this.formataTextoNota(lista.size());
		
		return this.montarTextoSaida(lista, textoNota);
	}

	/**
	 * Iteração e concatenação do texto de saída utilizando métodos funcionais 
	 * @param lista
	 * @param textoNota
	 * @return
	 */
	private String montarTextoSaida(List<Integer> lista, String textoNota) {
		// Padrão declarativo funcional para iterar e montar o texoto de saída
		String textoSaida = lista.stream().map(val -> val.toString()).collect(Collectors.joining(", ", textoNota, ".")).toString();

		StringBuffer sb = new StringBuffer(textoSaida);

		if(lista.size() > 1) {
			sb = sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, " e");
		}

		return sb.toString();
	}
	
	/**
	 * Formata texto plural ou singular de acordo com a quantidade de itens da lista
	 * @param lista
	 * @return
	 */
	private String formataTextoNota(int lista) {
		final String txtPlural = "das notas fiscais";
		final String txtSingular = "da nota fiscal";

		return String.format(NOTA, lista > 1 ? txtPlural : txtSingular);
	}
}