import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

import javax.swing.BorderFactory;
class Main extends Frame implements ActionListener
{
    private Buttons button[][];
    private Icon img[][];
    private int color;
    private int n;

    Main(int n)
    {
        this.n=n;
        
        int fWidth=(n+1)*25+n*75;
        int fHeight=fWidth+25;
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        double width=screenSize.getWidth();
        double heigth=screenSize.getHeight();
        int yPosition=(int)(heigth-fHeight)/2;
        int xPosition=(int)(width-fWidth)/2;

        setTitle("Chain Reaction-JavX");
        setBounds(xPosition,yPosition,fWidth,fHeight);
        setVisible(true);
        setResizable(false);
        setBackground(new Color(2,2,2));

        addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent ae)
                {
                    System.exit(0);
                }
            });

        Image img=Toolkit.getDefaultToolkit().getImage("Icon.png");
        MediaTracker track=new MediaTracker(this);
        track.addImage(img, 0);
        try 
        {
            track.waitForID(0);
        }
        catch(Exception e)
        {
        }
        setIconImage(img);
        setLayout(null);
        
        color=0;
        loadImages();
        initializeButton();
    }
    
    private void loadImages()
    {
        img=new Icon[2][4];
        String imgName[][]={
                {"Red//redOne.png","Red//redTwo.png","Red//redThree.png","Red//redFour.png"},
                {"Blue//blueOne.png","Blue//blueTwo.png","Blue//blueThree.png","Blue//blueFour.png"}};
        for(int j=0;j<2;j++)
        {
            for(int i=0;i<4;i++)
            {
                img[j][i]=new ImageIcon(imgName[j][i]);
            }
        }
    }

    private void initializeButton()
    {                
        button=new Buttons[n][n];
        
        int x=25,y=50;
        for(int i=0;i<n;i++)
        {            
            for(int j=0;j<n;j++) 
            {
                button[i][j]=new Buttons();
                button[i][j].setBounds(x, y, 75, 75);
                button[i][j].setBackground(Color.black); 
                button[i][j].addActionListener(this);
                add(button[i][j]);
                x+=100;
            }
            x=25;
            y+=100;
        }
    }

    public void actionPerformed(ActionEvent ae)
    {        
        int i=0,j=0;

        for(int x=0;x<n;x++)
        {
            for(int y=0;y<n;y++)
            {
                if(ae.getSource()==button[x][y])
                {
                    i=x;
                    j=y;
                }
            }
        }

        updateButton(i,j);
        color++;
    }

    private void updateButton(int i,int j)
    {                
        int limit[][]=new int[n][n];
        for(int x=0;x<n;x++)
        {
            for(int y=0;y<n;y++)
            {
                if((x==0||x==n-1)&&(y==0||y==n-1))
                    limit[x][y]=2;
                else if(((0<x&&x<n-1)&&(y==0||y==n-1))||(0<y&&y<n-1)&&(x==0||x==n-1))
                    limit[x][y]=3;
                else
                    limit[x][y]=4;
            }
        }
                
        int l=button[i][j].label+1;

        if(l>limit[i][j])
        {
            int a,b,c,d;
            a=i-1;
            b=j+1;
            c=i+1;
            d=j-1;

            button[i][j].label(0);
            button[i][j].setIcon(null);

            if(a>-1)
                updateButton(a,j);
            if(b<n)
                updateButton(i,b);
            if(c<n)
                updateButton(c,j);
            if(d>-1)
                updateButton(i,d);
        }
        else
        {
            button[i][j].label(l);
            button[i][j].setIcon(img[color%2][l-1]);
        }
    }

    public static void main(String[]args)
    {
        System.out.print("Enter size(5 recommended): ");
        Main m=new Main(new Scanner(System.in).nextInt());
    }
}
class Buttons extends JButton
{
    int label;
    public void label(int l)
    {
        label=l;    
    }
            }