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


@SupportedAnnotationTypes("org.adscale.adserver.test.annotation.Awesome")
public class ReviewAnnotationProcessor extends AbstractProcessor {

    private static final String FORMAT = "dd-MM-yyyy";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Awesome.class)) {
            final Awesome annotation = element.getAnnotation(Awesome.class);
            try {
                if (valueExpired(annotation.value())) {
                    processingEnv.getMessager().printMessage(Kind.ERROR, "Awesome code past expiry " + annotation.value() + " please review!", element);
                }
            }
            catch (ParseException e) {
                processingEnv.getMessager().printMessage(Kind.ERROR, "date format unparsable: should be " + FORMAT, element);
            }
        }
        return true;
    }

    private boolean valueExpired(String value) throws ParseException {
        DateFormat format = new SimpleDateFormat(FORMAT);
        final Date parsedDate = format.parse(value);
        return new Date().after(parsedDate);
    }
}
