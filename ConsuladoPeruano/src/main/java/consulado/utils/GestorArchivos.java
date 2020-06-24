package consulado.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class GestorArchivos {
	
	public static String guardarArchivo(MultipartFile multiPart, String ruta, String prefijo_destino) {  
		// Obtenemos el nombre original del archivo.
		String nombreOriginal = multiPart.getOriginalFilename();
		nombreOriginal = nombreOriginal.replace(" ", "-");
		Path p = Paths.get(nombreOriginal);
		String name = p.getFileName().toString();	
		
		String nombreFinal = prefijo_destino+randomAlphaNumeric(8)+name;
		try {
			// Formamos el nombre del archivo para guardarlo en el disco duro.
			File imageFile = new File(ruta+ nombreFinal);
			//System.out.println("Archivo: " + imageFile.getAbsolutePath());
			//Guardamos fisicamente el archivo en HD.
			multiPart.transferTo(imageFile);
			return nombreFinal;
		} catch (IOException e) {
			//System.out.println("Error " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Metodo para generar una cadena aleatoria de longitud N
	 * @param count
	 * @return
	 */
	public static String randomAlphaNumeric(int count) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));
		}
		return builder.toString();
	}

}
