package net.sf.anathema.character.reporting.pdf.content.combo;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.transform;

public class ComboContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public ComboContent(IGenericCharacter character, IResources resources) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Combos";
  }

  @Override
  public boolean hasContent() {
    return getGenericCombos().size() > 0;
  }

  public List<DisplayCombo> getCombos() {
    return transform(getGenericCombos(), new ToDisplayCombo(getResources()));
  }

  private List<IGenericCombo> getGenericCombos() {
    return new ArrayList<IGenericCombo>(Arrays.asList(character.getCombos()));
  }
}
