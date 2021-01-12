pipeline {
    agent any
    stages {
        stage ('Build') {
            steps {
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
}