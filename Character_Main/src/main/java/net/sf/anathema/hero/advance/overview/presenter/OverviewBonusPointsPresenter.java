package net.sf.anathema.hero.advance.overview.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.hero.points.overview.SpendingModel;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;

import static java.text.MessageFormat.format;
import static net.sf.anathema.lib.message.MessageDuration.Permanent;
import static net.sf.anathema.lib.message.MessageType.NORMAL;
import static net.sf.anathema.lib.message.MessageType.WARNING;

class OverviewBonusPointsPresenter implements IOverviewSubPresenter {
  private Resources resources;
  private SpendingModel model;
  private IMessaging messaging;

  public OverviewBonusPointsPresenter(Resources resources, SpendingModel model, IMessaging messaging) {
    this.resources = resources;
    this.model = model;
    this.messaging = messaging;
  }

  @Override
  public void update() {
    int spending = model.getValue();
    int allotment = model.getAllotment();
    String pattern;
    MessageType type;
    if (spending <= allotment) {
      pattern = resources.getString("Overview.Creation.BonusPoints.Spent");
      type = NORMAL;
    } else {
      pattern = resources.getString("Overview.Creation.BonusPoints.Overspent");
      type = WARNING;
    }
    messaging.addMessage(new Message(format(pattern, spending, allotment), type, Permanent));
  }
}