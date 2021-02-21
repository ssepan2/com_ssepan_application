package com.ssepan.application;

import java.beans.*;
import java.io.*;
import javax.swing.JComponent;

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
    // </editor-fold>
    
    
    final String KEY_NEW = "(new)";
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public ModelBase()
    {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        //this.vetoableChangeSupport = new VetoableChangeSupport(this);
        
        Dirty = false;
        Key = KEY_NEW;

    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Properties">
    //Note: "Default serialization will not serialize 'transient' and 'static' fields."

    private String KeyOld;
    private String Key;
    public String getKey()
    {
        return Key;
    }
    public void setKey(String value)// throws PropertyVetoException
    {
        KeyOld = Key;
        //vetoableChangeSupport.fireVetoableChange("Key", KeyOld, value);
        Key = value;
        propertyChangeSupport.firePropertyChange("Key", Key, value);
        setDirty(true);
    }

    private Boolean DirtyOld;
    private Boolean Dirty;
    public Boolean isDirty()
    {
        return Dirty;
    }
    public void setDirty(Boolean value)// throws PropertyVetoException
    {
        DirtyOld = Dirty;
        //vetoableChangeSupport.fireVetoableChange("Dirty", DirtyOld, value);
        Dirty = value;
        propertyChangeSupport.firePropertyChange("Dirty", Dirty, value);
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
