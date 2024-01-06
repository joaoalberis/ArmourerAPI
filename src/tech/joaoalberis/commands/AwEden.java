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
                                player.sendMessage("Rede Eden | Skin AW foi editada de: " + aw.getAwName(itemStack) + " para " + aw.getAwName(newItem));
                            }
                            break;
                        case "add":
                            if (args[1] != null && args[2] != null && verifyExistsItem(itemStack)){
                                ItemStack newItem = aw.addSkin(itemStack, args[1], TypeAws.valueOf(args[2]));
                                player.setItemInHand(newItem);
                                player.sendMessage("Rede Eden | Skin AW " + aw.getAwName(newItem) + " foi colocado no item " + newItem.getType().toString());
                            }
                            break;
                        case "remove":
                            ItemStack newItem = aw.removeSkin(itemStack);
                            player.setItemInHand(newItem);
                            player.sendMessage("Rede Eden | Skin "+ aw.getAwName(itemStack) + " foi removida!");
                            break;
                        case "list":
                            String types = aw.listTypesString();
                            player.sendMessage(types);
                            break;
                        case "view":
                            String awName = aw.getAwName(itemStack);
                            player.sendMessage("Rede Eden | A AW atual deste item é: " + awName);
                            break;
                        case "help":
                            sendMessageHelp(player);
                            break;
                        default:
                            player.sendMessage(ChatColor.RED + "Comando incorreto! Utilize: /aweden help");
                            break;
                    }
                }catch (IllegalArgumentException ex){
                    try {
                        throw new InvalidTypeAwException("Rede Eden | Esse tipo AW não existe, use /aweden list e veja as AW's existentes.");
                    } catch (InvalidTypeAwException e) {
                        player.sendMessage(e.getMessage());
                    }
                } catch (InvalidTypeItemException | InvalidAwException | NoItemInHandException e) {
                    player.sendMessage("Rede Eden | " + e.getMessage());
                }

            }else {
                player.sendMessage(ChatColor.RED + "Comando incorreto! Utilize: /aweden help");
            }
        }
        return true;
    }

    private boolean verifyExistsItem(ItemStack itemStack) throws NoItemInHandException {
        if (itemStack.getType().equals(Material.AIR)){
            throw new NoItemInHandException("Você precisa está com um item na mão!");
        }
        return true;
    }

    private void sendMessageHelp(Player player){
        player.sendMessage("/-/-/-/-/-/-/-/-/-/-/-/-/-/");
        player.sendMessage("- /aweden add (nameArmourer) (Type) -> Adiciona uma aw a um item que não contém nenhuma.");
        player.sendMessage("- /aweden edit (nameArmourer) (Type) -> Edita a aw do item.");
        player.sendMessage("- /aweden remove -> Remove a aw do item.");
        player.sendMessage("- /aweden view -> Usado para receber o nome da aw");
        player.sendMessage("- /aweden list -> Lista todos os tipos disponiveis de Aw's.");
        player.sendMessage("/-/-/-/-/-/-/-/-/-/-/-/-/-/");
    }
}
