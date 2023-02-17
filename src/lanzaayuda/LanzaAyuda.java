/**
 * Ejemplo de uso de JavaHelp. 6 Oct 2007
 *
 * Una aplicación tonta con dos ventanas y un menú. En ella se puede ver la
 * ayuda de JavaHelp pulsando el item de menu adecuado o la tecla F1.
 * Sirve como basico ejemplo de uso de JavaHelp.
 *
 *  @author Chuidiang
 */
package LanzaAyuda;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

/**
 * Ejemplo sencillo de uso de JavaHelp. Crea dos ventanas con un menú y les pone
 * la ayuda.
 *
 * @author Chuidiang
 *
 */
public class LanzaAyuda {
    
    private JFrame principal;
    private JMenuItem itemAyuda, itemAbout;
    private JMenu menu;
    
    public static void main(String[] args) {
        new LanzaAyuda();
    }
    
    public LanzaAyuda() {
        creaVentanaPrincipal();
        ponLaAyuda();
        visualizaVentanaPrincipal();
    }
    
    private void visualizaVentanaPrincipal() {
       // principal.pack();
        principal.setVisible(true);
        principal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Hace que el item del menu y la pulsacion de F1 visualicen la ayuda.
     */
    private void ponLaAyuda() {
        try {
            // Carga el fichero de ayuda
            File fichero = new File("help/help_set.hs");
            URL hsURL = fichero.toURI().toURL();

            // Crea el HelpSet y el HelpBroker
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();

            // Pone ayuda a item de menu al pulsarlo y a F1 en ventana
            // principal y secundaria.
            hb.enableHelpOnButton(itemAyuda, "Winball", helpset);
            hb.enableHelpKey(itemAyuda, "Winball",helpset);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void creaVentanaPrincipal() {
        principal = new JFrame("Java Swing");
        
        principal.setSize(450, 260);
        JMenuBar menuBar = new JMenuBar();
        
        menu = new JMenu("Ayuda");
        itemAyuda = new JMenuItem("Contenido de Ayuda");
        itemAbout = new JMenuItem("About");
        menu.add(itemAyuda);
        itemAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        menu.add(itemAbout);
        
        menuBar.add(menu);

        principal.setJMenuBar(menuBar);
        //Button button = new Button("Salir");

        // principal.getContentPane().add(button);
    }
    
}
