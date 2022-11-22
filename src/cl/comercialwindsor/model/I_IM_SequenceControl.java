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
package cl.comercialwindsor.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for IM_SequenceControl
 *  @author iDempiere (generated) 
 *  @version Release 8.2
 */
@SuppressWarnings("all")
public interface I_IM_SequenceControl 
{

    /** TableName=IM_SequenceControl */
    public static final String Table_Name = "IM_SequenceControl";

    /** AD_Table_ID=1000003 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Break */
    public static final String COLUMNNAME_Break = "Break";

	/** Set Break	  */
	public void setBreak (int Break);

	/** Get Break	  */
	public int getBreak();

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CriticalBreak */
    public static final String COLUMNNAME_CriticalBreak = "CriticalBreak";

	/** Set CriticalBreak	  */
	public void setCriticalBreak (int CriticalBreak);

	/** Get CriticalBreak	  */
	public int getCriticalBreak();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name FinalRange */
    public static final String COLUMNNAME_FinalRange = "FinalRange";

	/** Set FinalRange	  */
	public void setFinalRange (int FinalRange);

	/** Get FinalRange	  */
	public int getFinalRange();

    /** Column name IM_SequenceControl_ID */
    public static final String COLUMNNAME_IM_SequenceControl_ID = "IM_SequenceControl_ID";

	/** Set Sequence Control	  */
	public void setIM_SequenceControl_ID (int IM_SequenceControl_ID);

	/** Get Sequence Control	  */
	public int getIM_SequenceControl_ID();

    /** Column name IM_SequenceControl_UU 
    public static final String COLUMNNAME_IM_SequenceControl_UU = "IM_SequenceControl_UU";

	/** Set IM_SequenceControl_UU	  
	public void setIM_SequenceControl_UU (String IM_SequenceControl_UU);

	/** Get IM_SequenceControl_UU	  
	public String getIM_SequenceControl_UU();

    /** Column name InitialRange */
    public static final String COLUMNNAME_InitialRange = "InitialRange";

	/** Set InitialRange	  */
	public void setInitialRange (int InitialRange);

	/** Get InitialRange	  */
	public int getInitialRange();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name NotificationMessage */
    public static final String COLUMNNAME_NotificationMessage = "NotificationMessage";

	/** Set Notification Message	  */
	public void setNotificationMessage (String NotificationMessage);

	/** Get Notification Message	  */
	public String getNotificationMessage();

    /** Column name PreviousSequence */
    public static final String COLUMNNAME_PreviousSequence = "PreviousSequence";

	/** Set PreviousSequence	  */
	public void setPreviousSequence (String PreviousSequence);

	/** Get PreviousSequence	  */
	public String getPreviousSequence();

    /** Column name R_MailText_ID */
    public static final String COLUMNNAME_R_MailText_ID = "R_MailText_ID";

	/** Set Mail Template.
	  * Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID);

	/** Get Mail Template.
	  * Text templates for mailings
	  */
	public int getR_MailText_ID();

	public org.compiere.model.I_R_MailText getR_MailText() throws RuntimeException;

    /** Column name R_MailText2_ID */
    public static final String COLUMNNAME_R_MailText2_ID = "R_MailText2_ID";

	/** Set Mail Template 2.
	  * Text templates for mailings
	  */
	public void setR_MailText2_ID (int R_MailText2_ID);

	/** Get Mail Template 2.
	  * Text templates for mailings
	  */
	public int getR_MailText2_ID();

	public org.compiere.model.I_R_MailText getR_MailText2() throws RuntimeException;

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
