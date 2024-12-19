import javax.swing.*;

public class ChristmasWindow extends JFrame {

    public ChristmasWindow(){

        super("Christmas");
        setSize(800, 400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new ChristmasPanel());

        setVisible(true);
    }
}
