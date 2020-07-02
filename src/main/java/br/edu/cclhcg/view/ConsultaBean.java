package br.edu.cclhcg.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.omnifaces.util.Messages;
import org.primefaces.model.chart.PieChartModel;

import br.edu.cclhcg.modelo.dto.SumarioTransacao;
import br.edu.cclhcg.modelo.servico.ServicoConsulta;

@Named
@ViewScoped
public class ConsultaBean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5332483559842177301L;
	
	@NotEmpty
	private String numCartao;
	
	@NotEmpty
	private String cpf;
	
	@NotEmpty
	private String credor;
	
	@NotNull
	private Date dataPesquisaInicial;
	
	@NotNull
	private Date dataPesquisaFinal;
	
	private List<SumarioTransacao> sumarioTransacoes;
	
	private PieChartModel pieModel1;
	private PieChartModel pieModel2;
	
	/**  **/
	@Inject
	private ServicoConsulta servicoConsulta;
	
	public void consultarUnicoCartao() {
		try {
			sumarioTransacoes = this.servicoConsulta.consultarUnicoCartao(numCartao, dataPesquisaInicial, dataPesquisaFinal);
			
			if (sumarioTransacoes.size() == 0) {
	        	Messages.addGlobalError("Não encontrado");
	        }
			
			this.createPieModel1();
			this.createPieModel2();
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void consultarCartoesCpf() {
		try {
			sumarioTransacoes = this.servicoConsulta.consultarCartoesCpf(cpf, dataPesquisaInicial, dataPesquisaFinal);
			
			if (sumarioTransacoes.size() == 0) {
	        	Messages.addGlobalError("Não encontrado");
	        }
			
			this.createPieModel1();
			this.createPieModel2();
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public void consultarTransacoesCredor() {
		try {
			sumarioTransacoes = this.servicoConsulta.consultarTransacoesCredor(credor, dataPesquisaInicial, dataPesquisaFinal);
			
			if (sumarioTransacoes.size() == 0) {
	        	Messages.addGlobalError("Não encontrado");
	        }
			
			this.createPieModel1();
			this.createPieModel2();
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	private void createPieModel1() {
        pieModel1 = new PieChartModel();        
        
        for (SumarioTransacao sumarioTransacao : sumarioTransacoes) {
        	pieModel1.set(sumarioTransacao.getTipo(), sumarioTransacao.getQtd());
		}

        pieModel1.setTitle("Quantidade");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
    }
	
	private void createPieModel2() {
        pieModel2 = new PieChartModel();
        
        for (SumarioTransacao sumarioTransacao : sumarioTransacoes) {
        	pieModel2.set(sumarioTransacao.getTipo(), sumarioTransacao.getValor());
		}

        pieModel2.setTitle("Valor");
        pieModel2.setLegendPosition("w");
        pieModel2.setShadow(false);
    }

	public String getNumCartao() {
		return numCartao;
	}

	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<SumarioTransacao> getSumarioTransacoes() {
		return sumarioTransacoes;
	}

	public void setSumarioTransacoes(List<SumarioTransacao> sumarioTransacoes) {
		this.sumarioTransacoes = sumarioTransacoes;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}


	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}

	public String getCredor() {
		return credor;
	}

	public void setCredor(String credor) {
		this.credor = credor;
	}

	public Date getDataPesquisaInicial() {
		return dataPesquisaInicial;
	}

	public void setDataPesquisaInicial(Date dataPesquisaInicial) {
		this.dataPesquisaInicial = dataPesquisaInicial;
	}

	public Date getDataPesquisaFinal() {
		return dataPesquisaFinal;
	}

	public void setDataPesquisaFinal(Date dataPesquisaFinal) {
		this.dataPesquisaFinal = dataPesquisaFinal;
	}
}
