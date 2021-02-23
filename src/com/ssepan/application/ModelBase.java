package com.ssepan.application;

import java.beans.*;
import java.io.*;
import java.util.logging.Level;
import javax.swing.JComponent;
import com.ssepan.utility.*;

/**
 *
 * @author ssepan
 */
public class ModelBase
     extends JComponent
{
    // <editor-fold defaultstate="collapsed" desc="Declarations">
    protected /*final*/ PropertyChangeSupport propertyChangeSupport;
    //protected /*final*/ VetoableChangeSupport vetoableChangeSupport;
    
    
    public final String KEY_NEW = "(new)";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public ModelBase()
    {
        String sStatusMessage="";
        String sErrorMessage="";

        try {
            this.propertyChangeSupport = new PropertyChangeSupport(this);
            //this.vetoableChangeSupport = new VetoableChangeSupport(this);

            //Key = KEY_NEW;
            //Dirty = false;
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Properties">
    //Note: "Default serialization will not serialize 'transient' and 'static' fields."

    private String KeyOld = "";//KEY_NEW;
    private String Key = "";//KEY_NEW;
    public String getKey()
    {
        String sStatusMessage="";
        String sErrorMessage="";
        String returnValue="";

        try {
            returnValue=Key;
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this

        }
        return returnValue;
    }
    public void setKey(String value)// throws PropertyVetoException
    {
        String sStatusMessage="";
        String sErrorMessage="";

        try {
            KeyOld = Key;
            //vetoableChangeSupport.fireVetoableChange("Key", KeyOld, value);
            Key = value;
            propertyChangeSupport.firePropertyChange("Key", KeyOld, Key);
            setDirty(true);
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this

        }
    }

    private Boolean DirtyOld = false;
    private Boolean Dirty = false;
    public Boolean isDirty()
    {
        String sStatusMessage="";
        String sErrorMessage="";
        Boolean returnValue=false;

        try {
            returnValue=Dirty;
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this

        }
        return returnValue;
    }
    public void setDirty(Boolean value)// throws PropertyVetoException
    {
        String sStatusMessage="";
        String sErrorMessage="";

        try {
            DirtyOld = Dirty;
            //vetoableChangeSupport.fireVetoableChange("Dirty", DirtyOld, value);
            Dirty = value;
            System.out.println(String.format("setDirty %s '%s' '%s'","Dirty", DirtyOld, Dirty));
            propertyChangeSupport.firePropertyChange("Dirty", DirtyOld, Dirty);
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this

        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="PropertyChangeSupport">
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) 
    {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) 
    {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) 
    {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) 
    {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="VetoableChangeSupport">
//    @Override
//    public void addVetoableChangeListener(VetoableChangeListener listener) 
//    {
//        vetoableChangeSupport.addVetoableChangeListener(listener);
//    }
//    
//    @Override
//    public void removeVetoableChangeListener(VetoableChangeListener listener) 
//    {
//        vetoableChangeSupport.removeVetoableChangeListener(listener);
//    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="XML IO">
    public static void Write
    (
        String filepath,
        ModelBase model
    ) 
        throws FileNotFoundException
    {
        try 
        (
            XMLEncoder encoder = new XMLEncoder
            (
                new BufferedOutputStream
                (
                    new FileOutputStream(filepath)//"Beanarchive.xml"
                )
            )
        )
        { 
            encoder.writeObject(model);
        }
    }
    
    public static ModelBase Read
    (
        String filepath
    ) 
        throws FileNotFoundException
    {
        ModelBase returnValue;
        try 
        (
            XMLDecoder decoder = new XMLDecoder
            (
                new BufferedInputStream
                (
                    new FileInputStream(filepath)//"Beanarchive.xml"
                )
            )
        )
        {        
            returnValue = (ModelBase)decoder.readObject();
        }
        
        return returnValue;
    }
//    // </editor-fold>
}
