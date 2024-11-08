package me.zhulin.shopapi;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class CartArchitectureTests {

    JavaClasses importedClasses = new ClassFileImporter().importPackages("me.zhulin.shopapi");

    @Test
    void deprecatedClassesCartService() {
        ArchRule rule = classes()
                .that().resideInAPackage("..impl..")
                .and().haveSimpleNameEndingWith("CartServiceImpl")
                .should().dependOnClassesThat()
                .areNotAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }

    @Test
    void deprecatedClassesCartController() {
        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .and().haveSimpleNameEndingWith("CartController")
                .should().dependOnClassesThat()
                .areNotAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }

    @Test
    void deprecatedClassesCartEntity() {
        ArchRule rule = classes()
                .that().resideInAPackage("..entity..")
                .and().haveSimpleNameEndingWith("Cart")
                .should().dependOnClassesThat()
                .areNotAnnotatedWith(Deprecated.class);

        rule.check(importedClasses);
    }

    @Test
    void cycleDependenciesControllerShouldBeFreeOfCycles() {
        SlicesRuleDefinition.slices()
                .matching("..api..")
                .should()
                .beFreeOfCycles()
                .allowEmptyShould(true)
                .check(importedClasses);
    }


    @Test
    void cycleDependenciesServiceShouldBeFreeOfCycles() {
        SlicesRuleDefinition.slices()
                .matching("..service..")
                .should()
                .beFreeOfCycles()
                .allowEmptyShould(true)
                .check(importedClasses);
    }

    @Test
    void cycleDependenciesEntityShouldBeFreeOfCycles() {
        SlicesRuleDefinition.slices()
                .matching("..entity..")
                .should()
                .beFreeOfCycles()
                .allowEmptyShould(true)
                .check(importedClasses);
    }

    @Test
    void cycleDependenciesRepositoryShouldBeFreeOfCycles() {
        SlicesRuleDefinition.slices()
                .matching("..repository..")
                .should()
                .beFreeOfCycles()
                .allowEmptyShould(true)
                .check(importedClasses);
    }
}