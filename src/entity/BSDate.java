package entity;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
//This represents an instance of BSDate. BSDate is used to store the date that the user first joined BattleStations

public class BSDate {
    //Attribute of BSDate
    private Date date;

    /**
    *Default constructor for BSDate class
    *BSDate object is set to current date and time
    */
    public BSDate() {
        date = new Date();
    }

    /**
    *Specific constructor for BSDate class that creates a BSDate object by converting date represented in String format
    *@param strDate date representation in "dd MMM yyyy")
    *@throws ParseException when the format is not valid (valid format : "dd MMM yyyy").
     */
    public BSDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        date = sdf.parse(strDate);
    }

    /**
    *Get the Date object representation of BSDate
    *@return Date object representation of BSDate
    */
    private Date getDate() {
        return date;
    }

    /**
    *Comparing if this BSDate object is equal to another BSDate object
    *@param anotherBSDate another BSDate object for comparison
    *@return boolean
    */
    public boolean equals(BSDate anotherBSDate) {
        return this.date.equals(anotherBSDate.getDate());
    }

    /**
    *Comparing if this BSDate object occurs at a later date than another BSDate object
    *@param anotherBSDate another BSDate object for comparison
    *@return boolean
    */
    public boolean after(BSDate anotherBSDate) {
        return this.date.after(anotherBSDate.getDate());
    }

    /**
    *Comparing if this BSDate object occurs at an earlier date than another BSDate object
    *@param anotherBSDate another BSDate object for comparison
    *@return boolean
    */
    public boolean before(BSDate anotherBSDate) {
        return this.date.before(anotherBSDate.getDate());
    }

    /**
    *Check whether a BSDate object occurs on the date as the current date
    *@param thisBSDate
    *@return boolean
    */
    public boolean sameDate(BSDate thisBSDate) {
        //Creation of the current date
        GregorianCalendar currentDate = new GregorianCalendar();

        //Change thisBSDate to GregorianCalendar object
        GregorianCalendar thisDate = new GregorianCalendar();
        thisDate.setTime(thisBSDate.getDate());

        return (thisDate.get(Calendar.DAY_OF_MONTH) == currentDate.get(Calendar.DAY_OF_MONTH)) 
            && (thisDate.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH))
            && (thisDate.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR));
    }

    /**
    *Returns the textual representation of a BSDate object in "dd MMM yyyy" format
    *@return the textual representation of BSDate object
    */
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return sdf.format(date);
    }
}