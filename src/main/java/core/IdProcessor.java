package core;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.lang.reflect.Field;
import java.util.Set;

public class IdProcessor extends AbstractProcessor {
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Id.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> rootElements = roundEnv.getRootElements();
        annotations.forEach(e -> {
            Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(e);

        });
        Class c = Class.forName(Id.class.getCanonicalName());
        Field[] fields = c.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Id.class)) {
                throw new RuntimeException("Id Annotaion Required!");
            }
        }
        return false;
    }
}
