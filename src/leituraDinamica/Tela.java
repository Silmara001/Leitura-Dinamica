package leituraDinamica;
import java.io.File;
import arquivo.ArquivoTexto;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Tela extends Application{
	long delay;
	String caminho;
	String[] textoSeparado;
	String leituraTxt;
	int i = 0;
	boolean verificarPeriodo = false;
	boolean verificarNomeArquivo = false;
	File[] files = null;
	String achouArquivo;
	Label visor = new Label();
	TextField selecionaArquivo = new TextField();
	Label lbSelecionaArquivo = new Label("Nome do Arquivo ");
	Label lbtexto = new Label("Local de apresentação");
	Label lbPeriodo = new Label("Periodo    ");//restrição
	TextField txtPeriodo = new TextField();
	Button btnIniciar;
	Button btnPausar;
	Button btnParar;
	Label informacao = new Label("");
	Label ciclos = new Label("Leitura:");
	GridPane grade = new GridPane();
	BorderPane painelPrincipal = new BorderPane();
	ArquivoTexto arquivo = new ArquivoTexto();
	Controladores controle = new Controladores();
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage palco) throws Exception {
		try{
			palco.setTitle("Leitura Dinamica");		
			Group root = new Group();
			MenuBar menuBar = new MenuBar();   
			Menu menu = new Menu("Opções");
			//menu.getItems().add(new MenuItem("Abrir Arquivo"));
			menu.getItems().add(new MenuItem("Ajuda"));
			menu.getItems().add(new SeparatorMenuItem());
			menu.getItems().add(new MenuItem("Sair"));
			menuBar.getMenus().add(menu);
			menuBar.prefWidthProperty().bind(palco.widthProperty()); 
			root.getChildren().add(menuBar);
		
			painelPrincipal.setTop(menuBar);
			painelPrincipal.setCenter(grade);
		
			//Personalização unitaria
			palco.setHeight(500);
			palco.setWidth(375);
			grade.setPadding(new Insets(10, 10, 10, 10));
			visor.setPadding(new Insets(10, 10, 10, 10));
			lbPeriodo.setMinHeight(30);
			txtPeriodo.setMaxHeight(5);
			lbPeriodo.setFont(new Font("Arial Black", 12));
			lbSelecionaArquivo.setFont(new Font("Arial Black", 12));
			visor.setMinHeight(200);
			visor.setMinWidth(300);
			visor.setStyle("-fx-background-color: #F5FFFA;");
			visor.setFont(new Font("Times New Roman", 40));
			visor.setAlignment(Pos.CENTER);
			//.settForegroung(Color.yellow); // altera a cor da fonte
			//setBackground(Color.BLACK);  // altera a cor do fundo
			informacao.setPadding(new Insets(10, 10, 10, 10));
			ciclos.setPadding(new Insets(10, 10, 10, 10));
			informacao.setTextFill(Color.WHITE);
			painelPrincipal.setStyle("-fx-background-color: #FF6347;");
		
			//Wireframe
			controle.construtor(delay, selecionaArquivo, visor, informacao, lbtexto, lbPeriodo, txtPeriodo, caminho, leituraTxt, textoSeparado, verificarPeriodo, verificarNomeArquivo);
			HBox hboxmanipulacao = controle.adicionaBotoes();
			HBox hboxPeriodo = new HBox(lbPeriodo, txtPeriodo);
			HBox hboxCaminho = new HBox(lbSelecionaArquivo, selecionaArquivo);
			VBox vboxCampos = new VBox(hboxPeriodo, hboxCaminho);
			hboxmanipulacao.setPadding(new Insets(20, 0, 20, 10));
			grade.add(vboxCampos, 1, 1); //col lin
			grade.add(ciclos, 1, 2);
			grade.add(visor, 1, 3);
			grade.add(informacao, 1, 4);
			grade.add(hboxmanipulacao, 1, 5);
			grade.setAlignment(Pos.CENTER);
			
			controle.construtor(delay, selecionaArquivo, visor, informacao, lbtexto, lbPeriodo, txtPeriodo, caminho, leituraTxt, textoSeparado, verificarPeriodo, verificarNomeArquivo);
			controle.apresentacao();
		
			Scene cena = new Scene(painelPrincipal, 400, 400);
			palco.setScene(cena);
			palco.show();
		}catch(NumberFormatException erro){
   		 	informacao.setText("Periodo Informado invalido!");
   	 	}catch(Exception ex){
   	 		informacao.setText("Erro na leitura do arquivo!");
   	 	}
	}
}
