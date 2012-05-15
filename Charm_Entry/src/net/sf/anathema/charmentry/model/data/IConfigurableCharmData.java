package net.sf.anathema.charmentry.model.data;

import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IExtendedCharmData;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IConfigurableCharmData extends IExtendedCharmData {

  ITextualDescription getName();

  @Override
  IConfigurableCostList getTemporaryCost();

  @Override
  CharmTypeModel getCharmTypeModel();

  void setCharacterType(ICharacterType type);

  void setEssencePrerequisite(IGenericTrait type);

  void setPrimaryPrerequisite(IGenericTrait trait);

  void setDuration(IDuration duration);

  void setParentCharms(ICharm[] charms);

  void setExcellencyRequired(boolean required);

  void addAttribute(ICharmAttribute charmAttribute);

  void removeAttribute(ICharmAttribute charmAttribute);

  void setId(String id);

  void setGroupId(String id);

  void setSource(IExaltedSourceBook sourceBook);

  int getPage();

  void setPage(int newValue);

  IGenericTrait getPrimaryPrerequisite();
}