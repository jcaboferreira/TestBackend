package me.zhulin.shopapi;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.Entity;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class UserArchitectureTests {

    JavaClasses importedClasses = new ClassFileImporter().importPackages("me.zhulin.shopapi");

    @Test
    public void annotationServiceTest() {
        ArchRule rule = classes()
                .that().resideInAPackage("..impl..")
                .and().haveSimpleNameEndingWith("ServiceImpl")
                .should().beAnnotatedWith(Service.class);

        rule.check(importedClasses);
    }

    @Test
    public void annotationControllerTest() {
        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .and().haveSimpleNameEndingWith("Controller")
                .should().beAnnotatedWith(RestController.class);

        rule.check(importedClasses);
    }

    @Test
    public void annotationEntityTest() {
        ArchRule rule = classes()
                .that().resideInAPackage("..entity..")
                .should().beAnnotatedWith(Entity.class);

        rule.check(importedClasses);
    }

    @Test
    public void deprecatedClassesUserService() {
        ArchRule rule = classes()
                .that().resideInAPackage("..impl..")
                .and().haveSimpleNameEndingWith("UserServiceImpl")
                .should().dependOnClassesThat()
                .areNotAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }

    @Test
    public void deprecatedClassesUserController() {
        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .and().haveSimpleNameEndingWith("UserController")
                .should().dependOnClassesThat()
                .areNotAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }

    @Test
    public void deprecatedClassesUserEntity() {
        ArchRule rule = classes()
                .that().resideInAPackage("..entity..")
                .and().haveSimpleNameEndingWith("User")
                .should().dependOnClassesThat()
                .areNotAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }


}