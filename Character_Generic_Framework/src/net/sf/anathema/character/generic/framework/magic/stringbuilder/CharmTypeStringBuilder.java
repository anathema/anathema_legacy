package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import java.text.MessageFormat;

import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.magic.charms.type.IReflexiveSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.ISimpleSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;
import net.sf.anathema.lib.resources.IResources;

public class CharmTypeStringBuilder implements ICharmTypeStringBuilder {

  private final IResources resources;

  public CharmTypeStringBuilder(IResources resources) {
    this.resources = resources;
  }

  public String createTypeString(final ICharmTypeModel charmTypeModel) {
    final StringBuffer buffer = new StringBuffer();
    final CharmType charmType = charmTypeModel.getCharmType();
    buffer.append(resources.getString(charmType.getId()));
    if (charmTypeModel.hasSpecialsModel()) {
      charmType.accept(new ICharmTypeVisitor() {
        public void visitExtraAction(CharmType visitedType) {
          // Nothing to do
        }

        public void visitReflexive(CharmType visitedType) {
          buffer.append(buildReflexiveModelString((IReflexiveSpecialsModel) charmTypeModel.getSpecialsModel()));
        }

        public void visitSimple(CharmType visitedType) {
          buffer.append(buildSimpleModelString((ISimpleSpecialsModel) charmTypeModel.getSpecialsModel()));
        }

        public void visitSpecial(CharmType visitedType) {
          // Nothing to do
        }

        public void visitPermanent(CharmType visitedType) {
          // Nothing to do
        }

        public void visitSupplemental(CharmType visitedType) {
          // Nothing to do
        }
      });
    }
    return buffer.toString();
  }

  private StringBuffer buildReflexiveModelString(IReflexiveSpecialsModel model) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(IMagicStringBuilderConstants.Space);
    buffer.append("("); //$NON-NLS-1$    
    MessageFormat formatter = new MessageFormat(""); //$NON-NLS-1$
    Object[] objects;
    if (model.getSecondaryStep() == null) {
      formatter.applyPattern(resources.getString("CharmTreeView.ToolTip.Type.SingleStep")); //$NON-NLS-1$
      objects = new Object[] { model.getPrimaryStep() };
    }
    else {
      formatter.applyPattern(resources.getString("CharmTreeView.ToolTip.Type.DualStep")); //$NON-NLS-1$
      objects = new Object[] { model.getPrimaryStep(), model.getSecondaryStep() };
    }
    buffer.append(formatter.format(objects));
    buffer.append(")"); //$NON-NLS-1$
    return buffer;
  }

  private StringBuffer buildSimpleModelString(ISimpleSpecialsModel model) {
    StringBuffer buffer = new StringBuffer();
    final boolean defaultSpeed = model.getSpeed() == ISimpleSpecialsModel.DEFAULT_SPEED;
    final boolean defaultDefense = model.getDefenseModifier() == ISimpleSpecialsModel.DEFAULT_DEFENSE_MODIFIER;
    if (defaultSpeed && defaultDefense) {
      return buffer;
    }
    buffer.append(IMagicStringBuilderConstants.Space);
    buffer.append("("); //$NON-NLS-1$
    final boolean dramaticAction = model.getTurnType() == TurnType.DramaticAction;
    if (dramaticAction) {
      buffer.append(resources.getString("CharmTreeView.ToolTip.Type.DramaticAction")); //$NON-NLS-1$
    }
    else if (!defaultSpeed) {
      buffer.append(buildSpeedString(model));
    }
    if (!defaultDefense) {
      buffer.append(buildDefenseString(model, defaultSpeed, dramaticAction));
    }
    buffer.append(")"); //$NON-NLS-1$
    return buffer;
  }

  private StringBuffer buildSpeedString(ISimpleSpecialsModel model) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(resources.getString("CharmTreeView.ToolTip.Type.Speed")); //$NON-NLS-1$
    buffer.append(IMagicStringBuilderConstants.Space);
    buffer.append(model.getSpeed());
    if (model.getTurnType() == TurnType.LongTick) {
      buffer.append(IMagicStringBuilderConstants.Space);
      buffer.append(resources.getString("CharmTreeView.ToolTip.Type.LongTick")); //$NON-NLS-1$
    }
    return buffer;
  }

  private StringBuffer buildDefenseString(
      ISimpleSpecialsModel model,
      final boolean defaultSpeed,
      final boolean dramaticAction) {
    StringBuffer buffer = new StringBuffer();
    if (!defaultSpeed || dramaticAction) {
      buffer.append(IMagicStringBuilderConstants.CommaSpace);
    }
    buffer.append(resources.getString("CharmTreeView.ToolTip.Type.Defense")); //$NON-NLS-1$
    buffer.append(IMagicStringBuilderConstants.Space);
    final int defenseModifier = model.getDefenseModifier();
    if (defenseModifier == 0) {
      buffer.append("-"); //$NON-NLS-1$
    }
    buffer.append(defenseModifier);
    return buffer;
  }
}