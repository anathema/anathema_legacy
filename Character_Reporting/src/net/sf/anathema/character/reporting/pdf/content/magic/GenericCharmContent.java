package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.ITraitTypeGroup;
import net.sf.anathema.character.reporting.pdf.content.SubBoxContent;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TYPE_LONG_FORM_CUTOFF;

public class GenericCharmContent implements SubBoxContent {

  private IResources resources;
  private IGenericCharacter character;

  public GenericCharmContent(IResources resources, IGenericCharacter character) {
    this.resources = resources;
    this.character = character;
  }

  @Override
  public String getHeader() {
    return resources.getString("Sheet.Header.GenericCharms");
  }

  @Override
  public boolean hasContent() {
    return getGenericCharmCount() > 0;
  }

  public List<String> getTraitLabels() {
    List<String> traits = new ArrayList<String>();
    for (ITraitType trait : getTraits()) {
      String text = getTraitLabel(trait);
      traits.add(text);
    }
    return traits;
  }

  private String getTraitLabel(ITraitType trait) {
    String text = resources.getString(trait.getId());
    if (text.length() >= TYPE_LONG_FORM_CUTOFF) {
      return resources.getString(trait.getId() + ".Short");
    }
    return text;
  }

  private List<ITraitType> getTraits() {
    IIdentifiedTraitTypeGroup[] groups = getCharmTraitGroups();
    return getAllTraitsFor(groups);
  }

  private IIdentifiedTraitTypeGroup[] getCharmTraitGroups() {
    FavoringTraitType type = character.getTemplate().getMagicTemplate().getFavoringTraitType();
    if (type == FavoringTraitType.AbilityType) {
      return character.getAbilityTypeGroups();
    }
    if (type == FavoringTraitType.AttributeType) {
      return character.getAttributeTypeGroups();
    }
    if (type == FavoringTraitType.YoziType) {
      return character.getYoziTypeGroups();
    }
    return new IIdentifiedTraitTypeGroup[0];
  }

  private List<ITraitType> getAllTraitsFor(IIdentifiedTraitTypeGroup[] groups) {
    List<ITraitType> traits = new ArrayList<ITraitType>();
    for (ITraitTypeGroup group : groups) {
      for (ITraitType trait : group.getAllGroupTypes()) {
        traits.add(trait);
      }
    }
    return traits;
  }

  public int getGenericCharmCount() {
    return character.getGenericCharmStats().length;
  }
}
