pipeline {
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
    }
    post{
        always{
            cleanWs()
            }
        }
}