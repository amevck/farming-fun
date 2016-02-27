import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 import java.net.* ;

public class KeyListenerTest extends JPanel implements KeyListener, Runnable {
    boolean isUpPressed, isDownPressed, isLeftPressed,isRightPressed;
    static JFrame f;
    static char u = 'u';
    static char d = 'd';
    static char l = 'l';
    static char r = 'r';
    static int packetsize = 10 ;
    static byte [] data = new byte [packetsize];
 
    public static void main(String[] args) {

       DatagramSocket socket;
        f = new JFrame();
        f.setSize(600,300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(new KeyListenerTest());
        f.setVisible(true);

        try
      {
         final int port         = Integer.parseInt( args[0] ) ;
          

       final String loghost = "192.168.1.2";
        final InetAddress host = InetAddress.getByName(loghost);
        socket = new DatagramSocket();

        while(true){
            System.out.println(data[0]+"- data\n");
         DatagramPacket packet =  new DatagramPacket(data,data.length,host,port);
         Thread.sleep(5);
            socket.send( packet ) ;
        }
           
      }
      catch( Exception e1)
      {
         System.out.println( e1 ) ;
      }
        
         
      
    }
 
    public KeyListenerTest() {
        setFocusable(true);
        addKeyListener(this);
        new Thread(this).start();
    }
 
    public void keyTyped(KeyEvent ke) {
    }
 
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_UP: isUpPressed = true; break;
            case KeyEvent.VK_DOWN: isDownPressed = true; break;
            case KeyEvent.VK_LEFT: isLeftPressed = true; break;
             case KeyEvent.VK_RIGHT: isRightPressed = true; break;
        }
    }
 
    public void keyReleased(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_UP: isUpPressed = false; break;
            case KeyEvent.VK_DOWN: isDownPressed = false; break;
            case KeyEvent.VK_LEFT: isLeftPressed = false; break;
            case KeyEvent.VK_RIGHT: isRightPressed = false; break;
        }
}
 
    public void run() {
        while(true) {
            try {
                if(isUpPressed==true) {data[0]= (byte)u;System.out.println("u\n");}else data[0]='\0';
                if(isDownPressed==true) {data[1]= (byte)d;System.out.println("d\n");} else data[1]= 0;
                if(isLeftPressed==true) {data[2]= (byte)l;System.out.println("l\n");} else data[2]= 0;
                if(isRightPressed==true){data[3]= (byte)r;System.out.println("r\n");} else data[3]= 0;
                Thread.sleep(5);
       
          // Convert the arguments to ensure that they are valid
        //final  InetAddress host = InetAddress.getByName( args[0] ) ;

            } catch(Exception exc) {
                exc.printStackTrace();
                break;
            }
        }
    }
}