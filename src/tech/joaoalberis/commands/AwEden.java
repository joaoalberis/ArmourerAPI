package tech.joaoalberis.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tech.joaoalberis.enums.TypeAws;
import tech.joaoalberis.methods.Aw;

public class AwEden implements CommandExecutor {

    Aw aw = new Aw();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player))
            sender.sendMessage(ChatColor.RED + "Comando apenas para players.");
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("aweden")) {
            if (args.length != 0){
                ItemStack itemStack = player.getItemInHand();
                String option = args[0];
                switch (option){
                    case "set":
                        if (args[1] != null && args[2] != null ){
                            player.setItemInHand(aw.setSkin(itemStack, args[1], TypeAws.valueOf(args[2])));
                            String[] nameSplit = args[1].split("/");
                            String name = nameSplit[nameSplit.length - 1];
                            player.sendMessage("§aA aw de seu item foi mudada para: " + name);
                        }
                        break;
                    case "add":
                        if (args[1] != null && args[2] != null ){
                            player.setItemInHand(aw.addSkin(itemStack, args[1], TypeAws.valueOf(args[2])));
                            String[] nameSplit = args[1].split("/");
                            String name = nameSplit[nameSplit.length - 1];
                            player.sendMessage("§aItem setado no §b" + itemStack.getType() + "§a com a aw: §e" + name);
                        }
                        break;
                    case "remove":
                        player.setItemInHand(aw.removeSkin(itemStack));
                        String[] nameSplit = args[1].split("/");
                        String name = nameSplit[nameSplit.length - 1];
                        player.sendMessage("§cA skin §e(" + name + ")§c foi removida com sucesso!");
                        break;
                    case "list":
                        TypeAws[] types = aw.listTypes();
                        StringBuilder builder = new StringBuilder();
                        for (TypeAws type : types)
                            builder.append(type + "§e, ");
                        player.sendMessage(builder.toString());
                        break;
                    case "view":
                        player.sendMessage(aw.getAwName(itemStack));
                        break;
                    case "help":
                        sendMessageHelp(player);
                        break;
                    default:
                        player.sendMessage(ChatColor.RED + "Comando incorreto! Utilize: /aweden help");
                        break;
                }
            }else {
                player.sendMessage(ChatColor.RED + "Comando incorreto! Utilize: /aweden help");
            }
        }
        return true;
    }

    private void sendMessageHelp(Player player){
        player.sendMessage("/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        player.sendMessage("- /aweden add (nameArmourer) (Type) -> Adiciona uma aw a um item que não contém nenhuma.");
        player.sendMessage("- /aweden set (nameArmourer) (Type) -> Edita a aw do item.");
        player.sendMessage("- /aweden remove -> Remove a aw do item.");
        player.sendMessage("- /aweden view -> Usado para receber o nome da aw");
        player.sendMessage("- /aweden list -> Lista todos os tipos disponiveis de Aw's.");
        player.sendMessage("/-/-/-/-/-/-/-/-/-/-/-/-/-/");
    }
}
