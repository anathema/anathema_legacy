package net.sf.anathema.character.generic.framework.magic;

import static java.text.MessageFormat.format;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.MagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.ShortCharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class ConfigurableGenericCharmStats implements IMagicStats
{
	private final String name;
	private final IExaltedSourceBook sourcebook;
	private final ICharacterType characterType;
	private final ICharmTypeModel typeModel;
	private final IDuration duration;
	private final String costString;
	private final boolean comboOK;
	
	public ConfigurableGenericCharmStats(String name,
			ICharacterType type,
			IExaltedSourceBook source,
			ICharmTypeModel typeModel,
			String costString,
			IDuration duration,
			boolean comboOK)
	{
		this.name = name;
		this.sourcebook = source;
		this.comboOK = comboOK;
		this.characterType = type;
		this.typeModel = typeModel;
		this.duration = duration;
		this.costString = costString;
	}

	@Override
	public String getSourceString(IResources resources)
	{
		return new MagicSourceStringBuilder<IMagic>(resources).createShortSourceString(getSourceBook(), getId());
	}

	public ExaltedSourceBook getSourceBook()
	{
		return (ExaltedSourceBook) sourcebook;
	}

	@Override
	public String[] getDetailStrings(IResources resources)
	{
	  String description = resources.getString(getId() + ".Description");
	  String cleanedDescription = format(description, resources.getString(getCharacterType().getFavoringTraitType().getId()));
	  return new String[]{cleanedDescription}; //$NON-NLS-1$
	}

	@Override
	public final String getGroupName(IResources resources)
	{
		return resources.getString("Generics"); //$NON-NLS-1$
	}

	@Override
	public final IIdentificate getName()
	{
		return new Identificate(getId());
	}

	@Override
	public final String getNameString(IResources resources)
	{
		return resources.getString(getId());
	}

	public String getId()
	{
		return name;
	}

	public boolean isComboOk()
	{
		return comboOK;
	}

	public ICharacterType getCharacterType()
	{
		return characterType;
	}
	
	@Override
	public String getCostString(IResources resources) {
		return costString;
	}

	@Override
	public String getType(IResources resources) {
		return new ShortCharmTypeStringBuilder(resources).createTypeString(typeModel);
	}

	@Override
	public String getDurationString(IResources resources)
	{
		return duration.getText(resources);
	}
	
	public boolean equals(Object obj)
	{
		if (obj instanceof IMagicStats)
			return this.getId().equals(((IMagicStats)obj).getName().getId());
		return false;
	}
	
	@Override
	public int compareTo(IMagicStats stats) {
	  if (stats instanceof ConfigurableGenericCharmStats) {
	    return this.getId().compareTo(stats.getName().getId());
	  } else {
	    return -1;
	  }
	}
}
