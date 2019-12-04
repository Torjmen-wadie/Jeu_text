public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Thread initGame = new Thread(() -> game.menu());

        Thread stopGame = new Thread(() -> {
            try {
                initGame.start();
                //wait until game starts...
                while (!game.runGame){
                    Thread.sleep(5000);
                }

                //starts countdown
                Thread.sleep(10000);
                game.stopGame();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        stopGame.start();



    }
}
