package net.sf.anathema.character.lunar.reporting.rendering.health;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.FirstEditionHealthAndMovementEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.health.FirstEditionHealthAndMovementTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionLunarHealthAndMovementEncoder extends FirstEditionHealthAndMovementEncoder {

  public FirstEditionLunarHealthAndMovementEncoder(IResources resources) {
    super(resources);
  }

  @Override
  protected final ITableEncoder createTableEncoder() {
    return new FirstEditionHealthAndMovementTableEncoder(getResources()) {
      protected IGenericTraitCollection getTraits(IGenericCharacter character) {
        return ((IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID)).getBeastTraitCollection();
      }
    };
  }
}
