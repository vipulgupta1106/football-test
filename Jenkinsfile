pipeline {
    agent any
    stages {
         stage('docker build'){
              agent any
              steps{
                sh 'docker build -t football/vipul-spring-boot-docker .'
              }
            }
    }
}