package S105n1Exercici3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GestinarDirectori {

	private FileOutputStream fileOutputStream;
	private OutputStreamWriter outputStreamWriter;
	private BufferedWriter bufferedWriter;
	
	public GestinarDirectori() {
	
		fileOutputStream = null;
		outputStreamWriter = null;
		bufferedWriter = null;
	}
	
	private String obtenirDataModificacioFitxer (File file) {
		
		long ms = file.lastModified();
    	Date d = new Date(ms);
    	Calendar c = new GregorianCalendar(); 
    	c.setTime(d);
    	String dia = Integer.toString(c.get(Calendar.DATE));
    	String mes = Integer.toString(c.get(Calendar.MONTH));
    	String any = Integer.toString(c.get(Calendar.YEAR));
    	
    	return dia + "/" + mes + "/" + any;
	}
	
	private void obrirFitxerEscriptura(String nomFitxerEscriptura) {
		
		try {
			
        	fileOutputStream = new FileOutputStream(nomFitxerEscriptura);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
	}
	
	private void tancarFitxerEscriptura() {
		
		try {
			if (bufferedWriter != null) {
    			bufferedWriter.close();
    		}
			if( fileOutputStream != null){   
				fileOutputStream.close();     
			}      
			if( outputStreamWriter != null){   
				outputStreamWriter.close();     
			}      
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	private void imprimirArbreDirectorisFitxer (File directori, String separador) {
		
		if (bufferedWriter != null) {
			File[] fitxers = directori.listFiles(); 
		    if(fitxers != null){
		    	Arrays.sort(fitxers);
		    	
		        for (int i = 0; i < fitxers.length; i++) {
		            
		        	String data = obtenirDataModificacioFitxer(fitxers[i]);
		        	
		            if (fitxers[i].isDirectory()) {
		            	try {
			            	bufferedWriter.append (separador + "> (D) " + fitxers[i].getName() + " (Data modificació: " + data + ")");
			            	bufferedWriter.newLine();
			            } catch (Exception e) {
			            	e.printStackTrace();
			            }
		            	
		                String nuevo_separador = separador + "--";
		                imprimirArbreDirectorisFitxer(fitxers[i], nuevo_separador);
		            } else {
		            	try {
			            	bufferedWriter.append (separador + "> (F) " + fitxers[i].getName() + " (" + data + ")");
			            	bufferedWriter.newLine();
			            } catch (Exception e) {
			            	e.printStackTrace();
			            }
		            }
		        }
		    }
	    }
	}
	
	private void imprimirArbreDirectoris (File directori, String separador) {
		
		File[] fitxers = directori.listFiles();
		 
	    if(fitxers != null){
	    	Arrays.sort(fitxers);
	    	
	        for (int i = 0; i < fitxers.length; i++) {
	            
	        	String data = obtenirDataModificacioFitxer(fitxers[i]);
	        	
	            if (fitxers[i].isDirectory()) {
	            	System.out.println(separador + "> (D) " + fitxers[i].getName() + " (Data modificació: " + data + ")");
	                String nuevo_separador = separador + "--";
	                imprimirArbreDirectoris(fitxers[i], nuevo_separador);
	            } else {
	            	System.out.println(separador + "> (F) " + fitxers[i].getName() + " (" + data + ")");
	            }
	        }
	    }
	}

	public void llistarArbreDirectoris (String nomDirectori, String nomFitxerEscriptura) {
		
		File directori = new File(nomDirectori);
		
		if (directori.exists()) {
			obrirFitxerEscriptura(nomFitxerEscriptura);
			imprimirArbreDirectorisFitxer (directori, "-");
			tancarFitxerEscriptura();
		} else {
			System.out.println ("El directori no existeix");
		}
	}

	public void llistarArbreDirectoris (String nomDirectori) {
		
		File directori = new File(nomDirectori);
		
		if (directori.exists()) {
			imprimirArbreDirectoris (directori, "-");
		} else {
			System.out.println ("El directori no existeix");
		}
	}

	public void llistarDirectori(String nomDirectori) {
		
		File directori = new File(nomDirectori);
		
		if (directori.exists()) {
			
			File[] fitxers = directori.listFiles();
			
			if(fitxers != null){
				Arrays.sort(fitxers);
			
				for (int i = 0; i < fitxers.length; i++) {
		            System.out.println("> " + fitxers[i].getName());
				}
			}
			
		} else {
			System.out.println ("El directori no existeix");
		}
	}
}
