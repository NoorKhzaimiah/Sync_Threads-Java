 
package ass2networkprog;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

 class CalcThread extends Thread {
File f ;
   private boolean finished;
   int sum ;
    public CalcThread( File f) {
         this.f = f ;
    }  
     boolean isFinished(){
        return finished;
    }
  
@Override
    public void run (){
    
        FileInputStream fis = null;
        try {
            fis = new FileInputStream( f);
            int content;
             this.sum = 0;
            while ((content = fis.read()) != -1) {
                 if(content%2 !=0)
                          this.sum+=1;     
                            
            }
              finished=true;
            System.out.println("The number of prime numbers is : "+ this.sum );

        } catch (IOException e) {
        } finally {
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException ex) {
            }
        }
    }
     
  
} //  end Calc class


public class Ass2NetworkProg extends Thread {
  String file1;
  File f1,f2,f3 ;
     private boolean finished;
    Ass2NetworkProg(String file1   ){
        this.file1=file1;
           
     this. f1   = new File("d:\\"+file1+".txt");
       

        
    }
    
 @Override
    public void run(){
        //create the file 
       
     try {
             try (FileWriter writer1 = new FileWriter(f1)) {
                 for (int i = 0 ; i<1000 ; i++)
                     writer1.write(((int)(Math.random()*51)+1));
             }
                                
               finished=true;
     } catch (IOException ex) {
         Logger.getLogger(Ass2NetworkProg.class.getName()).log(Level.SEVERE, null, ex);
     }

    }
    
     boolean isFinished(){
        return finished;
    }
     
    
  @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws InterruptedException {
   String file1 = "f1 ",file2="f2",file3="f3" ;
         Ass2NetworkProg th1 , th2 , th3 ;
         th1= new Ass2NetworkProg( file1  );
         th2= new Ass2NetworkProg( file2  );
         th3= new Ass2NetworkProg( file3  );
        th1.start();
        th2.start();
        th3.start();
        
       //  while (!th1.isFinished() || !th2.isFinished() || !th3.isFinished());        
        
       CalcThread th4 = new CalcThread(th1.f1) ;
       CalcThread th5 = new CalcThread(th2.f2) ;
        CalcThread th6 = new CalcThread(th3.f3) ;
      th4.start();            
       th5.start();        
       th6.start();
      //  while (!th4.isFinished() || !th5.isFinished() || !th6.isFinished());
       //  System.out.println( "1: " + th4.sum +"\n"+ "2:"+ th5.sum+"\n"+"3:"+ th6.sum);
        
         
      /* try {
        
         th3.join();
         th4.join();
      } catch ( Exception e) {
         System.out.println("Interrupted");
      }*/
        
       
     }
    
}


