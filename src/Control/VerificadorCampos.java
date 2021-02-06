/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author hacke
 */
public class VerificadorCampos {
    
    public void validaNumeros(java.awt.event.KeyEvent evt){
        char key = evt.getKeyChar();
        int k = Integer.valueOf(key);
        
        if((k < 48 && k != 8)  || k > 57){
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }else if(k == 8)
            evt.consume();
    }
    
    public void borrarCampos(ArrayList<JTextField> textFields){
        for (JTextField array :textFields ){
            array.setText("");
        }
    }
    
    public JLabel verificarCualCampoVacio(ArrayList<JTextField> textFields, ArrayList<JLabel> labels){
        for(int i = 0; i < textFields.size() ;i++){
            if(!textFields.get(i).getText().isEmpty())
                return labels.get(i);
            else
                return null;
        }
        return null;
    }
    
    public boolean verificarCampoVacio(ArrayList<JTextField> textFields, int indice){
        boolean ok = false;
        String camposVacios = "";
        
        if(!textFields.get(indice).getText().isEmpty())
            ok = true;
        else{
            ok = false;
            camposVacios = textFields.get(indice).getName()+" ";
        }
        
        
        if(!camposVacios.isEmpty()){
            JOptionPane.showMessageDialog(null, "POR FAVOR LLENE LOS SIGUIENTES CAMPOS \n"+camposVacios,
                               "!ATENCION!", JOptionPane.ERROR_MESSAGE);
        }
        
        return ok;
    }
    
    public boolean verificarSiCamposVacios(ArrayList<JTextField> textFields, ArrayList<JLabel> labels){
        boolean ok = false;
        JLabel joptionpane = new JLabel();
        String camposVacios = "";
        
        for(int i = 0; i < textFields.size() ;i++){
            if(!textFields.get(i).getText().isEmpty())
                ok = true;
            else{
                ok = false;
                camposVacios = camposVacios+labels.get(i).getText()+"**";
            }
        }
        
        if(!camposVacios.isEmpty()){
            joptionpane.setText("<html><center><b>"+"POR FAVOR LLENE LOS SIGUIENTES CAMPOS:</b><br><br>"
                       +"<u>**"+camposVacios+"</u></center></html>");
            JOptionPane.showMessageDialog(null, joptionpane,"!ATENCION!", JOptionPane.ERROR_MESSAGE);
        }
        camposVacios = "";
        return ok;
    }
}
