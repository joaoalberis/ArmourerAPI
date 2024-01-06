package tech.joaoalberis;

import cpw.mods.fml.common.Loader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import tech.joaoalberis.commands.AwEden;
import tech.joaoalberis.exception.LoaderModException;

import java.io.File;


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
        try {
            if (!Loader.isModLoaded("armourersWorkshop")){
                throw new LoaderModException("§4[ArmourerAPI] - Plugin não foi habilitado", new Throwable("§4[ArmourerAPI] - mod Armourer's Workshop não foi encontrado"));
            }
            Bukkit.getConsoleSender().sendMessage("§2[ArmourerAPI] - Mod encontrado com sucesso!");
            Bukkit.getConsoleSender().sendMessage("§2[ArmourerAPI] - Plugin iniciado com sucesso!");
            registerCommand();
            createConfig();
        }catch (LoaderModException e) {
            Bukkit.getConsoleSender().sendMessage(e.getCause().toString());
            Bukkit.getConsoleSender().sendMessage(e.getMessage());
        }
    }

    private void createConfig(){
        File file = new File(getDataFolder(), "teste.yml");
        if (!file.exists()){
            file.getParentFile().mkdir();
            saveResource("teste.yml", false);
        }
    }

    private void registerCommand(){
        getCommand("aweden").setExecutor(new AwEden());
    }
}