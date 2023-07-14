pipeline {
	agent any

	environment {
		mavenHome = tool 'mvn'
	}

	tools {
		jdk 'jdk-17'
	}

	stages {

		stage('BUILD'){
			steps {
				bat "mvn clean install -DskipTests"
			}
		}
		stage('RUN') {
			steps {
			    bat "mvn spring-boot:run"
			}
		}
	}
}