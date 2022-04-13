import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.EntityValidator;

public class EntityValidatorTest {

    @DisplayName("Entity의 Id어노테이션 필드의 존재여부 검증")
    @Test
    void readEntityAnnotationClassName() throws ClassNotFoundException {
        Assertions.assertThatCode(() -> EntityValidator.checkRequired("entity.Member")).doesNotThrowAnyException();
    }

}
