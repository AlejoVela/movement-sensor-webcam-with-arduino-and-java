package Modelo;

import Vista.InOut;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.util.Date;

public class Procesos {
    Webcam webcam = Webcam.getDefault();
    InOut ov = new InOut();
    Date fecha;
    int cont = 0;
    
    //Objetos Manejo de archivos
    File archivo;
    GestionA gestion = new GestionA();

    public void conectarCamara() {
        
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        if (webcam != null) {
            WebcamPanel panel = new WebcamPanel(webcam);
            panel.setFPSDisplayed(true);
            panel.setDisplayDebugInfo(true);
            panel.setImageSizeDisplayed(true);
            panel.setMirrored(true);
            
            JFrame window = new JFrame("Web cam activa!!");
            window.add(panel);
            window.setResizable(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.pack();
            window.setVisible(true);
        } else {
            ov.mostrar("No se ha detectado ninguna camara en este dispositivop");
        }
    }

    public void comprobarDistancia(String dintancia, JTextArea datosDistancia, JScrollBar vertical) throws IOException {
        int distance = Integer.parseInt(dintancia);
        fecha = new Date();
        if (distance >= 1 && distance <= 10) {
            //enviamos datos al jscrollpanel
            datosDistancia.setText(datosDistancia.getText() + distance +" cm                "+fecha+"\nTomando Capturas!!\n");
            //creamos y enviamos datos al archivo
            CrearArchivo(datosDistancia);
            tomarFoto();
        } else if (distance >= 1 && distance <= 15) {
            datosDistancia.setText(datosDistancia.getText() + distance +" cm                "+fecha+"\n¡el intruso esta a menos de 15 centimetros del dispositivo!\n");
        }else if(distance >= 15){
            datosDistancia.setText(datosDistancia.getText() + distance +" cm\n");     
        }
        
        //acomodamos la barra del scrolbar
        vertical.setValue( vertical.getMaximum() );
    }
    
    public void CrearArchivo(JTextArea datosDistancia) throws IOException {
        
        archivo = new File("Datos y Capturas\\datos.txt");
        if (!archivo.exists()) {
            String contenido = datosDistancia.getText();
            String respuesta = gestion.GuardarATexto(archivo, contenido);
            System.out.println(respuesta);
        }else{
            archivo.delete();
            String contenido = datosDistancia.getText();
            String respuesta = gestion.GuardarATexto(archivo, contenido);
            System.out.println(respuesta);
        }

    }
    
    public void tomarFoto() throws IOException{
        File directorio = new File("Datos y Capturas");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
        if(cont!=10){
            BufferedImage image = webcam.getImage();
            ImageIO.write(image, "PNG", new File("Datos y Capturas\\captura_intruso_"+cont+".png"));
            cont++;
        }else{
            cont = 0;
        }
        

    }
}
