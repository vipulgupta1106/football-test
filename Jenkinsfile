pipeline {
    agent any
    tools{
        maven "3.6.0"
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