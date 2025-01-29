import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;

class Main extends JFrame implements ActionListener {
   private Buttons[][] button;
   private Icon[][] img;
   private int color;
   private int n;

   Main(int n) {
      this.n = n;
      int fWidth = n * 50 + 10;
      int fHeight = fWidth + 25;
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      double width = screenSize.getWidth();
      double heigth = screenSize.getHeight();
      int yPosition = (int) (heigth - (double) fHeight) / 2;
      int xPosition = (int) (width - (double) fWidth) / 2;
      this.setTitle("Chain Reaction");
      this.setBounds(xPosition, yPosition, fWidth, fHeight);
      this.setVisible(true);
      this.setResizable(false);
      this.setBackground(Color.black);
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent ae) {
            System.exit(0);
         }
      });
      Image icon = Toolkit.getDefaultToolkit().getImage("Images//Icon.png");
      MediaTracker track = new MediaTracker(this);
      track.addImage(icon, 0);

      try {
         track.waitForID(0);
      } catch (Exception var14) {
      }

      this.setIconImage(icon);
      this.setLayout((LayoutManager) null);
      this.color = 0;
      this.loadImages();
      this.initializeButton();
   }

   private void loadImages() {
      this.img = new Icon[2][3];
      String[][] imgName = new String[][] {
            { "Images//Red//redOne.png", "Images//Red//redTwo.png", "Images//Red//redThree.png" },
            { "Images//Blue//blueOne.png", "Images//Blue//blueTwo.png", "Images//Blue//blueThree.png" } };

      for (int j = 0; j < 2; ++j) {
         for (int i = 0; i < 3; ++i) {
            this.img[j][i] = new ImageIcon(imgName[j][i]);
         }
      }

   }

   private void initializeButton() {
      this.button = new Buttons[this.n][this.n];
      int x = 0;
      int y = 0;

      for (int i = 0; i < this.n; ++i) {
         for (int j = 0; j < this.n; ++j) {
            this.button[i][j] = new Buttons();
            this.button[i][j].setBounds(x, y, 50, 50);
            this.button[i][j].setState(2);
            this.button[i][j].setBackground(Color.black);
            this.button[i][j].setBorder(BorderFactory.createLineBorder(Color.red));
            this.button[i][j].addActionListener(this);
            this.add(this.button[i][j]);
            x += 50;
         }

         x = 0;
         y += 50;
      }

   }

   public void actionPerformed(ActionEvent ae) {
      int i = 0;
      int j = 0;

      for (int x = 0; x < this.n; ++x) {
         for (int y = 0; y < this.n; ++y) {
            if (ae.getSource() == this.button[x][y]) {
               i = x;
               j = y;
            }
         }
      }

      if (this.color % 2 == this.button[i][j].state || this.button[i][j].state == 2) {
         this.updateButton(i, j);
         Color[] border = new Color[] { Color.red, Color.blue };
         ++this.color;
         this.setBorderColor(border[this.color % 2]);
         this.checkResult();
      }

   }

   private void updateButton(int i, int j) {
      this.button[i][j].setState(this.color % 2);
      int[][] limit = new int[this.n][this.n];

      int x;
      int y;
      for (x = 0; x < this.n; ++x) {
         for (y = 0; y < this.n; ++y) {
            if ((x == 0 || x == this.n - 1) && (y == 0 || y == this.n - 1)) {
               limit[x][y] = 1;
            } else if ((x <= 0 || x >= this.n - 1 || y != 0 && y != this.n - 1)
                  && (y <= 0 || y >= this.n - 1 || x != 0 && x != this.n - 1)) {
               limit[x][y] = 3;
            } else {
               limit[x][y] = 2;
            }
         }
      }

      x = this.button[i][j].label + 1;
      if (x > limit[i][j]) {
         y = i - 1;
         int b = j + 1;
         int c = i + 1;
         int d = j - 1;
         this.button[i][j].setLabel(0);
         this.button[i][j].setState(2);
         this.button[i][j].setIcon((Icon) null);
         if (y > -1) {
            this.updateButton(y, j);
         }

         if (b < this.n) {
            this.updateButton(i, b);
         }

         if (c < this.n) {
            this.updateButton(c, j);
         }

         if (d > -1) {
            this.updateButton(i, d);
         }
      } else {
         this.button[i][j].setLabel(x);
         this.button[i][j].setIcon(this.img[this.color % 2][x - 1]);
      }

   }

   private void setBorderColor(Color c) {
      for (int i = 0; i < this.n; ++i) {
         for (int j = 0; j < this.n; ++j) {
            this.button[i][j].setBorder(BorderFactory.createLineBorder(c));
         }
      }

   }

   private void checkResult() {
      int red = 0;
      int blue = 0;
      Buttons[][] var7;
      int var6 = (var7 = this.button).length;

      int j;
      for (j = 0; j < var6; ++j) {
         Buttons[] B = var7[j];
         Buttons[] var11 = B;
         int var10 = B.length;

         for (int var9 = 0; var9 < var10; ++var9) {
            Buttons b = var11[var9];
            if (b.state == 0) {
               ++red;
            }

            if (b.state == 1) {
               ++blue;
            }
         }
      }

      if (red == 0 && blue > 1 || blue == 0 && red > 1) {
         String winner = blue == 0 ? "RED" : "BLUE";
         this.setBorderColor(Color.white);

         for (int i = 0; i < this.n; ++i) {
            for (j = 0; j < this.n; ++j) {
               this.button[i][j].removeActionListener(this);
            }
         }

         JOptionPane.showMessageDialog(this, winner + " won!", "Game Result", JOptionPane.INFORMATION_MESSAGE);
      }

   }

   public static void main(String[] args) {
      new Main(8);
      new JFrame();
   }
}

class Buttons extends JButton {
   protected int label;
   protected int state;

   protected void setLabel(int l) {
      this.label = l;
   }

   protected void setState(int s) {
      this.state = s;
   }
}
