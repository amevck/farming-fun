import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 import java.net.* ;

public class SimpleKeyTest {

    public static  void main(String[] args) {
        
 
     
         
         
         final int port         = Integer.parseInt( args[1] ) ;
          int packetsize = 20 ;
          final byte [] data = new byte [packetsize];


            //@Override
            
                JFrame frame = new JFrame();
                JPanel panel = new JPanel();

                frame.getContentPane().add(panel);

                panel.addKeyListener(new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyReleased(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                    	
                        char t = e.getKeyChar();
                        byte b = (byte) t;

                        DatagramSocket socket;
      
      try
      {
          // Convert the arguments to ensure that they are valid
        //final  InetAddress host = InetAddress.getByName( args[0] ) ;
         //Q1: Construct the socket
       final String loghost = "192.168.1.2";
        final InetAddress host = InetAddress.getByName(loghost);
        socket = new DatagramSocket();

        
         DatagramPacket packet =  new DatagramPacket(data,data.length,host,port);
         
                  //  socket.send( packet ) ;
                        //get  time
         long startTime = System.currentTimeMillis();  



                   
                      
               
             
             long endTime   = System.currentTimeMillis();
      
             float time =( (endTime-startTime)/1000f);
         
            
            // System.out.format("%.3f s  -Sent packets: "+i+" pkts \n",time );
                                

                                     //delay for 1 s.
             //Thread.sleep(100);

             data[0]=(Byte)b;
            socket.send( packet ) ;
            System.out.println("Pressed " + t);
         
         //socket.close() ;
      }
      catch( Exception e1)
      {
         System.out.println( e1 ) ;
      }
        
                        //data[0]=(Byte)b;
                      //socket.send( packet ) ;
                        //System.out.println("Pressed " + t);
                    }
                });

                panel.setFocusable(true);
                panel.requestFocusInWindow();

                frame.setSize(new Dimension(300, 300));
                frame.setVisible(true);
            

        

        //SwingUtilities.invokeLater(r);

    }
}
