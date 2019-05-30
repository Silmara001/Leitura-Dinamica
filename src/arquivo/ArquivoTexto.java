package arquivo;

//Arquivotexto gravar
import java.io.File;
import java.io.FileFilter;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.PrintWriter;
//leitura
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//Date e hr
//import java.util.Date;
//import java.util.Scanner;
import java.util.regex.Pattern;

public class ArquivoTexto {
    public String lerArquivo(String nomeArq) throws IOException{
		try{
			Path caminho = Paths.get(nomeArq);
			byte[] texto = Files.readAllBytes(caminho);
			String leituraTxt = new String(texto);
			
			return leituraTxt;
		} catch(IOException e){
			throw new IOException();
			//return "ERROR!";
		}
	}
  
    public Path abrirArquivo(String trajeto){
    	Path caminho = Paths.get(trajeto);
    	return caminho;
    }
    
   /* public String recebeTxt(){
        Scanner sc = new Scanner(System.in);
        System.out.println(" -- Digite o texto: "); 
        String txt = sc.nextLine();  
        return txt;
     }*/
    
    public void mostrarConteudo(String txt){
    	System.out.println(txt);
	}
    
    public String[] separaTexto(String txt){
        String separador = ".,;: \n";  // \n representa quebra de linha
        String[] lista = txt.split("[" + Pattern.quote(separador) + "]"); //separa string de acordo com os caracteres de SEPAARADOR
        //int n = lista.length;
        return lista;
    }
    
	public File[] listaArq(){ 
        File f = new File("src/"); //-- O diretório
        //-- Lista de arquivos .java...
        File[] files = f.listFiles (new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".txt");
            } 
        });
        for (int i = 0; i < files.length; ++i) {
            System.out.println (files[i]);
        }
        return files;
    }

}

