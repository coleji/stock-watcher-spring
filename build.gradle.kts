plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	war
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
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
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
	implementation("com.mysql:mysql-connector-j:8.4.0")

	// jooq
	// https://mvnrepository.com/artifact/org.jooq/jooq
	implementation("org.jooq:jooq:3.20.1")
	implementation("org.jooq:jooq-meta:3.20.1")
	implementation("org.jooq:jooq-codegen:3.20.1")

	// https://mvnrepository.com/artifact/org.testcontainers/testcontainers
	testImplementation("org.testcontainers:testcontainers:1.20.5")
	// https://mvnrepository.com/artifact/org.testcontainers/mysql
	testImplementation("org.testcontainers:mysql:1.20.5")


}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
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

tasks.register("jooqCodegen") {
	doLast {
//		val schemaPath = layout.projectDirectory.file("/src/main/resources/schema.sql").asFile.path

		org.jooq.meta.jaxb.Configuration()
			.withJdbc(
				org.jooq.meta.jaxb.Jdbc()
					.withDriver(project.property("spring.datasource.driver-class-name") as String)
					.withUrl(project.property("spring.datasource.url") as String)
					.withUser(project.property("spring.datasource.username") as String)
					.withPassword(project.property("spring.datasource.password") as String)
			)
			.withGenerator(
				org.jooq.meta.jaxb.Generator()
					.withDatabase(org.jooq.meta.jaxb.Database().withInputSchema("finance"))
					.withTarget(
						org.jooq.meta.jaxb.Target()
							.withPackageName("org.jooq.generated")
							.withDirectory("${layout.buildDirectory.get()}/generated/main/java")
					)
			).also(org.jooq.codegen.GenerationTool::generate)
	}
}