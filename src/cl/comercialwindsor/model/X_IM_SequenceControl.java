/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package cl.comercialwindsor.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for IM_SequenceControl
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_IM_SequenceControl extends PO implements I_IM_SequenceControl, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20221017L;

    /** Standard Constructor */
    public X_IM_SequenceControl (Properties ctx, int IM_SequenceControl_ID, String trxName)
    {
      super (ctx, IM_SequenceControl_ID, trxName);
      /** if (IM_SequenceControl_ID == 0)
        {
			setBreak (0);
			setC_DocType_ID (0);
			setCriticalBreak (0);
			setEMail (null);
			setIM_SequenceControl_ID (0);
			setR_MailText_ID (0);
			setR_MailText2_ID (0);
        } */
    }

    /** Load Constructor */
    public X_IM_SequenceControl (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_IM_SequenceControl[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Break.
		@param Break Break	  */
	public void setBreak (int Break)
	{
		set_Value (COLUMNNAME_Break, Integer.valueOf(Break));
	}

	/** Get Break.
		@return Break	  */
	public int getBreak () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Break);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CriticalBreak.
		@param CriticalBreak CriticalBreak	  */
	public void setCriticalBreak (int CriticalBreak)
	{
		set_Value (COLUMNNAME_CriticalBreak, Integer.valueOf(CriticalBreak));
	}

	/** Get CriticalBreak.
		@return CriticalBreak	  */
	public int getCriticalBreak () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CriticalBreak);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set FinalRange.
		@param FinalRange FinalRange	  */
	public void setFinalRange (int FinalRange)
	{
		set_Value (COLUMNNAME_FinalRange, Integer.valueOf(FinalRange));
	}

	/** Get FinalRange.
		@return FinalRange	  */
	public int getFinalRange () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FinalRange);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sequence Control.
		@param IM_SequenceControl_ID Sequence Control	  */
	public void setIM_SequenceControl_ID (int IM_SequenceControl_ID)
	{
		if (IM_SequenceControl_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_IM_SequenceControl_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_IM_SequenceControl_ID, Integer.valueOf(IM_SequenceControl_ID));
	}

	/** Get Sequence Control.
		@return Sequence Control	  */
	public int getIM_SequenceControl_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IM_SequenceControl_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set IM_SequenceControl_UU.
		@param IM_SequenceControl_UU IM_SequenceControl_UU	  
	public void setIM_SequenceControl_UU (String IM_SequenceControl_UU)
	{
		set_Value (COLUMNNAME_IM_SequenceControl_UU, IM_SequenceControl_UU);
	}

	/** Get IM_SequenceControl_UU.
		@return IM_SequenceControl_UU	  
	public String getIM_SequenceControl_UU () 
	{
		return (String)get_Value(COLUMNNAME_IM_SequenceControl_UU);
	}

	/** Set InitialRange.
		@param InitialRange InitialRange	  */
	public void setInitialRange (int InitialRange)
	{
		set_Value (COLUMNNAME_InitialRange, Integer.valueOf(InitialRange));
	}

	/** Get InitialRange.
		@return InitialRange	  */
	public int getInitialRange () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_InitialRange);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}


	/** Critical = C */
	public static final String NOTIFICATIONMESSAGE_Critical = "C";
	/** First = F */
	public static final String NOTIFICATIONMESSAGE_First = "F";
	/** None = N */
	public static final String NOTIFICATIONMESSAGE_None = "N";
	/** Set Notification Message.
		@param NotificationMessage Notification Message	  */
	public void setNotificationMessage (String NotificationMessage)
	{

		set_Value (COLUMNNAME_NotificationMessage, NotificationMessage);
	}

	/** Get Notification Message.
		@return Notification Message	  */
	public String getNotificationMessage () 
	{
		return (String)get_Value(COLUMNNAME_NotificationMessage);
	}

	/** Set PreviousSequence.
		@param PreviousSequence PreviousSequence	  */
	public void setPreviousSequence (String PreviousSequence)
	{
		set_Value (COLUMNNAME_PreviousSequence, PreviousSequence);
	}

	/** Get PreviousSequence.
		@return PreviousSequence	  */
	public String getPreviousSequence () 
	{
		return (String)get_Value(COLUMNNAME_PreviousSequence);
	}

	public org.compiere.model.I_R_MailText getR_MailText() throws RuntimeException
    {
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_Name)
			.getPO(getR_MailText_ID(), get_TrxName());	}

	/** Set Mail Template.
		@param R_MailText_ID 
		Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID)
	{
		if (R_MailText_ID < 1) 
			set_Value (COLUMNNAME_R_MailText_ID, null);
		else 
			set_Value (COLUMNNAME_R_MailText_ID, Integer.valueOf(R_MailText_ID));
	}

	/** Get Mail Template.
		@return Text templates for mailings
	  */
	public int getR_MailText_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_MailText_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_MailText getR_MailText2() throws RuntimeException
    {
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_Name)
			.getPO(getR_MailText2_ID(), get_TrxName());	}

	/** Set Mail Template 2.
		@param R_MailText2_ID 
		Text templates for mailings
	  */
	public void setR_MailText2_ID (int R_MailText2_ID)
	{
		if (R_MailText2_ID < 1) 
			set_Value (COLUMNNAME_R_MailText2_ID, null);
		else 
			set_Value (COLUMNNAME_R_MailText2_ID, Integer.valueOf(R_MailText2_ID));
	}

	/** Get Mail Template 2.
		@return Text templates for mailings
	  */
	public int getR_MailText2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_MailText2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}