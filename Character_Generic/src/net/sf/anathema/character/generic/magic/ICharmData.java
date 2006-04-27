package net.sf.anathema.character.generic.magic;

import java.util.Set;

import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.character.generic.magic.charms.ICharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.charms.IComboRestrictions;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public interface ICharmData extends IMagicData {
  public static final IIdentificate FORM_ATTRIBUTE = new Identificate("Form"); //$NON-NLS-1$
  public static final String ALLOWS_CELESTIAL_ATTRIBUTE = "AllowsCelestial"; //$NON-NLS-1$
  public static final IIdentificate UNRESTRICTED_ATTRIBUTE = new Identificate("Unrestricted"); //$NON-NLS-1$
  public static final IIdentificate NO_STYLE_ATTRIBUTE = new Identificate("NoStyle"); //$NON-NLS-1$

  public CharacterType getCharacterType();

  public Duration getDuration();

  public IGenericTrait getEssence();

  public IGenericTrait[] getPrerequisites();

  public String getGroupId();

  public IComboRestrictions getComboRules();

  public ICharmAttributeRequirement[] getAttributeRequirements();

  public Set<ICharm> getParentCharms();

  public ICharmTypeModel getCharmTypeModel();
}