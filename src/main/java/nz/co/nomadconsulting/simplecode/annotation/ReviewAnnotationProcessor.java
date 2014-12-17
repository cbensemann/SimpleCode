/*
* Copyright 2014 Nomad Consulting Limited
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package nz.co.nomadconsulting.simplecode.annotation;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;


@SupportedAnnotationTypes({"nz.co.nomadconsulting.simplecode.annotation.Awesome", "nz.co.nomadconsulting.simplecode.annotation.Temporary", "nz.co.nomadconsulting.simplecode.annotation.DeprecatedReview"})
public class ReviewAnnotationProcessor extends AbstractProcessor {

    private static final String FORMAT = "dd-MM-yyyy";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Awesome.class)) { 
            final Awesome annotation = element.getAnnotation(Awesome.class);
            final String value = annotation.value();
            checkValue(element, value, "Awesome");
        }
        for (Element element : roundEnv.getElementsAnnotatedWith(Temporary.class)) { 
            final Temporary annotation = element.getAnnotation(Temporary.class);
            final String value = annotation.value();
            checkValue(element, value, "Temporary");
        }
        for (Element element : roundEnv.getElementsAnnotatedWith(DeprecatedReview.class)) { 
            final DeprecatedReview annotation = element.getAnnotation(DeprecatedReview.class);
            final String value = annotation.value();
            checkValue(element, value, "DeprecatedReview");
        }
        return true;
    }

    
    private void checkValue(Element element, final String value, final String type) {
        try {
            if (valueExpired(value)) {
                processingEnv.getMessager().printMessage(Kind.ERROR, type + " code past expiry " + value + " please review!", element);
            }
        }
        catch (ParseException e) {
            processingEnv.getMessager().printMessage(Kind.ERROR, "date format unparsable: should be " + FORMAT, element);
        }
    }

    
    private boolean valueExpired(String value) throws ParseException {
        DateFormat format = new SimpleDateFormat(FORMAT);
        final Date parsedDate = format.parse(value);
        return new Date().after(parsedDate);
    }
}
