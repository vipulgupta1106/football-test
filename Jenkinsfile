pipeline {
    environment {
        JAVA_TOOL_OPTIONS = "-Duser.home = /var/maven"
    }
    agent {
     docker{
     image "maven:3.6-jdk-8"
     label "master"
     }
    }
    stages {
        stage ('Build') {
            steps {
                sh 'mvn --version'
                sh 'mvn clean install'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
        stage('docker build'){
                      agent any
                      steps{
                        sh 'docker build -t football/vipul-spring-boot-docker .'
                      }
                    }
    }
    post{
        always{
            cleanWs()
            }
        }
}