import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.0"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    // MyBatis Generatorプラグイン
    id ("com.thinkimi.gradle.MybatisGenerator") version "2.1.2"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
}

group = "com.raishin"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    mybatisGenerator
}

mybatisGenerator {
    verbose = true
    // 設定ファイル
    configFile = "src/main/resources/generatorConfig.xml"

    dependencies {
        mybatisGenerator ("com.itfsw:mybatis-generator-plugin:1.3.7")
        mybatisGenerator ("org.mybatis.generator:mybatis-generator-core:1.3.7")
        mybatisGenerator ("org.mariadb.jdbc:mariadb-java-client:2.4.4")
    }
}
repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4")
    implementation("org.mariadb.jdbc:mariadb-java-client:2.4.4")
    implementation("com.opencsv:opencsv:5.2")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.batch:spring-batch-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
