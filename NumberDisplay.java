
/**
 * The NumberDisplay class represents a digital number display that can hold
 * values from zero to a given limit. The limit can be specified when creating
 * the display. The values range from zero (inclusive) to limit-1. If used,
 * for example, for the seconds on a digital clock, the limit would be 60, 
 * resulting in display values from 0 to 59. When incremented, the display 
 * automatically rolls over to zero when reaching the limit.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class NumberDisplay
{
    private int limit;
    private int value;
    private NumberDisplay theOtherDisplay;

    /**
     * Constructor for objects of class NumberDisplay.
     * Set the limit at which the display rolls over.
     */
    public NumberDisplay(int rollOverLimit)
    {
        limit = rollOverLimit;
        value = 0;
    }
    
    /**
     * Constructor for objects of class NumberDisplay.
     * Set the limit at which the display rolls over.
     * Pass an object of itself to it, so calls can be done between these 2
     */
    public NumberDisplay(int rollOverLimit, NumberDisplay theOtherDisplay)
    {
        limit = rollOverLimit;
        value = 0;
        this.theOtherDisplay = theOtherDisplay;
    }
    
    /**
     * Update the other NumberDisplay object, passed as reference
     * Call this when the first NumberDisplay (this) reached its limit
     */
    public void updateTheOtherNumberDisplay()
    {
        // I know this method is a little bit silly, as it is not really neccessary
        // but it makes the code more obvious, so I'm gonna keep it that way :))
        theOtherDisplay.increment();
    }

    /**
     * Return the current value.
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Return the display value (that is, the current value as a two-digit
     * String. If the value is less than ten, it will be padded with a leading
     * zero).
     */
    public String getDisplayValue()
    {
        if(value < 10) {
            return "0" + value;
        }
        else {
            return "" + value;
        }
    }

    /**
     * Set the value of the display to the new specified value. If the new
     * value is less than zero or over the limit, do nothing.
     */
    public void setValue(int replacementValue)
    {
        if((replacementValue >= 0) && (replacementValue < limit)) {
            value = replacementValue;
        }
    }

    /**
     * Increment the display value by one, rolling over to zero if the
     * limit is reached.
     */
    public void increment()
    {
        value = (value + 1) % limit;
        // the update has been done, verify whether this NumberDisplay has reached its limit
        // if that's the case, the value restarted at 0
        // check if the reference is valid, to know whether we should update the other NumberDisplay object
        if (value == 0 && theOtherDisplay != null)
        {
            // it actually is the case that this NumberDisplay reseted and there is a valid reference to be updated
            updateTheOtherNumberDisplay(); // all that is left is to update it
        }
    }
}
