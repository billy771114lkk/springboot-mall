public class Singleton {
    private static Singleton instance;

    private Singleton(){
        // 這裡面跑很了多code，建立物件需要花費很多資源
    }

    public static Singleton getInstance(){
        // 第一次被呼叫的時候再建立物件
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}



