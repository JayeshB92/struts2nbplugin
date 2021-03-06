/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is XWork Support. The Initial Developer of the Original
 * Software is 
 * Aleh Maksimovich <aleh.maksimovich@gmail.com>, 
 *                  <aleh.maksimovich@hiqo-solutions.com>.
 * Portions Copyright 2011 Aleh Maksimovich. All Rights Reserved.
 */
package org.netbeans.modules.framework.xwork;

/**
 * Enumeration of all document MIME types supported by the plug-in. These values
 * will be used to detect document types inside plug-in. MIME type values are
 * defined as string constants since only constants could be used as property
 * values inside annotations.
 *
 * @author Aleh Maksimovich
 * @since 0.5.1
 */
public final class XWorkMimeType {

    /**
     * XWork Validator Config XML document.
     */
    public static final String VALIDATOR_CONFIG_XML_MIME = "text/x-xwork-validator-config+xml";
    /**
     * XWork Validator XML document.
     */
    public static final String VALIDATOR_XML_MIME = "text/x-xwork-validator+xml";
    /**
     * Java source code document.
     */
    public static final String JAVA_SOURCE_MIME = "text/x-java";

    private XWorkMimeType() {
    }
}
