package me.gmx.mtr;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileStuff {

    private Mtr plugin;
    private File file;

    public static void copy (InputStream input, File file){
        try{
            FileOutputStream output = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int i;
            while ((i = input.read(b)) > 0){
                output.write(b,0,i);
            }
            output.close();
            input.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void mkdir(File file){
        try{
            file.mkdir();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public FileStuff(Mtr plugin) {
        this.plugin = plugin;
        System.out.println(plugin.getDataFolder()+"");
        file = new File(plugin.getDataFolder() + "/reports.txt");
    }

    public void addMessage(String message){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.append(message);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setup(){
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void formatMessage(String msg, String name) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm:ss");
        String date = dateFormat.format(new Date());
        String finalMessage = date + " - " + name + ": " + msg + "\n";
        addMessage(finalMessage);

    }
}
