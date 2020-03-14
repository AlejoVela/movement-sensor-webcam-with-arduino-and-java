/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.io.*;
/**
 *
 * @author Sandra Vela
 */
public class GestionA {
    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;
    
    public GestionA(){
        
    }
    
    public String AbrirATexto(File archivo){
        String contenido = "";
        
        try{
            entrada = new FileInputStream(archivo);
            int ascci;
            while((ascci = entrada.read()) != -1){
                char caracter = (char)ascci;
                contenido += caracter;
            }
        } catch(Exception e){
            
        }
        
        return contenido;
    }
    
    public String GuardarATexto(File archivo, String contenido){
        String respuesta = null;
        
        try{
            salida = new FileOutputStream(archivo);
            byte[] byteTxt = contenido.getBytes();
            salida.write(byteTxt);
            respuesta = "Se ha guardado con exito el archivo";
        } catch (Exception e){
            
        }
        
        return respuesta;
    }
    
    
}
