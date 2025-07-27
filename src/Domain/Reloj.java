/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Miguel
 */
public class Reloj extends Thread{
    
   private int hora;
   private int minuto;
   private int segundo;
   private boolean pausa;
   private BufferedImage imagenReloj;
   
    public Reloj() {

        //obtener la hora de la computadora 
        resetClock();

        this.pausa = false;
        this.cargarImagenReloj();
    }
    
   public Reloj(int hora, int minuto, int segundo){
       
       this.hora = hora;
       this.minuto = minuto;
       this.segundo = segundo;
       this.pausa = false;

       this.cargarImagenReloj();
   }
   
   
   @Override
   public void run(){
       
       while(!this.pausa){

           this.avanzarTiempo();
           this.actualizarTiempo();
           try {
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
               break;
           }
           
       }
       
   }
   
    public void cargarImagenReloj() {

        try {
            this.imagenReloj = ImageIO.read(getClass().getResourceAsStream("/img/relojManecilla.png"));
        } catch (IOException ex) {
            Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  
   public int getHora(){
       
       return this.hora;
       
   }
   
   public int getMinuto(){
       
       return this.minuto;
       
   }
   
   public boolean getPausa(){
       
       return this.pausa;
       
   }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }
   
   public void setHora(int hora){
       
       this.hora = hora;
       
   }
   
    public void resetClock() {

        LocalTime currentTime = LocalTime.now();

        this.hora = currentTime.getHour();
        this.minuto = currentTime.getMinute();
        this.segundo = currentTime.getSecond();

    }
   
   public void setMinuto(int minuto){
       
       this.minuto = minuto;
       
   }
   
   public void setPausa(boolean pausa){
       
       this.pausa = pausa;
       
   }
   
   public void avanzarTiempo(){
        
       ++this.segundo;
       
   }
   
   public void actualizarTiempo(){

              
       if (this.segundo >= 60) {

           this.segundo = this.segundo - 60;

           ++this.minuto;
       }
     
       if (this.minuto >= 60) {

           this.minuto = this.minuto - 60;
           ++this.hora;

       }

       if (this.hora > 23) {
           this.hora = this.hora - 24;

       }
       
   }
   
   @Override
   public String toString(){
       
       String minuto = this.minuto+"";
       String segundo = this.segundo+"";
       
       if(this.minuto < 10)
           minuto = "0"+minuto;
       
        if(this.segundo < 10)
           segundo = "0"+segundo;
           
       return this.hora+":"+minuto+" "+segundo;
       
   }

    public void dibujar(Graphics2D g2d) {

        g2d.setColor(Color.black);

        int xCentro = 308;
        int yCentro = 258;

        double anguloMinuto = Math.toRadians(6 * this.minuto);
        double anguloSegundo = Math.toRadians(6 * this.segundo);
        double anguloHora = Math.toRadians(6 * (hora * 5));

        g2d.setStroke(new BasicStroke(3));

        g2d.drawImage(this.imagenReloj, 60, 5, this.imagenReloj.getWidth(), this.imagenReloj.getHeight(), null);

        int longitudManecillaS = 199;

        int xSegundo = (int) (xCentro + longitudManecillaS * Math.sin(anguloSegundo));
        int ySegundo = (int) (yCentro - longitudManecillaS * Math.cos(anguloSegundo));
        g2d.drawLine(xCentro, yCentro, xSegundo, ySegundo);

        int longitudManecillaM = 193;
        
        g2d.setStroke(new BasicStroke(6));
        
        int xMinuto = (int) (xCentro + longitudManecillaM * Math.sin(anguloMinuto));
        int yMinuto = (int) (yCentro - longitudManecillaM * Math.cos(anguloMinuto));
        g2d.drawLine(xCentro, yCentro, xMinuto, yMinuto);

        int longitudManecillaH = 160;
        
        g2d.setStroke(new BasicStroke(10));
        
        int xHora = (int) (xCentro + longitudManecillaH * Math.sin(anguloHora));
        int yHora = (int) (yCentro - longitudManecillaH * Math.cos(anguloHora));
        g2d.drawLine(xCentro, yCentro, xHora, yHora);
        
    }
   
}
