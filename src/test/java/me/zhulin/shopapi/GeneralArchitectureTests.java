package me.zhulin.shopapi;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.thirdparty.com.google.common.util.concurrent.AbstractService;
import me.zhulin.shopapi.service.*;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class GeneralArchitectureTests {

    JavaClasses importedClasses = new ClassFileImporter().importPackages("me.zhulin.shopapi");

    //Package Dependency
    /**
     * Ensures that classes in the 'service' package don't depend on the 'controller' package
     */
    @Test
    public void servicesShouldNotDependOnControllers() {
        noClasses()
                .that()
                .resideInAPackage("..service..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..api..")
                .check(importedClasses);
    }

    /**
     * Test to ensure controllers only depend on services and models
     */
    @Test
    void controllersShouldOnlyDependOnServicesAndModels() {
        classes()
                .that().resideInAPackage("..api..")
                .should().onlyHaveDependentClassesThat()
                .resideInAnyPackage("..service..", "..entity..", "..api..")
                .check(importedClasses);
    }

    /**
     * Ensures that classes in the 'controller' package don't directly depend on the 'repository' package
     */
    @Test
    void controllersShouldNotDependOnRepositories() {
        ArchRuleDefinition.noClasses()
                .that().resideInAPackage("..api..")
                .should().dependOnClassesThat().resideInAPackage("..repository..")
                .check(importedClasses);
    }

    //Inheritance
    @Test
    public void orderServiceImplShouldImplementOrderService() {
        classes()
                .that().haveSimpleName("OrderServiceImpl") // Target OrderServiceImpl class
                .should().beAssignableTo(OrderService.class) // Check that it implements OrderService
                .check(importedClasses);
    }

    @Test
    public void cartServiceImplShouldImplementCartService() {
        classes()
                .that().haveSimpleName("CartServiceImpl")
                .should().beAssignableTo(CartService.class)
                .check(importedClasses);
    }

    @Test
    public void categoryServiceImplShouldImplementCategoryService() {
        classes()
                .that().haveSimpleName("CategoryServiceImpl")
                .should().beAssignableTo(CategoryService.class)
                .check(importedClasses);
    }

    @Test
    public void userServiceImplShouldImplementUserService() {
        classes()
                .that().haveSimpleName("UserServiceImpl")
                .should().beAssignableTo(UserService.class)
                .check(importedClasses);
    }

    @Test
    public void productServiceImplShouldImplementProductService() {
        classes()
                .that().haveSimpleName("ProductServiceImpl")
                .should().beAssignableTo(ProductService.class)
                .check(importedClasses);
    }

    @Test
    public void cartShouldImplementSerializable() {
        classes()
                .that().haveSimpleName("Cart")
                .should().beAssignableTo(Serializable.class)
                .check(importedClasses);
    }

}
