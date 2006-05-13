package net.sf.anathema.charmentry.model.data;

import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IExtendedCharmData;
import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IConfigurableCharmData extends IExtendedCharmData {

  public ITextualDescription getName();

  public IConfigurableMagicSource getSource();

  public IConfigurablePermanentCostList getPermanentCost();

  public IConfigurableCostList getTemporaryCost();

  public IExaltedEdition getEdition();

  public CharmTypeModel getCharmTypeModel();

  public void setCharacterType(CharacterType type);

  public void setEdition(IExaltedEdition edition);

  public void setEssencePrerequisite(IGenericTrait type);

  public void setPrimaryPrerequisite(IGenericTrait trait);

  public IGenericTrait getPrimaryPrerequiste();

  public void setDuration(Duration duration);

  public void setParentCharms(ICharm[] charms);

  public void setExcellencyRequired(boolean required);

  public void addAttribute(ICharmAttribute charmAttribute);

  public void removeAttribute(ICharmAttribute charmAttribute);

  public void setId(String id);

  public void setGroupId(String id);
}