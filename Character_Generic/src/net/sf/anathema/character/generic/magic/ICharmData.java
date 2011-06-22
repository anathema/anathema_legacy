package net.sf.anathema.character.generic.magic;

import java.util.List;
import java.util.Set;

import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.duration.IDuration;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public interface ICharmData extends IMagicData {
  public static final IIdentificate FORM_ATTRIBUTE = new Identificate("Form"); //$NON-NLS-1$
  public static final IIdentificate MERGED_ATTRIBUTE = new Identificate("Merged"); //$NON-NLS-1$
  public static final IIdentificate ALLOWS_CELESTIAL_ATTRIBUTE = new Identificate("AllowsCelestial"); //$NON-NLS-1$
  public static final IIdentificate UNRESTRICTED_ATTRIBUTE = new Identificate("Unrestricted"); //$NON-NLS-1$
  public static final IIdentificate NO_STYLE_ATTRIBUTE = new Identificate("NoStyle"); //$NON-NLS-1$
  public static final IIdentificate NOT_ALIEN_LEARNABLE = new Identificate("NotAlienLearnable"); //$NON-NLS-1$
  public static final String FAVORED_CASTE_PREFIX = "FavoredCaste."; //$NON-NLS-1$

  public ICharacterType getCharacterType();

  public IDuration getDuration();

  public IGenericTrait getEssence();

  public IGenericTrait[] getPrerequisites();

  public ITraitType getPrimaryTraitType();

  public String getGroupId();

  public IComboRestrictions getComboRules();

  public ICharmAttribute[] getAttributes();

  public ICharmAttributeRequirement[] getAttributeRequirements();

  public Set<ICharm> getParentCharms();
  
  public List<String> getParentSubeffects();

  public ICharmTypeModel getCharmTypeModel();

}