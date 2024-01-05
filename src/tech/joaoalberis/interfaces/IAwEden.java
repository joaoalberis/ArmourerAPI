package tech.joaoalberis.interfaces;

import org.bukkit.inventory.ItemStack;
import tech.joaoalberis.enums.TypeAws;

import java.util.List;

public interface IAwEden {

    public TypeAws[] listTypes();
    public String getAwName(ItemStack itemStack);
    public ItemStack addSkin(ItemStack itemStack, String awName, TypeAws typeAw);
    public ItemStack setSkin(ItemStack itemStack, String awName, TypeAws typeAw);
    public ItemStack removeSkin(ItemStack itemStack);
}
