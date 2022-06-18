import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private int[] snake_x=new int[750];
    private int[] snake_y=new int[750];

    private boolean right= false;
    private boolean left=false;
    private boolean down=false;
    private boolean up=false;

    private ImageIcon snake_r;
    private ImageIcon snake_l;
    private ImageIcon snake_d;
    private ImageIcon snake_u;
    private ImageIcon body;




    private Timer timer;
    private int delay=100;

    private  int snakeLength=3;
    private int moves=0;
    private int score=0;

    private int[] food_x= {25 ,50 ,75 ,100 ,125 ,150 ,175 ,200
            ,225 ,250 ,275 ,300 ,325 ,350 ,375 ,400 ,425 ,450
            ,475 ,500 ,525 ,550 ,575 ,600 ,625 ,650 ,675 ,700 ,725 ,750 ,775 ,800 ,825,850};

    private int[] food_y={75 ,100 ,125 ,150 ,175 ,200
            ,225 ,250 ,275 ,300 ,325 ,350 ,375 ,400 ,425 ,450
            ,475 ,500 ,525 ,550 ,575,600 ,625};

    private ImageIcon food;

    private Random random= new Random();
    private int x_food=random.nextInt(34);
    private int y_food=random.nextInt(23);


    private ImageIcon title;

    public GamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
//        GameOver();
        timer=new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g){

        if(moves==0){
            snake_x[0]=100;
            snake_x[1]=75;
            snake_x[2]=50;


            snake_y[0]=100;
            snake_y[1]=100;
            snake_y[2]=100;
        }
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);

        title= new ImageIcon("title.png");
        title.paintIcon(this,g,25,11);

        //border of gameplay
        g.setColor(Color.white);
        g.drawRect(24,74,851,577);
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);

        //draw the scores;
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,18));
        g.drawString("Score: "+ score,780,40);

        snake_r=new ImageIcon("snake_r.png");
        snake_r.paintIcon(this,g,snake_x[0],snake_y[0]);

        for(int i=0;i<snakeLength;i++){
            if (i==0&& right){
                snake_r=new ImageIcon("snake_r.png");
                snake_r.paintIcon(this,g,snake_x[i],snake_y[i]);
            }
            if (i==0&& left){
                snake_l=new ImageIcon("snake_l.png");
                snake_l.paintIcon(this,g,snake_x[i],snake_y[i]);
            }
            if (i==0&& up){
                snake_u=new ImageIcon("snake.png");
                snake_u.paintIcon(this,g,snake_x[i],snake_y[i]);
            }
            if (i==0&& down){
                snake_d=new ImageIcon("snake_d.png");
                snake_d.paintIcon(this,g,snake_x[i],snake_y[i]);
            }

            if(i!=0){
                body=new ImageIcon("body.png");
                body.paintIcon(this,g,snake_x[i],snake_y[i]);
            }
        }
// collision detection
        food= new ImageIcon("food.png");
        food.paintIcon(this,g,food_x[x_food],food_y[y_food]);

        if(food_x[x_food]==snake_x[0] && food_y[y_food]==snake_y[0]){
            snakeLength++;
            score++;
            x_food=random.nextInt(34);
            y_food=random.nextInt(23);
        }

        //check if game over(snake bites itself)


        if(GameOver()==true){
                right=false;
                left=false;
                up=false;
                down=false;

                g.setColor(Color.RED);
                g.setFont(new Font("arial",Font.BOLD,50));
                g.drawString("GAME OVER!",300,300);

                g.setColor(Color.WHITE);
                g.setFont(new Font("arial",Font.BOLD,20));
                g.drawString("Space to Restart",350,340);
        }


        g.dispose();



    }

    public boolean GameOver(){
        for(int i=1; i<snakeLength;i++){
            if(snake_x[i]==snake_x[0]&& snake_y[i]==snake_y[0]){
                return true;
            }
        }
        return false;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(right){
            for(int i=snakeLength-1; i>=0;i-- ){
                snake_y[i+1]=snake_y[i];
            }
            for(int i=snakeLength;i>=0;i--){
                if(i==0){
                    snake_x[i]=snake_x[i]+25;// moving by 25
                }
                else {
                    snake_x[i]=snake_x[i-1];
                }
                if(snake_x[i]>850){
                    snake_x[i]=25;
                }
            }
            repaint();
        }
        if(left){
            for(int i=snakeLength-1; i>=0;i-- ){
                snake_y[i+1]=snake_y[i];
            }
            for(int i=snakeLength;i>=0;i--){
                if(i==0){
                    snake_x[i]=snake_x[i]-25;// moving by 25
                }
                else {
                    snake_x[i]=snake_x[i-1];
                }
                if(snake_x[i]<25){
                    snake_x[i]=850;
                }
            }
            repaint();
        }
        if(up){
            for(int i=snakeLength-1; i>=0;i-- ){
                snake_x[i+1]=snake_x[i];
            }
            for(int i=snakeLength;i>=0;i--){
                if(i==0){
                    snake_y[i]=snake_y[i]-25;// moving by 25
                }
                else {
                    snake_y[i]=snake_y[i-1];
                }
                if(snake_y[i]<75){
                    snake_y[i]=625;
                }
            }
            repaint();
        }
        if(down){
            for(int i=snakeLength-1; i>=0;i-- ){
                snake_x[i+1]=snake_x[i];
            }
            for(int i=snakeLength;i>=0;i--){
                if(i==0){
                    snake_y[i]=snake_y[i]+25;// moving by 25
                }
                else {
                    snake_y[i]=snake_y[i-1];
                }
                if(snake_y[i]>625){
                    snake_y[i]=75;
                }
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
             moves++;
             if(!left){
                 right=true;
             }
             else{
             right=false;
             left=true;}

             up=false;
             down=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            moves++;
            if(!right){
                left=true;
            }
            else{
            right=true;
            left=false;
            }
            up=false;
            down=false;}

        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            moves++;
            if(!up){
                down=true;
            }
            else{
                down=false;
                up=true;
            }
            right=false;
            left=false;
        }

        if(e.getKeyCode()==KeyEvent.VK_UP){
            moves++;
            if(!down){
                up=true;
            }
            else{
                down=true;
                up=false;
            }
            left=false;
            right=false;
        }

        if(GameOver()==true){
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                score=0;
                moves=0;
                snakeLength=3;
                repaint();
            }
        }

    }


    @Override
    public void keyReleased(KeyEvent e) {

    }
}
