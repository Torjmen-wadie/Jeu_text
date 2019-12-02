public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.init();


        Thread init = new Thread(){
            @Override
            public void run() {
                game.init();
            }
        };

        init.start();

        Thread stop = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                    Game.runGame = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        stop.start();
        try {
            stop.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(game.runGame);
    }
}
