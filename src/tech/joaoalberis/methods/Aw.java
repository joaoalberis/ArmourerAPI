package tech.joaoalberis.methods;

import net.minecraft.server.v1_7_R4.NBTTagByte;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.NBTTagInt;
import net.minecraft.server.v1_7_R4.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import tech.joaoalberis.enums.TypeAws;
import tech.joaoalberis.exception.InvalidAwException;
import tech.joaoalberis.exception.InvalidTypeItemException;
import tech.joaoalberis.interfaces.IAwEden;

import java.util.EnumSet;
import java.util.Set;

public class Aw implements IAwEden {

    Set<Material> weapons = EnumSet.of(
            Material.DIAMOND_SWORD,
            Material.IRON_SWORD,
            Material.GOLD_SWORD,
            Material.WOOD_SWORD,
            Material.STONE_SWORD
    );

    Set<Material> toolAxe = EnumSet.of(
            Material.WOOD_AXE,
            Material.STONE_AXE,
            Material.IRON_AXE,
            Material.GOLD_AXE,
            Material.DIAMOND_AXE
    );

    Set<Material> toolHoe = EnumSet.of(
            Material.WOOD_HOE,
            Material.STONE_HOE,
            Material.IRON_HOE,
            Material.GOLD_HOE,
            Material.DIAMOND_HOE
    );

    Set<Material> toolShovel = EnumSet.of(
            Material.WOOD_SPADE,
            Material.STONE_SPADE,
            Material.IRON_SPADE,
            Material.GOLD_SPADE,
            Material.DIAMOND_SPADE
    );

    Set<Material> toolPickaxe = EnumSet.of(
            Material.WOOD_PICKAXE,
            Material.STONE_PICKAXE,
            Material.IRON_PICKAXE,
            Material.GOLD_PICKAXE,
            Material.DIAMOND_PICKAXE
    );

    Set<Material> armorChest = EnumSet.of(
            Material.LEATHER_CHESTPLATE,
            Material.IRON_CHESTPLATE,
            Material.DIAMOND_CHESTPLATE,
            Material.GOLD_CHESTPLATE
    );

    Set<Material> armorLegs = EnumSet.of(
            Material.LEATHER_LEGGINGS,
            Material.IRON_LEGGINGS,
            Material.DIAMOND_LEGGINGS,
            Material.GOLD_LEGGINGS
    );

    Set<Material> armorBoots = EnumSet.of(
            Material.LEATHER_BOOTS,
            Material.IRON_BOOTS,
            Material.DIAMOND_BOOTS,
            Material.GOLD_BOOTS
    );

    Set<Material> armorHead = EnumSet.of(
            Material.LEATHER_HELMET,
            Material.IRON_HELMET,
            Material.DIAMOND_HELMET,
            Material.GOLD_HELMET
    );

    @Override
    public String listTypesString() {
        TypeAws[] types = TypeAws.values();
        StringBuilder builder = new StringBuilder("Rede Eden | §cAW's List: §a");
        for (TypeAws type : types){
            if(type.equals(types[types.length - 1])){
                builder.append(type);
                break;
            }
            builder.append(type + ", ");
        }
        return builder.toString();
    }

    @Override
    public TypeAws[] listTypes(){
        return TypeAws.values();
    }

    @Override
    public String getAwName(ItemStack itemStack) throws InvalidAwException {
        net.minecraft.server.v1_7_R4.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);

        if (nms.getTag() == null || !nms.getTag().hasKey("armourersWorkshop")){
            throw new InvalidAwException("Este item não possui uma AW! ");
        }

        String libraryFile = nms.getTag().getCompound("armourersWorkshop").getCompound("identifier")
                .getString("libraryFile");

        String[] name = libraryFile.split("/");

