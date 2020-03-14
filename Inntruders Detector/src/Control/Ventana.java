/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.GestionA;
import Modelo.Procesos;
import Vista.InOut;
import com.github.sarxos.webcam.Webcam;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import static java.time.Clock.system;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class Ventana extends javax.swing.JFrame {

public List<String> ListaDePuertos;
public String puerto;
InOut o1 =new InOut();
Procesos o2= new Procesos();
JScrollBar vertical;
public String distancia;
int b = 5;

public  PanamaHitek_Arduino o3 =  new PanamaHitek_Arduino();
private final SerialPortEventListener listener  =  new SerialPortEventListener(){

    @Override
    public void serialEvent(SerialPortEvent spe) {
        try{
            if(o3.isMessageAvailable()){
                
                
                distancia = o3.printMessage();
                System.out.println(distancia);
                //datosDistancia.setText(datosDistancia.getText() + distancia +" cm\n");
                
                try {
                    o2.comprobarDistancia(distancia, datosDistancia, vertical);
                } catch (IOException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }catch(SerialPortException | ArduinoException ex){
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
};
       


    public Ventana() {
        initComponents();
        Iniciar();
        
    }
    
    public void Iniciar(){
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Detector de intrusos");
        vertical = scroll.getVerticalScrollBar();

        
        ListaDePuertos = o3.getSerialPorts();
        
        
        for (int i = 0; i < ListaDePuertos.size(); i++) {
            
            System.out.println(ListaDePuertos.get(i));
            cajaSeleccionPuerto.addItem(ListaDePuertos.get(i));  
            puerto = "Vacio";
            
            if(ListaDePuertos.size() == 1){
                puerto = ListaDePuertos.get(0);
                cajaSeleccionPuerto.setSelectedIndex(1);
            }else{
                puerto = cajaSeleccionPuerto.getSelectedItem().toString();
            }
        }
        if(puerto == null){
            o1.mostrar("no se ha encontrado ningun puerto con arduino,"
            +"\nasegurese de que arduino es te conectado");
        }
        ConeccionArduinoRX(o3);
        
        o2.conectarCamara();
    }
    
    public void ConeccionArduinoRX(PanamaHitek_Arduino obj){
        
        try {
            obj.arduinoRX(puerto, 9600, listener);
        } catch (SerialPortException | ArduinoException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //funcion para comprobar si no hay puertos disponibles
    public boolean RevisarPuertos(){
        if(cajaSeleccionPuerto.getSelectedItem().toString().compareTo("Puertos") == 0){
            o1.mostrar("no sea seleccionado ningun puerto");
            return false;
        }
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        datosDistancia = new javax.swing.JTextArea();
        cambiar = new javax.swing.JButton();
        cajaSeleccionPuerto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        datosDistancia.setColumns(20);
        datosDistancia.setRows(5);
        datosDistancia.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        scroll.setViewportView(datosDistancia);

        cambiar.setBackground(new java.awt.Color(255, 255, 255));
        cambiar.setText("Cambiar");
        cambiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cambiar.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        cambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarActionPerformed(evt);
            }
        });

        cajaSeleccionPuerto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puertos" }));
        cajaSeleccionPuerto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cajaSeleccionPuerto.setDebugGraphicsOptions(javax.swing.DebugGraphics.LOG_OPTION);
        cajaSeleccionPuerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaSeleccionPuertoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cajaSeleccionPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(cambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajaSeleccionPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //boton para cambiar de puertos
    private void cambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarActionPerformed
        try {
            o3.killArduinoConnection();
        } catch (ArduinoException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        PanamaHitek_Arduino o4 = new PanamaHitek_Arduino();
        this.puerto = cajaSeleccionPuerto.getSelectedItem().toString();
        System.out.println(puerto);
        this.o3 = o4;
        ConeccionArduinoRX(o3);

    }//GEN-LAST:event_cambiarActionPerformed

    private void cajaSeleccionPuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaSeleccionPuertoActionPerformed
        this.puerto = cajaSeleccionPuerto.getSelectedItem().toString();
    }//GEN-LAST:event_cajaSeleccionPuertoActionPerformed
     
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cajaSeleccionPuerto;
    private javax.swing.JButton cambiar;
    private javax.swing.JTextArea datosDistancia;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
