import javax.swing.*;

public class App{
    public static void main(String[] args)throws Exception {
        int boardwidth = 360;
        int boardheight = 640;
        
        JFrame frame = new JFrame("Flappy bird");
        // frame.setVisible(true);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Flappybird flappybird = new Flappybird();
        frame.add(flappybird);
        frame.pack();
        flappybird.requestFocus();
        frame.setVisible(true); 
    }
    
}
