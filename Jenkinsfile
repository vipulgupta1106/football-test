pipeline {
    agent any
    tools{
        maven "3.6.0"
    }

    stages {
        stage ('Maven Build') {
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
                      steps{
                        sh 'docker build -t vipul/find-standings-project .'
                      }
              }
          stage('docker run'){
               agent any
               steps{
                 sh 'docker run webapp -d -p 8085:8085 springio/gs-spring-boot-docker'
               }
             }
    }
}