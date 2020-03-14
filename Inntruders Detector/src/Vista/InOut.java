
package Vista;

import javax.swing.JOptionPane;


public class InOut {
    public String cadena(String m){
        return JOptionPane.showInputDialog(m);
    }
    
    public int cadenaint(String m){       
        return Integer.parseInt(JOptionPane.showInputDialog(m));
    }
    
    public void mostrar(String m){
           JOptionPane.showMessageDialog(null,m);
        
    }
}
