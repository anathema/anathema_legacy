package net.sf.anathema.campaign.music.impl.view.player;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.campaign.music.presenter.IMusicPlayerProperties;
import net.sf.anathema.campaign.music.presenter.MusicUI;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerView;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.lib.gui.action.RunnableAction;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.util.Closure;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.GridLayout;

public class MusicPlayerView implements IMusicPlayerView {
  private final JButton playButton = new JButton();
  private final JButton stopButton = new JButton();
  private final JLabel totalTimeLabel = new JLabel();
  private final JLabel elapsedTimeLabel = new JLabel();
  private final JSlider slider = new JSlider(0, 100, 0);
  private final MusicUI icons;
  private JPanel content;
  private Runnable resumeAction;
  private Runnable playAction;
  private Runnable pauseAction;

  public MusicPlayerView(MusicUI icons) {
    this.icons = icons;
  }

  public JComponent getContent(IMusicPlayerProperties properties) {
    if (content == null) {
      content = createContent(properties);
    }
    return content;
  }

  private JPanel createContent(IMusicPlayerProperties properties) {
    JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill()));
    panel.add(slider);
    panel.add(createTimeDisplay(properties), new CC().alignX("center"));
    panel.add(createControlDisplay(), new CC().alignX("center"));
    panel.setBorder(new TitledBorder(properties.getPlayerBorderString()));
    return panel;
  }

  private JComponent createTimeDisplay(IMusicPlayerProperties properties) {
    JPanel panel = new JPanel();
    panel.add(new JLabel(properties.getTimeString() + ":")); //$NON-NLS-1$
    panel.add(elapsedTimeLabel);
    panel.add(new JLabel("/")); //$NON-NLS-1$
    panel.add(totalTimeLabel);
    return panel;
  }

  private JComponent createControlDisplay() {
    JPanel panel = new JPanel(new GridLayout(1, 0));
    panel.add(playButton);
    panel.add(stopButton);
    return panel;
  }

  @Override
  public void whenPlayIsTriggered(Runnable playAction) {
    this.playAction = playAction;
  }

  @Override
  public void whenPauseIsTriggered(Runnable pauseAction) {
    this.pauseAction = pauseAction;
  }

  @Override
  public void whenResumeIsTriggered(Runnable resumeAction) {
    this.resumeAction = resumeAction;
  }

  @Override
  public void whenStopIsTriggered(Runnable stopAction) {
    stopButton.setAction(new RunnableAction(icons.getStopButtonIcon(), stopAction));
  }

  @Override
  public void addPositionChangeListener(final Closure<Integer> listener) {
    slider.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        if (slider.getValueIsAdjusting()) {
          return;
        }
        int bytes = slider.getValue();
        listener.execute(bytes);
      }
    });
  }

  @Override
  public void setMaximumPosition(int maximum, String time) {
    slider.setMaximum(maximum);
    totalTimeLabel.setText(time);
  }

  @Override
  public void setCurrentPosition(int position, String time) {
    if (slider.getValueIsAdjusting()) {
      return;
    }
    slider.setValue(position);
    elapsedTimeLabel.setText(time);
  }

  @Override
  public void indicateError(Message message) {
    MessageUtilities.indicateMessage(MusicPlayerView.class, content, message);
  }

  @Override
  public void showPlay() {
    playButton.setAction(new RunnableAction(icons.getPlayButtonIcon(), playAction));
  }

  @Override
  public void showResume() {
    playButton.setAction(new RunnableAction(icons.getResumeButtonIcon(), resumeAction));
  }

  @Override
  public void showPause() {
    playButton.setAction(new RunnableAction(icons.getPauseButtonIcon(), pauseAction));
  }
}