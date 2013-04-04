package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.reporting.pdf.content.SubBoxContent;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TYPE_LONG_FORM_CUTOFF;

public class GenericCharmContent implements SubBoxContent {

  private Resources resources;
  private IGenericCharacter character;

  public GenericCharmContent(Resources resources, IGenericCharacter character) {
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
    List<String> traits = new ArrayList<>();
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
    return GenericCharmUtilities.getGenericCharmTraits(character);
  }

  public int getGenericCharmCount() {
    return GenericCharmUtilities.getDisplayedGenericCharmCount(character);
  }
}
