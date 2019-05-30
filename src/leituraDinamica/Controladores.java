package leituraDinamica;
import java.io.IOException;

import arquivo.ArquivoTexto;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class Controladores {
	ArquivoTexto arquivo = new ArquivoTexto();
	long delay = 0;
	String caminho;
	String[] textoSeparado;
	Timeline timeline;
	AnimationTimer timer;
	String leituraTxt;
	int i = 0;
	boolean verificarPeriodo = false;
	boolean verificarNomeArquivo = false;
	boolean achouArquivo;
	Label visor = new Label();
	TextField selecionaArquivo = new TextField();
	Label lbSelecionaArquivo = new Label("Nome do Arquivo ");
	Label informacao = new Label("");
	Label lbtexto = new Label("Local de apresentação");
	Label lbPeriodo = new Label("Periodo    ");//restrição
	TextField txtPeriodo = new TextField();
	
	public void construtor(long _delay, TextField _selecionaArquivo, Label _visor, Label _informacao, Label _lbTexto, Label _lbPeriodo, TextField _txtPeriodo, String _caminho, String _leituraTxt, String[] _textoSeparado, boolean _verificarPeriodo, boolean _verificarNomeArquivo){ 
//(delay, selecionaArquivo, visor, informacao, lbtexto, lbPeriodo, txtPeriodo, caminho, leituraTxt, timeline, timer, textoSeparado, verificarPeriodo, verificarNomeArquivo)
		this.delay = _delay;
		this.caminho = _caminho;
		//this.timeline = _timeline;
		//this.timer = _timer;
		this.textoSeparado = _textoSeparado;
		this.verificarNomeArquivo = _verificarNomeArquivo;
		this.verificarPeriodo = _verificarPeriodo;
		this.leituraTxt = _leituraTxt;
		this.txtPeriodo  = _txtPeriodo;
		this.lbPeriodo  = _lbPeriodo;
		this.lbtexto  = _lbTexto;
		this.informacao = _informacao;
		this.visor = _visor;
		this.selecionaArquivo = _selecionaArquivo;
	}
	
	public void apresentacao(){
		//create a timeline
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		
		//You can add a specific action when each frame is started.
		timer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				if(i < textoSeparado.length){
					visor.setText(""+textoSeparado[i]);
					i++;
					try {
							Thread.sleep(delay);
					}catch (InterruptedException ex) {
						informacao.setText("FATAL! " + ex);
						System.exit(0);
					}
				}else{
					timeline.stop();
					timer.stop();
					timer.start();
					verificarNomeArquivo = false;
					verificarPeriodo = false;
				}
			}

		};

		//create a keyFrame, the keyValue is reached at time 5s
		Duration duration = Duration.millis(delay);

		//KeyFrame keyFrame = new KeyFrame(duration, onFinished , keyValueX, keyValueY);
		KeyFrame keyFrame = new KeyFrame(duration);

		//add the keyframe to the timeline
		timeline.getKeyFrames().add(keyFrame);

	}

	HBox adicionaBotoes(){
		HBox painelBotoes = new HBox();
		painelBotoes.setPadding(new Insets(8, 10, 8, 10));
		painelBotoes.setSpacing(10);
		//painelBotoes.setStyle("-fx-background-color: #FF8C00;");
		
		Button btnIniciar = new Button("Iniciar");
		btnIniciar.setOnAction(e-> {	
			try{
				if( ( (txtPeriodo.getText().length()) > 0) && ( (Long.parseLong(txtPeriodo.getText()) ) > 400) && ((Long.parseLong(txtPeriodo.getText())) < 3000)  && ( (selecionaArquivo.getText().length()) > 0)){
					//dominio de periodo: 400 < p < 3000
					if(txtPeriodo.getText().matches("^[0-9]*$")){
						recebePeriodo(txtPeriodo.getText());
						informacao.setText("");
						verificarNomeArquivo = true;
					}else{
						throw new NumberFormatException();
					}
				}else{
					informacao.setText("Periodo Invalido!");
					if(( (selecionaArquivo.getText().length()) == 0)) informacao.setText("Erro de Caminho!");
				}
				//arquivo
				caminho = selecionaArquivo.getText();
				if((selecionaArquivo.getText()) != "" && ( (selecionaArquivo.getText().length()) > 0)){
					leituraTxt = arquivo.lerArquivo(selecionaArquivo.getText());
					textoSeparado = arquivo.separaTexto(leituraTxt);
					verificarPeriodo = true;
				}else{
					throw new IOException();
				}
				if(verificarNomeArquivo && verificarPeriodo){
					timeline.play();
					timer.start();
				}
				
			}catch(NumberFormatException erro){
				informacao.setText("Digite apenas Numeros!");
			}catch(IOException erro){
				informacao.setText("Arquivo Não Existe!");
			}catch(Exception erro){
				informacao.setText("ERROR!");
			}
		});
		Button btnPausar = new Button("Pausar");
		btnPausar.setOnAction(e-> {
			timeline.pause();
			timer.stop();
		});
		Button btnParar = new Button("Parar");
		btnParar.setOnAction(e-> {
			timeline.stop();
			timer.stop();
			i=0;
			verificarNomeArquivo = false;
			verificarPeriodo = false;
			visor.setText("");
		});
		painelBotoes.getChildren().addAll(btnIniciar, btnPausar, btnParar);
		return painelBotoes;
	}

	public void recebePeriodo(String periodo){
			if(periodo.matches("^[0-9]*$")){
				delay = Long.parseLong(periodo);
			}else{
				throw new NumberFormatException();
			}	
	}
}
	