        return name[name.length - 1];
    }
    @Override
    public ItemStack addSkin(ItemStack itemStack, String awName, TypeAws typeAw) throws InvalidTypeItemException, InvalidAwException {

        verifyTypeAwWithItem(itemStack.getType(), typeAw);

        String type = typeAw.getName();
        String libraryFile =  awName;

        net.minecraft.server.v1_7_R4.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);

        if (nms.tag == null){
            nms.tag = new NBTTagCompound();
        }

        if (nms.getTag().hasKey("armourersWorkshop")) throw new InvalidAwException("Você não pode adicionar uma aw em um item que ja contem, utilize o edit!");

        NBTTagCompound compoundSkin = new NBTTagCompound();

        compoundSkin.set("identifier", new NBTTagCompound());
        NBTTagCompound itentifier = compoundSkin.getCompound("identifier");
        itentifier.set("globalId", new NBTTagInt(0));
        itentifier.set("skinType", new NBTTagString(type));
        itentifier.set("libraryFile", new NBTTagString(libraryFile));
        itentifier.set("localId", new NBTTagInt(0));
        compoundSkin.set("lock", new NBTTagByte((byte) 1));
        compoundSkin.set("dyeData", new NBTTagCompound());
        NBTTagCompound dyeData = compoundSkin.getCompound("dyeData");
        dyeData.set("dye0r", new NBTTagByte((byte) -1));
        dyeData.set("dye0b", new NBTTagByte((byte) -1));
        dyeData.set("dye0t", new NBTTagByte((byte) -1));
        dyeData.set("dye0g", new NBTTagByte((byte) -1));
        nms.tag.set("armourersWorkshop", compoundSkin);

        itemStack = CraftItemStack.asCraftMirror(nms);

        return itemStack;
    }

    @Override
    public ItemStack setSkin(ItemStack itemStack, String awName, TypeAws typeAw) throws InvalidTypeItemException, InvalidAwException {

        verifyTypeAwWithItem(itemStack.getType(), typeAw);

        net.minecraft.server.v1_7_R4.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);

        if (nms.getTag() == null || !nms.getTag().hasKey("armourersWorkshop")){
            throw new InvalidAwException("Para editar uma skin é necessário que o item já possua uma skin AW.");
        }

        NBTTagString aw = new NBTTagString(awName);

        nms.getTag().getCompound("armourersWorkshop").getCompound("identifier")
                .set("libraryFile", aw);

        nms.getTag().getCompound("armourersWorkshop").getCompound("identifier")
                .set("localId", new NBTTagInt(0));

        itemStack = CraftItemStack.asCraftMirror(nms);

        return itemStack;
    }

    @Override
    public ItemStack removeSkin(ItemStack itemStack) throws InvalidAwException {
        net.minecraft.server.v1_7_R4.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);

        if (nms.getTag() == null || !nms.getTag().hasKey("armourersWorkshop")) throw new InvalidAwException("Não é possivel remover uma AW de um item que não possui uma AW");

        nms.getTag().remove("armourersWorkshop");
        itemStack = CraftItemStack.asCraftMirror(nms);
        return itemStack;
    }

    @Override
    public void verifyTypeAwWithItem(Material typeItem, TypeAws typeAw) throws InvalidTypeItemException {
        switch (typeAw) {
            case sword:
                if (!weapons.contains(typeItem)) {
                    throw new InvalidTypeItemException("O item em sua mão não equivale ao tipo de AW colocado no comando!");
                }
                break;
            case pickaxe:
                if (!toolPickaxe.contains(typeItem)) {
                    throw new InvalidTypeItemException("O item em sua mão não equivale ao tipo de AW colocado no comando!");
                }
                break;
            case axe:
                if (!toolAxe.contains(typeItem)) {
                    throw new InvalidTypeItemException("O item em sua mão não equivale ao tipo de AW colocado no comando!");
                }
                break;
            case head:
                if (!armorHead.contains(typeItem)) {
                    throw new InvalidTypeItemException("O item em sua mão não equivale ao tipo de AW colocado no comando!");
                }
                break;
            case chest:
                if (!armorChest.contains(typeItem)) {
                    throw new InvalidTypeItemException("O item em sua mão não equivale ao tipo de AW colocado no comando!");
                }
                break;
            case legs:
                if (!armorLegs.contains(typeItem)) {
                    throw new InvalidTypeItemException("O item em sua mão não equivale ao tipo de AW colocado no comando!");
                }
                break;
            case feet:
                if (!armorBoots.contains(typeItem)) {
                    throw new InvalidTypeItemException("O item em sua mão não equivale ao tipo de AW colocado no comando!");
                }
                break;
            case bow:
                if (!Material.BOW.equals(typeItem)) {
                    throw new InvalidTypeItemException("O item em sua mão não equivale ao tipo de AW colocado no comando!");
                }
                break;
            case shovel:
                if (!toolShovel.contains(typeItem)) {
                    throw new InvalidTypeItemException("O item em sua mão não equivale ao tipo de AW colocado no comando!");
                }
                break;
            case hoe:
                if (!toolHoe.contains(typeItem)) {
                    throw new InvalidTypeItemException("O item em sua mão não equivale ao tipo de AW colocado no comando!");
                }
                break;
            default:
                throw new InvalidTypeItemException("tipo invalido!");
        }
    }
}
