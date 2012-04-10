package net.sf.anathema.character.generic.character;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.health.HealthLevelType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IdentifiedInteger;

import java.util.List;

public interface IGenericCharacter extends ILimitationContext, IMagicCollection, IGenericCharmConfiguration {

  public boolean isAlienCharm(ICharm charm);

  public ICharacterTemplate getTemplate();

  public IGenericTrait[] getBackgrounds();

  public INamedGenericTrait[] getSpecialties(ITraitType traitType);

  public INamedGenericTrait[] getSubTraits(ITraitType traitType);

  public int getHealthLevelTypeCount(HealthLevelType type);

  public String getPeripheralPool();

  public int getPeripheralPoolValue();

  public String getPersonalPool();

  public int getPersonalPoolValue();

  public int getOverdrivePoolValue();

  public IdentifiedInteger[] getComplexPools();

  public int getAttunedPoolValue();

  public IAdditionalModel getAdditionalModel(String templateId);

  public IConcept getConcept();

  public List<IMagic> getAllLearnedMagic();

  public int getLearnCount(ICharm charm);

  public IGenericCombo[] getCombos();

  public boolean isExperienced();

  public int getPainTolerance();

  public IIdentifiedTraitTypeGroup[] getAbilityTypeGroups();

  public IIdentifiedTraitTypeGroup[] getAttributeTypeGroups();

  public IIdentifiedTraitTypeGroup[] getYoziTypeGroups();

  public int getTotalExperiencePoints();

  public int getSpentExperiencePoints();

  public String[] getLearnedEffects(ICharm charm);

  public boolean isMultipleEffectCharm(ICharm magic);

  public boolean isSubeffectCharm(ICharm magic);

  public void addSpecialtyListChangeListener(IChangeListener listener);
  
  public ICharm[] getGenericCharms();

  <T> List<T> getAllRegistered(Class<T> interfaceClass);
}