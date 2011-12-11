package net.sf.anathema.character.generic.framework.magic.stringbuilder.type;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmTypeStringBuilder;
import net.sf.anathema.character.generic.impl.magic.charm.type.CharmTypeModel;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.lib.testing.BasicTestCase;

public abstract class AbstractCharmTypeStringBuilderTest extends BasicTestCase {

  public void testNoSpecialModel() throws Exception {
    final CharmTypeModel charmTypeModel = new CharmTypeModel();
    charmTypeModel.setCharmType(CharmType.ExtraAction);
    String string = getBuilder().createTypeString(charmTypeModel);
    assertEquals("Extra Action", string); //$NON-NLS-1$
  }

  protected abstract ICharmTypeStringBuilder getBuilder();
}