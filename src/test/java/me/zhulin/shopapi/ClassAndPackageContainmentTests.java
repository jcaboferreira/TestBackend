package me.zhulin.shopapi;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ClassAndPackageContainmentTests {

    JavaClasses importedClasses = new ClassFileImporter().importPackages("me.zhulin.shopapi");

    @Test
    public void classPackageControllers () {

        ArchRule myRule = classes()
                .that()
                .resideInAPackage("..api..")
                .and().haveSimpleNameNotContaining("Test")
                .should()
                .haveSimpleNameEndingWith("Controller");

        myRule.check(importedClasses);

    }

    @Test
    public void classPackageServices() {

        ArchRule myRule = classes()
                .that()
                .resideInAPackage("me.zhulin.shopapi.service")
                .and().haveSimpleNameNotContaining("Test")
                .should()
                .haveSimpleNameEndingWith("Service");

        myRule.check(importedClasses);

    }


    @Test
    public void classPackageServicesImpl() {

        ArchRule myRule = classes()
                .that()
                .resideInAPackage("me.zhulin.shopapi.service.impl")
                .and().haveSimpleNameNotContaining("Test")
                .should()
                .haveSimpleNameEndingWith("Impl");

        myRule.check(importedClasses);

    }

    @Test
    public void classPackageRepository() {

        ArchRule myRule = classes()
                .that()
                .resideInAPackage("me.zhulin.shopapi.repository")
                .and().haveSimpleNameNotContaining("Test")
                .should()
                .haveSimpleNameEndingWith("Repository");

        myRule.check(importedClasses);

    }

////////////////////////////////////////////////////////////////////////////Individual class tests/////////////////////////////////////////////////////////////////////

    @Test
    public void classProductControllerPackage() {

        ArchRule myRule = classes()
                .that()
                .haveSimpleName("ProductController")
                .and().haveSimpleNameNotContaining("Test")
                .should()
                .resideInAPackage("me.zhulin.shopapi.api");

        myRule.check(importedClasses);

    }

    @Test
    public void classProductServicePackage() {

        ArchRule myRule = classes()
                .that()
                .haveSimpleName("ProductService")
                .and().haveSimpleNameNotContaining("Test")
                .should()
                .resideInAPackage("me.zhulin.shopapi.service");

        myRule.check(importedClasses);

    }

    @Test
    public void classProductServiceImplPackage() {

        ArchRule myRule = classes()
                .that()
                .haveSimpleName("ProductServiceImpl")
                .and().haveSimpleNameNotContaining("Test")
                .should()
                .resideInAPackage("me.zhulin.shopapi.service.impl");

        myRule.check(importedClasses);

    }

    @Test
    public void classProductRepositoryPackage() {

        ArchRule myRule = classes()
                .that()
                .haveSimpleName("ProductInfoRepository")
                .and().haveSimpleNameNotContaining("Test")
                .should()
                .resideInAPackage("me.zhulin.shopapi.repository");

        myRule.check(importedClasses);

    }

    @Test
    public void classProductDomainPackage() {

        ArchRule myRule = classes()
                .that()
                .haveSimpleName("ProductInfo")
                .and().haveSimpleNameNotContaining("Test")
                .should()
                .resideInAPackage("me.zhulin.shopapi.entity");

        myRule.check(importedClasses);

    }


}
