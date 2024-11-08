package me.zhulin.shopapi;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class OrderArchitectureTests {

    JavaClasses importedClasses = new ClassFileImporter().importPackages("me.zhulin.shopapi");




}
