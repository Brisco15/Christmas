import javax.imageio.ImageIO;
import javax.sound.midi.*;
import javax.sound.midi.spi.SoundbankReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ChristmasPanel extends JPanel implements ActionListener {

    private Image background;
    private Image weihnachtsmann;
    private Image schatten;
    private Timer timer = new Timer(60, this);
    private int x= 50, y=35;
    private int weihWidth = 100;


    public ChristmasPanel(){


        try {
            File imageFile = new File("winter.png");
            background = ImageIO.read(imageFile);

            weihnachtsmann = ImageIO.read(new File("weih.png"));
            schatten = ImageIO.read(new File("weihschatten.png"));



            timer.start();
            playMidi();

        } catch (IOException e) {
            System.out.println("Bild nicht gefunden");
            throw  new RuntimeException(e);
        }

    }


    private void playMidi(){
     try {
         Synthesizer synthesizer = MidiSystem.getSynthesizer();
         synthesizer.loadAllInstruments(synthesizer.getDefaultSoundbank());
         Sequencer sequencer = MidiSystem.getSequencer();


         //song
         Sequence sequence = MidiSystem.getSequence(new File("jingle.mid"));

         sequencer.open();
         sequencer.setSequence(sequence);
         sequencer.setTempoFactor(1.f);

         sequencer.start();

     } catch (MidiUnavailableException e){
         throw new RuntimeException(e);
     } catch (InvalidMidiDataException e) {
         throw new RuntimeException(e);
     } catch (IOException e) {
         throw new RuntimeException(e);
     }

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //statisch
        g.drawImage(background, -20,0,840,400, this);
        //dynamisch
        g.drawImage(weihnachtsmann, x,y, weihWidth, 100, this);
        g.drawImage(schatten, x, y+240, weihWidth, 100, this);

    }


    //Timer mit dem listener sind unsere "Spieleschleife"
    @Override
    public void actionPerformed(ActionEvent e) {

        x += 3;

        if(x > getWidth()){
            x = 0 - weihWidth;
        }

        repaint();

    }



}
