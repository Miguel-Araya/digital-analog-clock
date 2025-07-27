/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import Presentation.pnlReloj;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class SiguienteTiempo extends Thread{
    
   //Manecilla 0 -> hora, 1->minuto, 2->segundo
   private Reloj reloj;
   private int manecilla;
   private boolean detener;
   private int tiempoPausar;
   private pnlReloj panelARepintar;
   
   public SiguienteTiempo(Reloj reloj, int manecilla, int tiempoPausar, pnlReloj panelARepintar){
       
       this.reloj = reloj;
       
       this.panelARepintar = panelARepintar;
       
       this.manecilla = manecilla;
       
       this.detener = false;
       
       this.tiempoPausar = tiempoPausar;
   }

   @Override
   public void run(){
       
       while (!detener) {
           
           switch(this.manecilla){
               
               //hora
               case 0:{
               
                   this.reloj.setHora(this.reloj.getHora()+1);
                   break;
               }
               
               //minuto
               case 1:{

                   this.reloj.setMinuto(this.reloj.getMinuto() + 1);
                   break;
               }
               
               //segundo
               case 2:{
                   
                   this.reloj.setSegundo(this.reloj.getSegundo() + 1);
                   break;
                   
               }
           
           }
           
            this.reloj.actualizarTiempo();
           
           if(this.panelARepintar != null){
               
               this.panelARepintar.repaint();
               
           }
           
           try {
               Thread.sleep(this.tiempoPausar);
           } catch (InterruptedException ex) {
               Logger.getLogger(SiguienteTiempo.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
      
   }

    public void setDetener(boolean detener) {
        this.detener = detener;
    }
   
     
}
