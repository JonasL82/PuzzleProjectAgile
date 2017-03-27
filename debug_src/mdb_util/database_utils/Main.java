package mdb_util.database_utils;
import mdb_util.database_utils.DatabaseTest;
public class Main{
    public static void main(String[] args){
        String arg0 = args[0];
        String arg1 = args[1];
        System.out.println("Running db.Main...");
        DatabaseTest dt = new DatabaseTest();
        if(dt.hasConnection()){
            System.out.println("We have a connection");
        }else{
            System.out.println("There was a problem getting a connection.");
        }
        dt.updateMovie(arg0, arg1);
        System.out.println("updateMovie has been printed. Shutting down...");
        System.exit(1);
    }
}
