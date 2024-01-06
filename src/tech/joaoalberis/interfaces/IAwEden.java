package tech.joaoalberis.interfaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import tech.joaoalberis.enums.TypeAws;
import tech.joaoalberis.exception.InvalidAwException;
import tech.joaoalberis.exception.InvalidTypeAwException;
import tech.joaoalberis.exception.InvalidTypeItemException;

public interface IAwEden {

    public TypeAws[] listTypes();
    public String listTypesString();
    public String getAwName(ItemStack itemStack) throws InvalidAwException;
    public ItemStack addSkin(ItemStack itemStack, String awName, TypeAws typeAw) throws InvalidTypeItemException, InvalidAwException;
    public ItemStack setSkin(ItemStack itemStack, String awName, TypeAws typeAw) throws InvalidTypeItemException, InvalidAwException;
    public ItemStack removeSkin(ItemStack itemStack) throws InvalidAwException;
    public void verifyTypeAwWithItem(Material typeItem, TypeAws typeAw) throws InvalidTypeItemException;
}
