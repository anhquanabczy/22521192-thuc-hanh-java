import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Flappybird extends JPanel implements ActionListener, KeyListener {
    int boardwidth = 360;
    int boardheight = 640;

    //background
    Image backgroundImg;
    Image birdImage;
    Image toppipeImage;
    Image botpipeImage;

    //Bird 
    int birdX = boardwidth/8;
    int birdY = boardheight/2;
    int birdwidth = 34;
    int birdheight = 24;

    class Bird{
        int x = birdX;
        int y = birdY;
        int width = birdwidth;
        int height = birdheight;
        Image img;

        Bird(Image img){
            this.img = img;
        }
    }

    //pipe 
    int pipeX = boardwidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    class Pipe{
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img){
            this.img = img;
        }
    }

    // Game 
    Bird bird ;
    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameloop;
    Timer placePipesTimer;
    boolean gameover = false;
    double score = 0;

    Flappybird() {
        setPreferredSize(new Dimension(boardwidth, boardheight));//kich thuoc
        setFocusable(true);//focus vao man hinh chinh
        addKeyListener(this);// them cac tac vu 

        //background
        backgroundImg = new ImageIcon(getClass().getResource("flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
        toppipeImage = new ImageIcon(getClass().getResource("toppipe.png")).getImage();
        botpipeImage = new ImageIcon(getClass().getResource("bottompipe.png")).getImage();
        
        bird = new Bird(birdImage);
        pipes = new ArrayList<Pipe>();

        //pipe
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipe();
            }
        });

        placePipesTimer.start();//chay pipe

        // game loop
        gameloop = new Timer(1000/60, this);//FPS
        gameloop.start();
    }

    public void placePipe(){ // sinh pipe
        int randomPipeY = (int)(pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));// sinh so ngau nhien trong khoang 1/4 height den 3/4 height
        Pipe toppipe = new Pipe(toppipeImage);
        Pipe botpipe = new Pipe(botpipeImage);
        toppipe.y = randomPipeY;
        botpipe.y = randomPipeY + pipeHeight + 160;// khoang cach giua 2 pipe
        pipes.add(toppipe);
        pipes.add(botpipe);
    }

    public void paintComponent(Graphics g) {//ve 
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // background
        g.drawImage(backgroundImg, 0, 0, boardwidth, boardheight, null);
        // bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        //pipe
        for( int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
        //score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("" + (int)score, boardwidth/2 - 10, 80);
        if (gameover) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", boardwidth/2 - 120, boardheight/2);
        }
    }

    public void move() {
        //trong truong
        velocityY += gravity;

        //chim
        bird.y += velocityY;
        bird.y = Math.max(0, bird.y);

        //pipe
        for(int i=0; i <pipes.size(); i++){//pipe di chuyen
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
            if(pipe.x + pipe.width < 0){//neu pipe di chuywn qua khung hinh thi xoa pipe
                pipes.remove(i);
                i--;
            }
            if(!pipe.passed && pipe.x + pipe.width < bird.x){//neu bird qua pipe
                pipe.passed = true;
                score += 0.5;
            }
            if(collision(bird, pipe)){//neu cham pipe thi game over
                gameover = true;
            }
        }
        if(bird.y >= boardheight - 95){//neu cham day thi game over
            gameover = true;
        }

    }   

    public boolean collision(Bird a, Pipe b){
        return a.x < b.x+b.width &&
                a.x+a.width > b.x &&
                a.y < b.y+b.height &&
                a.y+a.height > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameover){
            gameloop.stop();
            placePipesTimer.stop();
        }
    }

    

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            velocityY = -10;
            if(gameover){
                bird.y = birdY;
                pipes.clear();
                score = 0;
                gameover = false;
                gameloop.start();
                placePipesTimer.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}
