package com.ssepan.application.mvvm;

import javax.swing.*;
import com.ssepan.utility.*;
import java.util.logging.*;

/**
 *
 * @author ssepan
 */
public  class ViewModelBase
    //extends JComponent
{
// <editor-fold defaultstate="collapsed" desc="Declarations">
// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="Constructors">
// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="INotifyPropertyChanged">
// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="Properties">
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Methods">


    /**
     * Use for Marquee-style progress bar, or when percentages/count must be indicated.
     *
        @param statusMessage
        @param errorMessage
        @param isMarqueeProgressBarStyle
        @param isCountProgressbar
        @param iProgressBarValue
        @param iProgressBarMax
        @param ctlStatusMessage
        @param ctlErrorMessage
        @param ctlProgressBar
        @param ctlActionIcon
        @param objImage
     */
    public static void StartProgressBar(
        String statusMessage,
        String errorMessage,
        Boolean isMarqueeProgressBarStyle, 
        Boolean isCountProgressbar,
        Integer iProgressBarValue, 
        Integer iProgressBarMax,
        JLabel ctlStatusMessage, 
        JLabel ctlErrorMessage,
        JProgressBar ctlProgressBar,
        JButton ctlActionIcon,
        javax.swing.Icon objImage
    )
    {
        try
        {
            ctlProgressBar.setVisible(true);
            ctlProgressBar.setEnabled(true);

            if (isMarqueeProgressBarStyle) {
                 //marquee
                 ctlProgressBar.setIndeterminate(true);
                 ctlProgressBar.setMaximum(100);
                //ctlProgressBar.setStep(1);
                 ctlProgressBar.setValue(33);//one third
            }
            else {
                 //progress
                 //if Style is not Marquee, then we are marking either a count or percentage
                 ctlProgressBar.setIndeterminate(false);
                 
                 if (isCountProgressbar) {
                      //count
                      //set to smooth if count is used.
                      //ctlProgressBar.setSmooth(true);
                      ctlProgressBar.setMaximum(iProgressBarMax);

                 }
                 else {
                      //percentage
                      //set to blocks if actual percentage is used.
                      //ctlProgressBar.setSmooth(false);
                      ctlProgressBar.setMaximum(100);

                 }

                 //set to value if percentage or count used.
//                 ctlProgressBar.setStep(1);
                 ctlProgressBar.setValue(iProgressBarValue);
             }

             if (statusMessage==null || statusMessage.trim().isEmpty()) {
                 ctlStatusMessage.setText(""); 
             }
             else {
                 ctlStatusMessage.setText(statusMessage);
             }

             if (errorMessage==null) {
                 ctlErrorMessage.setText("");
             }
             else {
                 ctlErrorMessage.setText(errorMessage);
             }

            ctlErrorMessage.setToolTipText(ctlErrorMessage.getText());

            if (objImage != null) {
                ctlActionIcon.setIcon(objImage);
             
                ctlActionIcon.setToolTipText(statusMessage);
                ctlActionIcon.setVisible(true);
            }
        }
        catch (RuntimeException ex)
        {
            Log.write(ex, Level.WARNING);
            throw ex;
        }
        finally {
            //give the app time to draw the eye-candy, even if its only for an instant
          Thread.yield();
          //Common.DoEvents();
        }
    }
    

    /**
     * Update percentage changes.
     *
    @param sStatusMessage String. If Null, do nothing, otherwise update.</param>
    @param isMarqueeProgressBarStyle Boolean
    @param isCountProgressbar Boolean
    @param iProgressBarValue LongInt. UpdateProgressBar does not manage the value, other than checking that it is within the range, and adjusting the Max for counting mode.
    @param ctlStatusMessage">TLabel
    @param ctlProgressBar">TProgressBar
     */
    public static void UpdateProgressBar
    (
        String statusMessage,
        Boolean isMarqueeProgressBarStyle, 
        Boolean isCountProgressbar,
        Integer iProgressBarValue, 
        JLabel ctlStatusMessage, 
        JProgressBar ctlProgressBar
    )
    {
        try
        {
            if (statusMessage==null) {
                /*do nothing*/ 
            }
              else { 
                ctlStatusMessage.setText(statusMessage);
            }
        

            if (isMarqueeProgressBarStyle) {
                  //do nothing with progressbar
            }
            else {
                //update count or percentage
                 //if Style is not Marquee, then we are marking either a count or percentage
                 //if we are simply counting, the progress bar will periodically need to adjust the Maximum.
                 if (isCountProgressbar) {
                    if (iProgressBarValue > ctlProgressBar.getMaximum()) {
                         ctlProgressBar.setMaximum(iProgressBarValue * 2);
                    }
                }

                  ctlProgressBar.setValue(iProgressBarValue);
            }
        }
        catch (RuntimeException ex)
        {
            Log.write(ex, Level.WARNING);
            throw ex;
        }
        finally {

          //give the app time to draw the eye-candy, even if its only for an instant
          Thread.yield();
          //Common.DoEvents();
        }
    }

    /**
     * Update message(s) only, without changing progress bar.
        Null parameter will leave a message unchanged; "" will clear it.
    @param sStatusMessage String
    @param serrorMessage String
    @param ctlStatusMessage JLabel
    @param ctlErrorMessage JLabel
     */
    public static void UpdateStatusBarMessages
    (
        String statusMessage,
        String errorMessage,
        JLabel ctlStatusMessage, 
        JLabel ctlErrorMessage
    )
    {
        try {
            if (statusMessage==null) {
                /*do nothing*/ 
            }
             else { 
                 ctlStatusMessage.setText(statusMessage);
            }

            if (errorMessage==null) {
                /*do nothing*/ 
            }
             else { 
                ctlErrorMessage.setText(errorMessage);
            }

            ctlErrorMessage.setToolTipText(ctlErrorMessage.getText());
        }
        catch (RuntimeException ex) {
            Log.write(ex, Level.WARNING);
            throw ex;
        }
        finally {
          //give the app time to draw the eye-candy, even if its only for an instant
          Thread.yield();
          //Common.DoEvents();
        }
    }


    /** 
    // Stop progress bar and display messages.
    // Application.ProcessMessages will ensure messages are processed before continuing.
    @param statusMessage
    @param errorMessage Null parameter will leave a message unchanged; String.Empty will clear it.
    @param ctlStatusMessage
    @param ctlErrorMessage
    @param ctlProgressBar
    @param ctlActionIcon
    */
    public static void StopProgressBar(
            String statusMessage, 
            String errorMessage,
            JLabel ctlStatusMessage, 
            JLabel ctlErrorMessage,
            JProgressBar ctlProgressBar,
            JButton ctlActionIcon
    )
    {
        try
        {
            if (statusMessage==null) {
                ctlStatusMessage.setText("");
            }
            else { 
                ctlStatusMessage.setText(statusMessage);
            }

            //do not clear error at end of operation, clear it at start of operation
            if (errorMessage==null) {
                /*do nothing*/ 
            }
            else { 
              ctlErrorMessage.setText(errorMessage);
            }
            
          //sync
          ctlErrorMessage.setToolTipText(ctlErrorMessage.getText());

          ctlProgressBar.setEnabled(false);
          ctlProgressBar.setVisible(false);

          ctlActionIcon.setVisible(false);
        }
        catch (RuntimeException ex) {
            Log.write(ex, Level.WARNING);
            throw ex;
        }
        finally {
           ctlProgressBar.setEnabled(false);
           ctlProgressBar.setVisible(false);

          //give the app time to draw the eye-candy, even if its only for an instant
          Thread.yield();
          //Common.DoEvents();
        }
    }
// </editor-fold>


}