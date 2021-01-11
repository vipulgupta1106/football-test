pipeline{
  agent none
  stages{
   stage('who'){
        agent any
        steps{
          sh 'whoami'
        }
      }
    stage('docker build'){
      agent any
      steps{
        sh 'docker build -t springio/gs-spring-boot-docker .'
      }
    }
    stage('docker run'){
      agent any
      steps{
        sh 'sudo docker run -p 8085:8085 springio/gs-spring-boot-docker'
      }
    }
  }
}