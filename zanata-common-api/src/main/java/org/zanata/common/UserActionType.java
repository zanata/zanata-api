package org.zanata.common;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "userActionType")
public enum UserActionType
{
   LAST_TRANSLATED, UPLOAD_DOC, REVIEWED_TRANSLATION;
}