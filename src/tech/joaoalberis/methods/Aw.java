package tech.joaoalberis.methods;

import net.minecraft.server.v1_7_R4.NBTTagByte;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.NBTTagInt;
import net.minecraft.server.v1_7_R4.NBTTagString;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import tech.joaoalberis.enums.TypeAws;
import tech.joaoalberis.interfaces.IAwEden;

import java.lang.reflect.Type;
import java.util.List;

public class Aw implements IAwEden {

    @Override
    public TypeAws[] listTypes() {
        return TypeAws.values();
    }

    @Override
    public String getAwName(ItemStack itemStack){
        net.minecraft.server.v1_7_R4.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);

        if (!nms.getTag().hasKey("armourersWorkshop")){
            return "Esse item não contém Aw";
        }

        String name = nms.getTag().getCompound("armourersWorkshop").getCompound("identifier")
                .getString("libraryFile");
        return name;
    }
    @Override
    public ItemStack addSkin(ItemStack itemStack, String awName, TypeAws typeAw) {
        String type = typeAw.getName();
        String libraryFile =  awName;

        net.minecraft.server.v1_7_R4.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);

        if (nms.tag == null){
            nms.tag = new NBTTagCompound();
        }

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
    public ItemStack setSkin(ItemStack itemStack, String awName, TypeAws typeAw) {

        net.minecraft.server.v1_7_R4.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);

        if (nms.tag == null){
            nms.tag = new NBTTagCompound();
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
    public ItemStack removeSkin(ItemStack itemStack) {
        net.minecraft.server.v1_7_R4.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);

        if (nms.tag == null){
            nms.tag = new NBTTagCompound();
        }

        nms.getTag().remove("armourersWorkshop");
        itemStack = CraftItemStack.asCraftMirror(nms);
        return itemStack;
    }
}
