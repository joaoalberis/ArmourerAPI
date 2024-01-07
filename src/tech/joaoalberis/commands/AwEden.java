package tech.joaoalberis.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tech.joaoalberis.enums.TypeAws;
import tech.joaoalberis.exception.InvalidAwException;
import tech.joaoalberis.exception.InvalidTypeAwException;
import tech.joaoalberis.exception.InvalidTypeItemException;
import tech.joaoalberis.exception.NoItemInHandException;
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
                try {
                    switch (option){
                        case "edit":
                            if (args[1] != null && args[2] != null && verifyExistsItem(itemStack)){
                                ItemStack newItem = aw.setSkin(itemStack, args[1], TypeAws.valueOf(args[2]));
                                player.setItemInHand(newItem);
                                player.sendMessage("§a§lRede§f§lEden §7| §aSkin AW foi editada de: §c" + aw.getAwName(itemStack) + " §apara §e" + aw.getAwName(newItem));
                            }
                            break;
                        case "add":
                            if (args[1] != null && args[2] != null && verifyExistsItem(itemStack)){
                                ItemStack newItem = aw.addSkin(itemStack, args[1], TypeAws.valueOf(args[2]));
                                player.setItemInHand(newItem);
                                player.sendMessage("§a§lRede§f§lEden §7| §aSkin AW §c" + aw.getAwName(newItem) + " §afoi colocado no item §e" + newItem.getType().toString());
                            }
                            break;
                        case "remove":
                            ItemStack newItem = aw.removeSkin(itemStack);
                            player.setItemInHand(newItem);
                            player.sendMessage("§a§lRede§f§lEden §7| §aSkin "+ aw.getAwName(itemStack) + " foi removida!");
                            break;
                        case "list":
                            String types = aw.listTypesString();
                            player.sendMessage(types);
                            break;
                        case "view":
                            String awName = aw.getAwName(itemStack);
                            player.sendMessage("§a§lRede§f§lEden §7| §aA AW atual deste item é: §c" + awName);
                            break;
                        case "help":
                            sendMessageHelp(player);
                            break;
                        default:
                            player.sendMessage(ChatColor.DARK_RED + "Comando Incorreto. Utilize " + ChatColor.GREEN + "/aweden help " + ChatColor.DARK_RED + "para obter ajuda.");
                            break;
                    }
                }catch (IllegalArgumentException ex){
                    try {
                        throw new InvalidTypeAwException("§a§lRede§f§lEden §7| §cEsse tipo AW não existe, use /aweden list e veja as AW's existentes.");
                    } catch (InvalidTypeAwException e) {
                        player.sendMessage(e.getMessage());
                    }
                } catch (InvalidTypeItemException | InvalidAwException | NoItemInHandException e) {
                    player.sendMessage("§a§lRede§f§lEden §7| §c" + e.getMessage());
                }

            }else {
                player.sendMessage(ChatColor.DARK_GREEN + "ArmourerApi version 1.1 by MrJoao");
                player.sendMessage(ChatColor.GOLD + "Use " + ChatColor.DARK_GREEN + "/aweden help " + ChatColor.GOLD + "for help.");
            }
        }
        return true;
    }

    private boolean verifyExistsItem(ItemStack itemStack) throws NoItemInHandException {
        if (itemStack.getType().equals(Material.AIR)){
            throw new NoItemInHandException("Para utilizar este comando é necessario possuir um item em sua mão.");
        }
        return true;
    }

    private void sendMessageHelp(Player player){
        player.sendMessage("§e-------------------------------");
        player.sendMessage("§a➛ /aweden add (NameArmourer) (Type) - Adiciona uma skin AW em um item.");
        player.sendMessage("§a➛ /aweden edit (NameArmourer) (Type) - Edita a AW de um item.");
        player.sendMessage("§a➛ /aweden remove  - Remove a skin AW de um item.");
        player.sendMessage("§a➛ /aweden view - Serve para ver o nome da AW de um Item.");
        player.sendMessage("§a➛ /aweden list - Mostra todos os tipos de AW's disponiveis.");
        player.sendMessage("§e-------------------------------");
    }
}
