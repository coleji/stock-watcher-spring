import org.jooq.meta.jaxb.*

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	war
	id("org.springframework.boot") version "3.5.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.jooq.jooq-codegen-gradle") version "3.20.1"
}

group = "com.coleji"
version = "0.0.1-SNAPSHOT"

java {
	project.tasks.build.get().dependsOn("jooqCodegen")
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
//	implementation("org.springframework.boot:spring-boot-starter-security")
// https://mvnrepository.com/artifact/commons-codec/commons-codec
	implementation("commons-codec:commons-codec:1.19.0")
	implementation("com.opencsv:opencsv:5.9")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.testcontainers:junit-jupiter")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
	implementation("com.mysql:mysql-connector-j:8.4.0")

	// jooq
	// https://mvnrepository.com/artifact/org.jooq/jooq
	implementation("org.jooq:jooq:3.20.5")
	implementation("org.jooq:jooq-meta:3.20.5")
	implementation("org.jooq:jooq-codegen:3.20.5")
	implementation("org.jooq:jooq-jackson-extensions:3.20.5")
	jooqCodegen("org.jooq:jooq-meta-extensions:3.20.5")


	// https://mvnrepository.com/artifact/org.testcontainers/testcontainers
	//testImplementation("org.testcontainers:testcontainers:1.20.5")
	// https://mvnrepository.com/artifact/org.testcontainers/mysql
	testImplementation("org.testcontainers:mysql:1.20.5")
	testImplementation(kotlin("test"))

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()

	minHeapSize = "1024m"
	maxHeapSize = "12288m"
}

tasks.processResources {
	expand(project.properties)
}

sourceSets {
	main {
		java {
			srcDir("${layout.buildDirectory.get()}/generated/main/java")
		}
	}
}

buildscript {
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("com.mysql:mysql-connector-j:8.4.0")
		classpath("org.jooq:jooq-codegen:3.20.1")
	}
}

jooq {
	configuration {
		generator {
			database {
				name = "org.jooq.meta.extensions.ddl.DDLDatabase"
				properties {
					property {
						key = "scripts"
						value = "src/main/resources/ddl.sql"
					}
				}
			}
			target {
				packageName = "org.jooq.generated"
				directory = "${layout.buildDirectory.get()}/generated/main/java"
			}
		}
	}
}