package net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.type;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.magic.IMagic;
import net.sf.anathema.character.main.magic.model.charm.ICharmTypeVisitor;
import net.sf.anathema.character.main.magic.model.charm.type.CharmType;
import net.sf.anathema.character.main.magic.model.charm.type.ICharmTypeModel;
import net.sf.anathema.character.main.magic.model.charm.type.IReflexiveSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.type.ISimpleSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.type.TurnType;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.ICharmTypeStringBuilder;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.IMagicTooltipStringBuilder;
import net.sf.anathema.lib.gui.TooltipBuilder;
import net.sf.anathema.lib.resources.Resources;

import java.text.MessageFormat;

public abstract class AbstractCharmTypeStringBuilder implements ICharmTypeStringBuilder, IMagicTooltipStringBuilder {

  private final boolean displayDefaultValues;
  private final Resources resources;

  public AbstractCharmTypeStringBuilder(Resources resources, boolean displayDefaultValues) {
    this.resources = resources;
    this.displayDefaultValues = displayDefaultValues;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object details) {
    if (magic instanceof ICharm) {
      builder.append(resources.getString("CharmTreeView.ToolTip.Type"));
      builder.append(TooltipBuilder.ColonSpace);
      builder.append(createTypeString(((ICharm) magic).getCharmTypeModel()));
      builder.append(TooltipBuilder.HtmlLineBreak);
    }
  }

  protected abstract StringBuilder buildDefenseString(ISimpleSpecialsModel model, boolean defaultSpeed, boolean longAction);

  private StringBuilder buildReflexiveModelString(IReflexiveSpecialsModel model) {
    StringBuilder builder = new StringBuilder();
    builder.append(TooltipBuilder.Space);
    builder.append("(");
    MessageFormat formatter = new MessageFormat("");
    Object[] objects;
    if (model.getSecondaryStep() == null) {
      formatter.applyPattern(getResources().getString(getReflexiveSingleStepPattern()));
      if (model.getPrimaryStep() == null) {
        objects = new Object[]{-1};
      } else {
        objects = new Object[]{model.getPrimaryStep()};
      }
    } else {
      formatter.applyPattern(getResources().getString(getReflexiveDualStepPattern()));
      if (model.getPrimaryStep() == null) {
        objects = new Object[]{-1, model.getSecondaryStep()};
      } else {
        objects = new Object[]{model.getPrimaryStep(), model.getSecondaryStep()};
      }
    }
    builder.append(formatter.format(objects));
    builder.append(")");
    return builder;
  }

  private StringBuilder buildSimpleModelString(ISimpleSpecialsModel model) {
    StringBuilder builder = new StringBuilder();
    boolean defaultSpeed = model.getSpeed() == ISimpleSpecialsModel.DEFAULT_SPEED;
    boolean defaultDefense = model.getDefenseModifier() == ISimpleSpecialsModel.DEFAULT_DEFENSE_MODIFIER;
    boolean defaultTurnType = model.getTurnType() == TurnType.Tick;
    if (defaultSpeed && defaultDefense && defaultTurnType) {
      return builder;
    }
    builder.append(TooltipBuilder.Space);
    builder.append("(");
    boolean dramaticAction = model.getTurnType() == TurnType.DramaticAction;
    boolean longTick = model.getTurnType() == TurnType.LongTick;
    if (dramaticAction) {
      builder.append(getResources().getString(getDramaticActionKey()));
    } else {
      if (!defaultSpeed || longTick || displayDefaultValues) {
        builder.append(buildSpeedString(model));
      }
    }
    if (!defaultDefense || displayDefaultValues) {
      builder.append(buildDefenseString(model, defaultSpeed, dramaticAction || longTick));
    }
    builder.append(")");
    return builder;
  }

  protected abstract StringBuilder buildSpeedString(ISimpleSpecialsModel model);

  @Override
  public String createTypeString(final ICharmTypeModel charmTypeModel) {
    final StringBuilder builder = new StringBuilder();
    CharmType charmType = charmTypeModel.getCharmType();
    builder.append(getResources().getString(charmType.getId()));
    if (charmTypeModel.hasSpecialsModel()) {
      charmType.accept(new ICharmTypeVisitor() {
        @Override
        public void visitExtraAction(CharmType visitedType) {
          // Nothing to do
        }

        @Override
        public void visitPermanent(CharmType visitedType) {
          // Nothing to do
        }

        @Override
        public void visitReflexive(CharmType visitedType) {
          builder.append(buildReflexiveModelString((IReflexiveSpecialsModel) charmTypeModel.getSpecialsModel()));
        }

        @Override
        public void visitSimple(CharmType visitedType) {
          builder.append(buildSimpleModelString((ISimpleSpecialsModel) charmTypeModel.getSpecialsModel()));
        }

        @Override
        public void visitSpecial(CharmType visitedType) {
          // Nothing to do
        }

        @Override
        public void visitSupplemental(CharmType visitedType) {
          // Nothing to do
        }
      });
    }
    return builder.toString();
  }

  protected abstract String getDramaticActionKey();

  protected abstract String getReflexiveDualStepPattern();

  protected abstract String getReflexiveSingleStepPattern();

  protected Resources getResources() {
    return resources;
  }

}
