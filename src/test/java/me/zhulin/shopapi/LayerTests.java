package me.zhulin.shopapi;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class LayerTests {

    JavaClasses importedClasses = new ClassFileImporter().importPackages("me.zhulin.shopapi");

    @Test
    public void layerFitnessTest () {

        ArchRule myRule = layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy("..api..")
                .layer("Service").definedBy("..service..")
                .layer("Repository").definedBy("..repository..")
                .layer("Domain").definedBy("..entity..")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
                .whereLayer("Domain").mayOnlyBeAccessedByLayers("Repository");

        //myRule.check(importedClasses);

    }


}
