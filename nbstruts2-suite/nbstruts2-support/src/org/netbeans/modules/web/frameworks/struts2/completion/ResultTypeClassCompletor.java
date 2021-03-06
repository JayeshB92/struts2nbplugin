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
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.web.frameworks.struts2.completion;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;
import org.netbeans.api.java.source.ClassIndex;
import org.netbeans.api.java.source.ClassIndex.NameKind;
import org.netbeans.api.java.source.ClassIndex.SearchScope;
import org.netbeans.api.java.source.CompilationController;
import org.netbeans.api.java.source.ElementHandle;
import org.netbeans.modules.web.frameworks.struts2.completion.ui.LazyResultTypeItem;
import org.netbeans.modules.web.frameworks.struts2.completion.ui.LazyTypeCompletionItem;
import org.netbeans.modules.web.frameworks.struts2.completion.ui.StrutsXMLConfigCompletionItem;
import org.netbeans.modules.web.frameworks.struts2.utils.TypeScanner;

/**
 *
 * @author Rohan Ranade (Rohan.Ranade@Sun.COM)
 */
public class ResultTypeClassCompletor extends JavaClassCompletor {

    public ResultTypeClassCompletor() {
    }
    
    @Override
    protected void addNormalTypes(CompilationController cc, ClassIndex ci, 
            List<StrutsXMLConfigCompletionItem> results, String typedPrefix, 
            String packageName, PackageElement packageElem, int substitutionOffset) {
        TypeElement resultElem = cc.getElements().getTypeElement("com.opensymphony.xwork2.Result"); // NOI18N
        if(resultElem == null || !resultElem.getKind().isInterface()) {
            return;
        }

        Types types = cc.getTypes();
        List<TypeElement> tes = new TypeScanner(EnumSet.<ElementKind>of(ElementKind.CLASS)).scan(packageElem);
        for (TypeElement te : tes) {
            if (types.isAssignable(te.asType(), resultElem.asType()) && te.getQualifiedName().toString().startsWith(typedPrefix)) {
                StrutsXMLConfigCompletionItem item = StrutsXMLConfigCompletionItem.createTypeItem(substitutionOffset,
                        te, ElementHandle.create(te), cc.getElements().isDeprecated(te), false);
                results.add(item);
            }
        }
    }

    @Override
    protected void addSmartTypes(CompilationController cc, ClassIndex ci, 
            List<StrutsXMLConfigCompletionItem> results, String typedPrefix, 
            String packageName, int substitutionOffset) {
        TypeElement resultElem = cc.getElements().getTypeElement("com.opensymphony.xwork2.Result"); // NOI18N
        if(resultElem == null || !resultElem.getKind().isInterface()) {
            return;
        }
        ElementHandle<TypeElement> resultTypeHandle = ElementHandle.create(resultElem);
        Set<ElementHandle<TypeElement>> matchingTypes = ci.getDeclaredTypes(typedPrefix,
                NameKind.CASE_INSENSITIVE_PREFIX, EnumSet.<SearchScope>allOf(SearchScope.class));
        for (ElementHandle<TypeElement> eh : matchingTypes) {
            if (eh.getKind() == ElementKind.CLASS) {
                LazyTypeCompletionItem item = new LazyResultTypeItem(substitutionOffset, eh, cc.getJavaSource(), resultTypeHandle); // NOI18N
                results.add(item);
            }
        }
    }

}
