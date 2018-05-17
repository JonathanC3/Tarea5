package Domain;

public class SynchronizedBuffer implements Buffer {

    private int buffer = -1;
    private int occupiedBuffer = 0;
    private int paint = 0;
    private int acum=0;

    @Override
    public synchronized void set(int value) {

        while (this.occupiedBuffer == 1) {
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        } // while
        this.buffer = value;
        this.paint = 1;
        ++this.occupiedBuffer;
        acum++;

        notify();
    } // set

    @Override
    public synchronized int get() {
        String name = Thread.currentThread().getName();
        while (this.occupiedBuffer == 0) {
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        --this.occupiedBuffer;

        notify();
        return this.buffer;
    } // get
    
    public int paint(){
        return this.paint;
    }
    
    public void setPaint(int paint){
        this.paint = paint;
    }
    public int num(){
        return this.acum;
    } 
   
} // fin de la clase

