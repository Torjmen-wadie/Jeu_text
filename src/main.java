public class main {
    public static void main (String [] args){
        Thread thread = new Thread(new Game());
        thread.start();
        Game g =new Game();
        g.startGame();
        try {
            thread.sleep(5000);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g.stopGame();
        thread.currentThread().interrupt();


       // System.out.println(t);

    }
}
