package tech.joaoalberis;

import cpw.mods.fml.common.Loader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tech.joaoalberis.commands.AwEden;


public class ArmourerApi extends JavaPlugin {
    @Override
    public void onLoad() {
        Bukkit.getConsoleSender().sendMessage("§2[ArmourerAPI] - Plugin carregando..");
        Bukkit.getConsoleSender().sendMessage("§2[ArmourerAPI] - Verificando mod Armourer's Workshop!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§4[ArmourerAPI] - Plugin desativado com sucesso!");
    }

    @Override
    public void onEnable() {
        if (Loader.isModLoaded("armourersWorkshop")){
            Bukkit.getConsoleSender().sendMessage("§2[ArmourerAPI] - Mod encontrado com sucesso!");
            registerCommand();
        }else{
            Bukkit.getConsoleSender().sendMessage("§4[ArmourerAPI| - Mod não encontrado");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private void registerCommand(){
        getCommand("aweden").setExecutor(new AwEden());
    }
}