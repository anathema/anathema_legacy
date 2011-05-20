package net.sf.anathema.character.thaumaturgy.model;

public class ThaumaturgyProvider
{
	public static String[] getArts()
	{
		return new String[] { "Astrology", "Enchantment" };
	}
	
	public static String[] getProcedures(String art)
	{
		if (art.equals("Astrology"))
			return new String[] { "CompileChart", "VarangianCasting"};
		return new String[0];
	}
	
	
}
