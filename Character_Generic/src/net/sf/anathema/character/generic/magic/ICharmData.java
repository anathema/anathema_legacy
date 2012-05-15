package net.sf.anathema.character.generic.magic;

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

import java.util.List;
import java.util.Set;

public interface ICharmData extends IMagicData {
  IIdentificate FORM_ATTRIBUTE = new Identificate("Form"); //$NON-NLS-1$
  IIdentificate MERGED_ATTRIBUTE = new Identificate("Merged"); //$NON-NLS-1$
  IIdentificate ALLOWS_CELESTIAL_ATTRIBUTE = new Identificate("AllowsCelestial"); //$NON-NLS-1$
  IIdentificate UNRESTRICTED_ATTRIBUTE = new Identificate("Unrestricted"); //$NON-NLS-1$
  IIdentificate NO_STYLE_ATTRIBUTE = new Identificate("NoStyle"); //$NON-NLS-1$
  IIdentificate NATIVE = new Identificate("Native"); //$NON-NLS-1$
  String FAVORED_CASTE_PREFIX = "FavoredCaste."; //$NON-NLS-1$

  ICharacterType getCharacterType();

  IDuration getDuration();

  IGenericTrait getEssence();

  IGenericTrait[] getPrerequisites();

  ITraitType getPrimaryTraitType();

  String getGroupId();

  IComboRestrictions getComboRules();

  ICharmAttribute[] getAttributes();

  ICharmAttributeRequirement[] getAttributeRequirements();

  Set<ICharm> getParentCharms();
  
  List<String> getParentSubeffects();

  ICharmTypeModel getCharmTypeModel();
  
  boolean isInstanceOfGenericCharm();
}